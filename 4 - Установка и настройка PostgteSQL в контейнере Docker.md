# Цель:

- установить PostgreSQL в Docker контейнере
- настроить контейнер для внешнего подключения

# Выполнение:

- сделать в GCE инстанс с Ubuntu 20.04

  - Воспользовался инстансом с прошлых заданий
  - ![image-20210811190424596](4%20-%20%D0%A3%D1%81%D1%82%D0%B0%D0%BD%D0%BE%D0%B2%D0%BA%D0%B0%20%D0%B8%20%D0%BD%D0%B0%D1%81%D1%82%D1%80%D0%BE%D0%B9%D0%BA%D0%B0%20PostgteSQL%20%D0%B2%20%D0%BA%D0%BE%D0%BD%D1%82%D0%B5%D0%B9%D0%BD%D0%B5%D1%80%D0%B5%20Docker.assets/image-20210811190424596.png)
  - ![image-20210811190329348](4%20-%20%D0%A3%D1%81%D1%82%D0%B0%D0%BD%D0%BE%D0%B2%D0%BA%D0%B0%20%D0%B8%20%D0%BD%D0%B0%D1%81%D1%82%D1%80%D0%BE%D0%B9%D0%BA%D0%B0%20PostgteSQL%20%D0%B2%20%D0%BA%D0%BE%D0%BD%D1%82%D0%B5%D0%B9%D0%BD%D0%B5%D1%80%D0%B5%20Docker.assets/image-20210811190329348.png)

- поставить на нем Docker Engine

  - sudo apt-get update

  - ![image-20210811191024659](4%20-%20%D0%A3%D1%81%D1%82%D0%B0%D0%BD%D0%BE%D0%B2%D0%BA%D0%B0%20%D0%B8%20%D0%BD%D0%B0%D1%81%D1%82%D1%80%D0%BE%D0%B9%D0%BA%D0%B0%20PostgteSQL%20%D0%B2%20%D0%BA%D0%BE%D0%BD%D1%82%D0%B5%D0%B9%D0%BD%D0%B5%D1%80%D0%B5%20Docker.assets/image-20210811191024659.png)

  - sudo apt-get install docker-ce docker-ce-cli containerd.io 

  - Команда не сработала

  - ![image-20210811191128474](4%20-%20%D0%A3%D1%81%D1%82%D0%B0%D0%BD%D0%BE%D0%B2%D0%BA%D0%B0%20%D0%B8%20%D0%BD%D0%B0%D1%81%D1%82%D1%80%D0%BE%D0%B9%D0%BA%D0%B0%20PostgteSQL%20%D0%B2%20%D0%BA%D0%BE%D0%BD%D1%82%D0%B5%D0%B9%D0%BD%D0%B5%D1%80%D0%B5%20Docker.assets/image-20210811191128474.png)

  - ```
    sudo apt-get install \
        apt-transport-https \
        ca-certificates \
        curl \
        gnupg \
        lsb-release
    ```

  - ![image-20210811191354293](4%20-%20%D0%A3%D1%81%D1%82%D0%B0%D0%BD%D0%BE%D0%B2%D0%BA%D0%B0%20%D0%B8%20%D0%BD%D0%B0%D1%81%D1%82%D1%80%D0%BE%D0%B9%D0%BA%D0%B0%20PostgteSQL%20%D0%B2%20%D0%BA%D0%BE%D0%BD%D1%82%D0%B5%D0%B9%D0%BD%D0%B5%D1%80%D0%B5%20Docker.assets/image-20210811191354293.png)

  - ```
    curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo gpg --dearmor -o /usr/share/keyrings/docker-archive-keyring.gpg
    ```

  - ![image-20210811191433498](4%20-%20%D0%A3%D1%81%D1%82%D0%B0%D0%BD%D0%BE%D0%B2%D0%BA%D0%B0%20%D0%B8%20%D0%BD%D0%B0%D1%81%D1%82%D1%80%D0%BE%D0%B9%D0%BA%D0%B0%20PostgteSQL%20%D0%B2%20%D0%BA%D0%BE%D0%BD%D1%82%D0%B5%D0%B9%D0%BD%D0%B5%D1%80%D0%B5%20Docker.assets/image-20210811191433498-16286948742061.png)

  - ```
    echo \
      "deb [arch=amd64 signed-by=/usr/share/keyrings/docker-archive-keyring.gpg] https://download.docker.com/linux/ubuntu \
      $(lsb_release -cs) stable" | sudo tee /etc/apt/sources.list.d/docker.list > /dev/null
    ```

  - ```
     sudo apt-get update
     sudo apt-get install docker-ce docker-ce-cli containerd.io
    ```

  - ![image-20210811191857695](4%20-%20%D0%A3%D1%81%D1%82%D0%B0%D0%BD%D0%BE%D0%B2%D0%BA%D0%B0%20%D0%B8%20%D0%BD%D0%B0%D1%81%D1%82%D1%80%D0%BE%D0%B9%D0%BA%D0%B0%20PostgteSQL%20%D0%B2%20%D0%BA%D0%BE%D0%BD%D1%82%D0%B5%D0%B9%D0%BD%D0%B5%D1%80%D0%B5%20Docker.assets/image-20210811191857695.png)

  - Проверил работу командой  sudo docker run hello-world

  - ![image-20210811192001747](4%20-%20%D0%A3%D1%81%D1%82%D0%B0%D0%BD%D0%BE%D0%B2%D0%BA%D0%B0%20%D0%B8%20%D0%BD%D0%B0%D1%81%D1%82%D1%80%D0%BE%D0%B9%D0%BA%D0%B0%20PostgteSQL%20%D0%B2%20%D0%BA%D0%BE%D0%BD%D1%82%D0%B5%D0%B9%D0%BD%D0%B5%D1%80%D0%B5%20Docker.assets/image-20210811192001747.png)

