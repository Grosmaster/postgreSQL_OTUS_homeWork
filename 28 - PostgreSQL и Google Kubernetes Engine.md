# Цель:

- запустить HA и multi master PostgreSQL кластер в Kubernetes

# Выполнение:

- Развернуть CitusDB в GKE
  - C:\Program Files (x86)\Google\Cloud SDK>gcloud beta container --project "handy-heuristic-322409" clusters create "citus" --zone "us-central1-c" --no-enable-basic-auth --cluster-version "1.19.9-gke.1900" --release-channel "None" --machine-type "e2-medium" --image-type "COS_CONTAINERD" --disk-type "pd-ssd" --disk-size "30" --metadata disable-legacy-endpoints=true --max-pods-per-node "110" --preemptible --num-nodes "3" --enable-stackdriver-kubernetes --enable-ip-alias --network "projects/handy-heuristic-322409/global/networks/default" --subnetwork "projects/handy-heuristic-322409/regions/us-central1/subnetworks/default" --no-enable-intra-node-visibility --default-max-pods-per-node "110" --no-enable-master-authorized-networks --addons HorizontalPodAutoscaling,HttpLoadBalancing,GcePersistentDiskCsiDriver --enable-autoupgrade --enable-autorepair --max-surge-upgrade 1 --max-unavailable-upgrade 0 --enable-shielded-nodes --node-locations "us-central1-c" --service-account=1061349314283@cloudservices.gserviceaccount.com
  - ![image-20210822090723562](28%20-%20PostgreSQL%20%D0%B8%20Google%20Kubernetes%20Engine.assets/image-20210822090723562.png)
  - возникли с ошибки
  - запустил GKE (был в состояние disable)
  - ![image-20210822091637253](28%20-%20PostgreSQL%20%D0%B8%20Google%20Kubernetes%20Engine.assets/image-20210822091637253-16296093978821.png)
  - повторил команду gcloud beta container --project "handy-heuristic-322409" clusters create "citus" --zone "us-central1-c" --no-enable-basic-auth --cluster-version "1.19.9-gke.1900" --release-channel "None" --machine-type "e2-medium" --image-type "COS_CONTAINERD" --disk-type "pd-ssd" --disk-size "30" --metadata disable-legacy-endpoints=true --max-pods-per-node "110" --preemptible --num-nodes "3" --enable-stackdriver-kubernetes --enable-ip-alias --network "projects/handy-heuristic-322409/global/networks/default" --subnetwork "projects/handy-heuristic-322409/regions/us-central1/subnetworks/default" --no-enable-intra-node-visibility --default-max-pods-per-node "110" --no-enable-master-authorized-networks --addons HorizontalPodAutoscaling,HttpLoadBalancing,GcePersistentDiskCsiDriver --enable-autoupgrade --enable-autorepair --max-surge-upgrade 1 --max-unavailable-upgrade 0 --enable-shielded-nodes --node-locations "us-central1-c" 
  - ![image-20210822112450238](28%20-%20PostgreSQL%20%D0%B8%20Google%20Kubernetes%20Engine.assets/image-20210822112450238.png)
  - ![image-20210822112547831](28%20-%20PostgreSQL%20%D0%B8%20Google%20Kubernetes%20Engine.assets/image-20210822112547831.png)
  - kubectl create -f secrets.yaml
    kubectl create -f master.yaml
    kubectl apply -f workers.yaml
  - ![image-20210822112700296](28%20-%20PostgreSQL%20%D0%B8%20Google%20Kubernetes%20Engine.assets/image-20210822112700296.png)
  - kubectl exec -it pod/citus-master-7b86795598-jds5s -- bash
  - psql -U postgres
    SELECT * FROM master_get_active_worker_nodes();
  - ![image-20210822114839071](28%20-%20PostgreSQL%20%D0%B8%20Google%20Kubernetes%20Engine.assets/image-20210822114839071.png)
