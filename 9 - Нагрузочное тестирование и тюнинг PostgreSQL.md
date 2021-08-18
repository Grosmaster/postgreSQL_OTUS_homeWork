# Цель:

- сделать нагрузочное тестирование PostgreSQL
- настроить параметры PostgreSQL для достижения максимальной производительности

# Выполнение:

- сделать проект ---10 
  - Воспользовался проектом с прошлых заданий
  - ![image-20210818103209335](9%20-%20%D0%9D%D0%B0%D0%B3%D1%80%D1%83%D0%B7%D0%BE%D1%87%D0%BD%D0%BE%D0%B5%20%D1%82%D0%B5%D1%81%D1%82%D0%B8%D1%80%D0%BE%D0%B2%D0%B0%D0%BD%D0%B8%D0%B5%20%D0%B8%20%D1%82%D1%8E%D0%BD%D0%B8%D0%BD%D0%B3%20PostgreSQL.assets/image-20210818103209335.png)
  
- сделать инстанс Google Cloud Engine типа e2-medium с ОС Ubuntu 20.04 

  - ![image-20210818104715387](9%20-%20%D0%9D%D0%B0%D0%B3%D1%80%D1%83%D0%B7%D0%BE%D1%87%D0%BD%D0%BE%D0%B5%20%D1%82%D0%B5%D1%81%D1%82%D0%B8%D1%80%D0%BE%D0%B2%D0%B0%D0%BD%D0%B8%D0%B5%20%D0%B8%20%D1%82%D1%8E%D0%BD%D0%B8%D0%BD%D0%B3%20PostgreSQL.assets/image-20210818104715387.png)
  - ![image-20210818104646474](9%20-%20%D0%9D%D0%B0%D0%B3%D1%80%D1%83%D0%B7%D0%BE%D1%87%D0%BD%D0%BE%D0%B5%20%D1%82%D0%B5%D1%81%D1%82%D0%B8%D1%80%D0%BE%D0%B2%D0%B0%D0%BD%D0%B8%D0%B5%20%D0%B8%20%D1%82%D1%8E%D0%BD%D0%B8%D0%BD%D0%B3%20PostgreSQL.assets/image-20210818104646474.png)

- поставить на него PostgreSQL 13 из пакетов собираемых postgres.org
  - sudo apt update && sudo apt upgrade -y && sudo sh -c 'echo "deb http://apt.postgresql.org/pub/repos/apt $(lsb_release -cs)-pgdg main" > /etc/apt/sources.list.d/pgdg.list' && wget --quiet -O - https://www.postgresql.org/media/keys/ACCC4CF8.asc | sudo apt-key add - && sudo apt-get update && sudo apt-get -y install postgresql && sudo apt install unzip
  - ![image-20210818105445374](9%20-%20%D0%9D%D0%B0%D0%B3%D1%80%D1%83%D0%B7%D0%BE%D1%87%D0%BD%D0%BE%D0%B5%20%D1%82%D0%B5%D1%81%D1%82%D0%B8%D1%80%D0%BE%D0%B2%D0%B0%D0%BD%D0%B8%D0%B5%20%D0%B8%20%D1%82%D1%8E%D0%BD%D0%B8%D0%BD%D0%B3%20PostgreSQL.assets/image-20210818105445374.png)
  
- настроить кластер PostgreSQL 13 на максимальную производительность не обращая внимание на возможные проблемы с надежностью в случае аварийной перезагрузки виртуальной машины 

  - воспользовался рекомендациями https://pgtune.leopard.in.ua/#/
  - ![image-20210818110545693](9%20-%20%D0%9D%D0%B0%D0%B3%D1%80%D1%83%D0%B7%D0%BE%D1%87%D0%BD%D0%BE%D0%B5%20%D1%82%D0%B5%D1%81%D1%82%D0%B8%D1%80%D0%BE%D0%B2%D0%B0%D0%BD%D0%B8%D0%B5%20%D0%B8%20%D1%82%D1%8E%D0%BD%D0%B8%D0%BD%D0%B3%20PostgreSQL.assets/image-20210818110545693.png)
  - ![image-20210818111131005](9%20-%20%D0%9D%D0%B0%D0%B3%D1%80%D1%83%D0%B7%D0%BE%D1%87%D0%BD%D0%BE%D0%B5%20%D1%82%D0%B5%D1%81%D1%82%D0%B8%D1%80%D0%BE%D0%B2%D0%B0%D0%BD%D0%B8%D0%B5%20%D0%B8%20%D1%82%D1%8E%D0%BD%D0%B8%D0%BD%D0%B3%20PostgreSQL.assets/image-20210818111131005.png)
  - ![image-20210818111228557](9%20-%20%D0%9D%D0%B0%D0%B3%D1%80%D1%83%D0%B7%D0%BE%D1%87%D0%BD%D0%BE%D0%B5%20%D1%82%D0%B5%D1%81%D1%82%D0%B8%D1%80%D0%BE%D0%B2%D0%B0%D0%BD%D0%B8%D0%B5%20%D0%B8%20%D1%82%D1%8E%D0%BD%D0%B8%D0%BD%D0%B3%20PostgreSQL.assets/image-20210818111228557.png)