- сделать каталог /var/lib/postgres 

  -  sudo mkdir /var/lib/postgres
  - ![image-20210811192053654](4%20-%20%D0%A3%D1%81%D1%82%D0%B0%D0%BD%D0%BE%D0%B2%D0%BA%D0%B0%20%D0%B8%20%D0%BD%D0%B0%D1%81%D1%82%D1%80%D0%BE%D0%B9%D0%BA%D0%B0%20PostgteSQL%20%D0%B2%20%D0%BA%D0%BE%D0%BD%D1%82%D0%B5%D0%B9%D0%BD%D0%B5%D1%80%D0%B5%20Docker.assets/image-20210811192053654.png)

- развернуть контейнер с PostgreSQL 13 смонтировав в него /var/lib/postgres

  - sudo docker network create pg-net
  - sudo  docker run --name pg-docker --network pg-net -e POSTGRES_PASSWORD=postgres -d -p 5432:5432 -v /var/lib/postgres:/var/lib/postgresql/data postgres:13
  - ![image-20210811195801142](4%20-%20%D0%A3%D1%81%D1%82%D0%B0%D0%BD%D0%BE%D0%B2%D0%BA%D0%B0%20%D0%B8%20%D0%BD%D0%B0%D1%81%D1%82%D1%80%D0%BE%D0%B9%D0%BA%D0%B0%20PostgteSQL%20%D0%B2%20%D0%BA%D0%BE%D0%BD%D1%82%D0%B5%D0%B9%D0%BD%D0%B5%D1%80%D0%B5%20Docker.assets/image-20210811195801142.png)
  - Столкнулся с проблемой что порт уже используется
  - Остановил постгрес и попытался заного запустить образ
  - ![image-20210811200241312](4%20-%20%D0%A3%D1%81%D1%82%D0%B0%D0%BD%D0%BE%D0%B2%D0%BA%D0%B0%20%D0%B8%20%D0%BD%D0%B0%D1%81%D1%82%D1%80%D0%BE%D0%B9%D0%BA%D0%B0%20PostgteSQL%20%D0%B2%20%D0%BA%D0%BE%D0%BD%D1%82%D0%B5%D0%B9%D0%BD%D0%B5%D1%80%D0%B5%20Docker.assets/image-20210811200241312.png)
  - Однако столкнулся с тем что образ уже был запущен с прошлой попытке
  - удалил контейнерой командой sudo docker rm pg-docker
  - ![image-20210811200621330](4%20-%20%D0%A3%D1%81%D1%82%D0%B0%D0%BD%D0%BE%D0%B2%D0%BA%D0%B0%20%D0%B8%20%D0%BD%D0%B0%D1%81%D1%82%D1%80%D0%BE%D0%B9%D0%BA%D0%B0%20PostgteSQL%20%D0%B2%20%D0%BA%D0%BE%D0%BD%D1%82%D0%B5%D0%B9%D0%BD%D0%B5%D1%80%D0%B5%20Docker.assets/image-20210811200621330.png)
  - повтороно запустил контейнер
  - ![image-20210811200716170](4%20-%20%D0%A3%D1%81%D1%82%D0%B0%D0%BD%D0%BE%D0%B2%D0%BA%D0%B0%20%D0%B8%20%D0%BD%D0%B0%D1%81%D1%82%D1%80%D0%BE%D0%B9%D0%BA%D0%B0%20PostgteSQL%20%D0%B2%20%D0%BA%D0%BE%D0%BD%D1%82%D0%B5%D0%B9%D0%BD%D0%B5%D1%80%D0%B5%20Docker.assets/image-20210811200716170.png)