- залить 10 Гб чикагского такси. 
  - ![image-20210822115134733](28%20-%20PostgreSQL%20%D0%B8%20Google%20Kubernetes%20Engine.assets/image-20210822115134733.png)
  - ![image-20210822115313454](28%20-%20PostgreSQL%20%D0%B8%20Google%20Kubernetes%20Engine.assets/image-20210822115313454.png)
  - перешел в cloud shell так как win shell отказался копировать команды
  - ![image-20210822115841375](28%20-%20PostgreSQL%20%D0%B8%20Google%20Kubernetes%20Engine.assets/image-20210822115841375.png)
  - создал табличку
  - ![image-20210822115949440](28%20-%20PostgreSQL%20%D0%B8%20Google%20Kubernetes%20Engine.assets/image-20210822115949440.png)
    - SELECT create_distributed_table('taxi_trips', 'unique_key');
    - ![image-20210822120539134](28%20-%20PostgreSQL%20%D0%B8%20Google%20Kubernetes%20Engine.assets/image-20210822120539134.png)
  - \q
  - загрузил данные на вирталку gsutil -m cp -R gs://taxi_trips_full/000000000000.csv .
  - однако получил ошибку gsutil нету
  - попытался использовать wget тоже получил ошибку
  - загрузил wget
  - ![image-20210822122303296](28%20-%20PostgreSQL%20%D0%B8%20Google%20Kubernetes%20Engine.assets/image-20210822122303296.png)
  - ![image-20210822122934671](28%20-%20PostgreSQL%20%D0%B8%20Google%20Kubernetes%20Engine.assets/image-20210822122934671-16296209752442.png)
  - date && for f in *.csv*; do psql -U postgres -c "\\COPY taxi_trips FROM PROGRAM 'cat $f' CSV HEADER"; done && dat
  - команда не выполняется так как у записей пропущен taxi_id
  - ![image-20210822124251342](28%20-%20PostgreSQL%20%D0%B8%20Google%20Kubernetes%20Engine.assets/image-20210822124251342.png)
  - ![image-20210822124259926](28%20-%20PostgreSQL%20%D0%B8%20Google%20Kubernetes%20Engine.assets/image-20210822124259926.png)
  - взял другой набор данных hacker_news_full
  - ![image-20210822125404722](28%20-%20PostgreSQL%20%D0%B8%20Google%20Kubernetes%20Engine.assets/image-20210822125404722.png)
  - попытался скачать
  - ![image-20210822125720435](28%20-%20PostgreSQL%20%D0%B8%20Google%20Kubernetes%20Engine.assets/image-20210822125720435.png)
  - судя по всему wget скачивает страницу а не сам файл
  - проверил командой cat 
  - ![image-20210822125929075](28%20-%20PostgreSQL%20%D0%B8%20Google%20Kubernetes%20Engine.assets/image-20210822125929075.png)
  - так и есть файл с csv содежит просто страничку, а не данные
  - для проверки скорости создал тестовую таблицу на 10 млилионов строк
  - create table test as
    select generate_series, (random() * 70), date'2019-01-01' + (random() * 300)::int as order_date
          , (array['returned', 'completed', 'placed', 'shipped'])[(random() * 4)::int]
          , concat_ws(' ', (array['go', 'space', 'sun', 'London'])[(random() * 5)::int]
              , (array['the', 'capital', 'of', 'Great', 'Britain'])[(random() * 6)::int]
              , (array['some', 'another', 'example', 'with', 'words'])[(random() * 6)::int]
              )
    from generate_series(1, 10000000); 
  - ![image-20210822133149905](28%20-%20PostgreSQL%20%D0%B8%20Google%20Kubernetes%20Engine.assets/image-20210822133149905.png)
- Шардировать. 
  - SELECT create_distributed_table('test', 'generate_series');
  - ![image-20210822133528304](28%20-%20PostgreSQL%20%D0%B8%20Google%20Kubernetes%20Engine.assets/image-20210822133528304.png)
- Оценить производительность. 
  - ![image-20210822133646825](28%20-%20PostgreSQL%20%D0%B8%20Google%20Kubernetes%20Engine.assets/image-20210822133646825.png)
  - select count(*) from test;
  - 801 ms
- Описать проблемы, с которыми столкнулись
  - основаная проблема возникла при загрузки файла, попытлся закинуть его через гугл диск, но оказалось все не так тривиально, поэтому просто сгенерировал табличку для теста

---

- залить все чикагское такси, оценить производительность
  - Задание * на данный момент не стал выполнять