- нагрузить кластер через утилиту https://github.com/Percona-Lab/sysbench-tpcc (требует установки https://github.com/akopytov/sysbench) 

  - использовал туториал https://severalnines.com/database-blog/how-benchmark-postgresql-performance-using-sysbench

  - ```
    curl -s https://packagecloud.io/install/repositories/akopytov/sysbench/script.deb.sh | sudo bash
    sudo apt -y install sysbench
    ```

  - ![image-20210818110653813](9%20-%20%D0%9D%D0%B0%D0%B3%D1%80%D1%83%D0%B7%D0%BE%D1%87%D0%BD%D0%BE%D0%B5%20%D1%82%D0%B5%D1%81%D1%82%D0%B8%D1%80%D0%BE%D0%B2%D0%B0%D0%BD%D0%B8%D0%B5%20%D0%B8%20%D1%82%D1%8E%D0%BD%D0%B8%D0%BD%D0%B3%20PostgreSQL.assets/image-20210818110653813.png)

- ![image-20210818111641338](9%20-%20%D0%9D%D0%B0%D0%B3%D1%80%D1%83%D0%B7%D0%BE%D1%87%D0%BD%D0%BE%D0%B5%20%D1%82%D0%B5%D1%81%D1%82%D0%B8%D1%80%D0%BE%D0%B2%D0%B0%D0%BD%D0%B8%D0%B5%20%D0%B8%20%D1%82%D1%8E%D0%BD%D0%B8%D0%BD%D0%B3%20PostgreSQL.assets/image-20210818111641338.png)

- ![image-20210818112828306](9%20-%20%D0%9D%D0%B0%D0%B3%D1%80%D1%83%D0%B7%D0%BE%D1%87%D0%BD%D0%BE%D0%B5%20%D1%82%D0%B5%D1%81%D1%82%D0%B8%D1%80%D0%BE%D0%B2%D0%B0%D0%BD%D0%B8%D0%B5%20%D0%B8%20%D1%82%D1%8E%D0%BD%D0%B8%D0%BD%D0%B3%20PostgreSQL.assets/image-20210818112828306.png)

- ![image-20210818113104204](9%20-%20%D0%9D%D0%B0%D0%B3%D1%80%D1%83%D0%B7%D0%BE%D1%87%D0%BD%D0%BE%D0%B5%20%D1%82%D0%B5%D1%81%D1%82%D0%B8%D1%80%D0%BE%D0%B2%D0%B0%D0%BD%D0%B8%D0%B5%20%D0%B8%20%D1%82%D1%8E%D0%BD%D0%B8%D0%BD%D0%B3%20PostgreSQL.assets/image-20210818113104204.png)

  - ```
    Read/Write Load
    
    sysbench \
    --db-driver=pgsql \
    --report-interval=2 \
    --oltp-table-size=100000 \
    --oltp-tables-count=24 \
    --threads=64 \
    --``time``=60 \
    --pgsql-host=127.0.0.1 \
    --pgsql-port=5432 \
    --pgsql-user=sbtest \
    --pgsql-password=password \
    --pgsql-db=sbtest \
    /usr/share/sysbench/tests/include/oltp_legacy/oltp``.lua \
    run
    ```

- написать какого значения tps удалось достичь, показать какие параметры в какие значения устанавливали и почему

  - ![image-20210818113457034](9%20-%20%D0%9D%D0%B0%D0%B3%D1%80%D1%83%D0%B7%D0%BE%D1%87%D0%BD%D0%BE%D0%B5%20%D1%82%D0%B5%D1%81%D1%82%D0%B8%D1%80%D0%BE%D0%B2%D0%B0%D0%BD%D0%B8%D0%B5%20%D0%B8%20%D1%82%D1%8E%D0%BD%D0%B8%D0%BD%D0%B3%20PostgreSQL.assets/image-20210818113457034.png)
  - ![image-20210818111131005](9%20-%20%D0%9D%D0%B0%D0%B3%D1%80%D1%83%D0%B7%D0%BE%D1%87%D0%BD%D0%BE%D0%B5%20%D1%82%D0%B5%D1%81%D1%82%D0%B8%D1%80%D0%BE%D0%B2%D0%B0%D0%BD%D0%B8%D0%B5%20%D0%B8%20%D1%82%D1%8E%D0%BD%D0%B8%D0%BD%D0%B3%20PostgreSQL.assets/image-20210818111131005.png)
  - shared_buffers - 25% от оперативки
  - effective_cache_size 75% ОП от общей на сервере
  - max_wal_size этот параметр может привести к увеличению времени, которое потребуется для восстановления после сбоя, но для нас это не критично