- развернуть контейнер с клиентом postgres 

  - sudo docker run -it --rm --network pg-net --name pg-client postgres:13 psql -h pg-server -U postgres
  - ![image-20210811201342640](4%20-%20%D0%A3%D1%81%D1%82%D0%B0%D0%BD%D0%BE%D0%B2%D0%BA%D0%B0%20%D0%B8%20%D0%BD%D0%B0%D1%81%D1%82%D1%80%D0%BE%D0%B9%D0%BA%D0%B0%20PostgteSQL%20%D0%B2%20%D0%BA%D0%BE%D0%BD%D1%82%D0%B5%D0%B9%D0%BD%D0%B5%D1%80%D0%B5%20Docker.assets/image-20210811201342640.png)
  - попытался подключиться к External IP
  - ![image-20210811203047586](4%20-%20%D0%A3%D1%81%D1%82%D0%B0%D0%BD%D0%BE%D0%B2%D0%BA%D0%B0%20%D0%B8%20%D0%BD%D0%B0%D1%81%D1%82%D1%80%D0%BE%D0%B9%D0%BA%D0%B0%20PostgteSQL%20%D0%B2%20%D0%BA%D0%BE%D0%BD%D1%82%D0%B5%D0%B9%D0%BD%D0%B5%D1%80%D0%B5%20Docker.assets/image-20210811203047586.png)
  - поптылся подключиться к Internal IP
  - ![image-20210811203129265](4%20-%20%D0%A3%D1%81%D1%82%D0%B0%D0%BD%D0%BE%D0%B2%D0%BA%D0%B0%20%D0%B8%20%D0%BD%D0%B0%D1%81%D1%82%D1%80%D0%BE%D0%B9%D0%BA%D0%B0%20PostgteSQL%20%D0%B2%20%D0%BA%D0%BE%D0%BD%D1%82%D0%B5%D0%B9%D0%BD%D0%B5%D1%80%D0%B5%20Docker.assets/image-20210811203129265.png)

- подключится из контейнера с клиентом к контейнеру с сервером и сделать таблицу с парой строк 

  - CREATE TABLE test (i serial, amount int);
    INSERT INTO test(amount) VALUES (100);
    INSERT INTO test(amount) VALUES (500);
    SELECT * FROM test;
  - ![image-20210811204433043](4%20-%20%D0%A3%D1%81%D1%82%D0%B0%D0%BD%D0%BE%D0%B2%D0%BA%D0%B0%20%D0%B8%20%D0%BD%D0%B0%D1%81%D1%82%D1%80%D0%BE%D0%B9%D0%BA%D0%B0%20PostgteSQL%20%D0%B2%20%D0%BA%D0%BE%D0%BD%D1%82%D0%B5%D0%B9%D0%BD%D0%B5%D1%80%D0%B5%20Docker.assets/image-20210811204433043.png)

- подключится к контейнеру с сервером с ноутбука/компьютера извне инстансов GCP 

  - попытался подключить pgAdmin
  - ![image-20210811211057272](4%20-%20%D0%A3%D1%81%D1%82%D0%B0%D0%BD%D0%BE%D0%B2%D0%BA%D0%B0%20%D0%B8%20%D0%BD%D0%B0%D1%81%D1%82%D1%80%D0%BE%D0%B9%D0%BA%D0%B0%20PostgteSQL%20%D0%B2%20%D0%BA%D0%BE%D0%BD%D1%82%D0%B5%D0%B9%D0%BD%D0%B5%D1%80%D0%B5%20Docker.assets/image-20210811211057272.png)
  - проверил конфиг
  - ![image-20210811211625218](4%20-%20%D0%A3%D1%81%D1%82%D0%B0%D0%BD%D0%BE%D0%B2%D0%BA%D0%B0%20%D0%B8%20%D0%BD%D0%B0%D1%81%D1%82%D1%80%D0%BE%D0%B9%D0%BA%D0%B0%20PostgteSQL%20%D0%B2%20%D0%BA%D0%BE%D0%BD%D1%82%D0%B5%D0%B9%D0%BD%D0%B5%D1%80%D0%B5%20Docker.assets/image-20210811211625218.png)
  - Забыл настроить Firewall гугла
  - ![image-20210811214616354](4%20-%20%D0%A3%D1%81%D1%82%D0%B0%D0%BD%D0%BE%D0%B2%D0%BA%D0%B0%20%D0%B8%20%D0%BD%D0%B0%D1%81%D1%82%D1%80%D0%BE%D0%B9%D0%BA%D0%B0%20PostgteSQL%20%D0%B2%20%D0%BA%D0%BE%D0%BD%D1%82%D0%B5%D0%B9%D0%BD%D0%B5%D1%80%D0%B5%20Docker.assets/image-20210811214616354.png)
  - ![image-20210811214847059](4%20-%20%D0%A3%D1%81%D1%82%D0%B0%D0%BD%D0%BE%D0%B2%D0%BA%D0%B0%20%D0%B8%20%D0%BD%D0%B0%D1%81%D1%82%D1%80%D0%BE%D0%B9%D0%BA%D0%B0%20PostgteSQL%20%D0%B2%20%D0%BA%D0%BE%D0%BD%D1%82%D0%B5%D0%B9%D0%BD%D0%B5%D1%80%D0%B5%20Docker.assets/image-20210811214847059.png)
  - Ошибка повторилась
  - заменил 0.0.0.0 на 0.0.0.0/0
  - ![image-20210811215400725](4%20-%20%D0%A3%D1%81%D1%82%D0%B0%D0%BD%D0%BE%D0%B2%D0%BA%D0%B0%20%D0%B8%20%D0%BD%D0%B0%D1%81%D1%82%D1%80%D0%BE%D0%B9%D0%BA%D0%B0%20PostgteSQL%20%D0%B2%20%D0%BA%D0%BE%D0%BD%D1%82%D0%B5%D0%B9%D0%BD%D0%B5%D1%80%D0%B5%20Docker.assets/image-20210811215400725.png)
  - Изменил параметры инстанса
    - ![image-20210811220900572](4%20-%20%D0%A3%D1%81%D1%82%D0%B0%D0%BD%D0%BE%D0%B2%D0%BA%D0%B0%20%D0%B8%20%D0%BD%D0%B0%D1%81%D1%82%D1%80%D0%BE%D0%B9%D0%BA%D0%B0%20PostgteSQL%20%D0%B2%20%D0%BA%D0%BE%D0%BD%D1%82%D0%B5%D0%B9%D0%BD%D0%B5%D1%80%D0%B5%20Docker.assets/image-20210811220900572.png)
    - ![image-20210811221000160](4%20-%20%D0%A3%D1%81%D1%82%D0%B0%D0%BD%D0%BE%D0%B2%D0%BA%D0%B0%20%D0%B8%20%D0%BD%D0%B0%D1%81%D1%82%D1%80%D0%BE%D0%B9%D0%BA%D0%B0%20PostgteSQL%20%D0%B2%20%D0%BA%D0%BE%D0%BD%D1%82%D0%B5%D0%B9%D0%BD%D0%B5%D1%80%D0%B5%20Docker.assets/image-20210811221000160.png)
  - Необходимо было поставить Apply to all для firewall, а не кастомный тег
    - ![image-20210811221830132](4%20-%20%D0%A3%D1%81%D1%82%D0%B0%D0%BD%D0%BE%D0%B2%D0%BA%D0%B0%20%D0%B8%20%D0%BD%D0%B0%D1%81%D1%82%D1%80%D0%BE%D0%B9%D0%BA%D0%B0%20PostgteSQL%20%D0%B2%20%D0%BA%D0%BE%D0%BD%D1%82%D0%B5%D0%B9%D0%BD%D0%B5%D1%80%D0%B5%20Docker.assets/image-20210811221830132.png)
  - ![image-20210811222133960](4%20-%20%D0%A3%D1%81%D1%82%D0%B0%D0%BD%D0%BE%D0%B2%D0%BA%D0%B0%20%D0%B8%20%D0%BD%D0%B0%D1%81%D1%82%D1%80%D0%BE%D0%B9%D0%BA%D0%B0%20PostgteSQL%20%D0%B2%20%D0%BA%D0%BE%D0%BD%D1%82%D0%B5%D0%B9%D0%BD%D0%B5%D1%80%D0%B5%20Docker.assets/image-20210811222133960.png)

  

- удалить контейнер с сервером

  - sudo docker rm  pg-docker
  - ![image-20210811215645795](4%20-%20%D0%A3%D1%81%D1%82%D0%B0%D0%BD%D0%BE%D0%B2%D0%BA%D0%B0%20%D0%B8%20%D0%BD%D0%B0%D1%81%D1%82%D1%80%D0%BE%D0%B9%D0%BA%D0%B0%20PostgteSQL%20%D0%B2%20%D0%BA%D0%BE%D0%BD%D1%82%D0%B5%D0%B9%D0%BD%D0%B5%D1%80%D0%B5%20Docker.assets/image-20210811215645795.png)
  - выдал ошибку так как забыл остановить контейнер
  - ![image-20210811215746432](4%20-%20%D0%A3%D1%81%D1%82%D0%B0%D0%BD%D0%BE%D0%B2%D0%BA%D0%B0%20%D0%B8%20%D0%BD%D0%B0%D1%81%D1%82%D1%80%D0%BE%D0%B9%D0%BA%D0%B0%20PostgteSQL%20%D0%B2%20%D0%BA%D0%BE%D0%BD%D1%82%D0%B5%D0%B9%D0%BD%D0%B5%D1%80%D0%B5%20Docker.assets/image-20210811215746432.png)

- создать его заново 

  - sudo  docker run --name pg-docker --network pg-net -e POSTGRES_PASSWORD=postgres -d -p 5432:5432 -v /var/lib/postgres:/var/lib/postgresql/data postgres:13
  - ![image-20210811215835427](4%20-%20%D0%A3%D1%81%D1%82%D0%B0%D0%BD%D0%BE%D0%B2%D0%BA%D0%B0%20%D0%B8%20%D0%BD%D0%B0%D1%81%D1%82%D1%80%D0%BE%D0%B9%D0%BA%D0%B0%20PostgteSQL%20%D0%B2%20%D0%BA%D0%BE%D0%BD%D1%82%D0%B5%D0%B9%D0%BD%D0%B5%D1%80%D0%B5%20Docker.assets/image-20210811215835427.png)

- подключится снова из контейнера с клиентом к контейнеру с сервером 

  - ![image-20210811215948600](4%20-%20%D0%A3%D1%81%D1%82%D0%B0%D0%BD%D0%BE%D0%B2%D0%BA%D0%B0%20%D0%B8%20%D0%BD%D0%B0%D1%81%D1%82%D1%80%D0%BE%D0%B9%D0%BA%D0%B0%20PostgteSQL%20%D0%B2%20%D0%BA%D0%BE%D0%BD%D1%82%D0%B5%D0%B9%D0%BD%D0%B5%D1%80%D0%B5%20Docker.assets/image-20210811215948600.png)

- проверить, что данные остались на месте 

  - все данные сохранились
  - ![image-20210811220021115](4%20-%20%D0%A3%D1%81%D1%82%D0%B0%D0%BD%D0%BE%D0%B2%D0%BA%D0%B0%20%D0%B8%20%D0%BD%D0%B0%D1%81%D1%82%D1%80%D0%BE%D0%B9%D0%BA%D0%B0%20PostgteSQL%20%D0%B2%20%D0%BA%D0%BE%D0%BD%D1%82%D0%B5%D0%B9%D0%BD%D0%B5%D1%80%D0%B5%20Docker.assets/image-20210811220021115.png)

- оставляйте в ЛК ДЗ комментарии что и как вы делали и как боролись с проблемами

  - пробелемы и способы решения описал выше