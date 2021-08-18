# Цель:

- реализовать свой миникластер на 3 ВМ.

# Выполнение:

- На 1 ВМ создаем таблицы test для записи, test2 для запросов на чтение.

  - Воспользовался проектом с прошлых заданий

  - ![image-20210818214210587](Untitled.assets/image-20210818214210587.png)

  - создал database rep

  - две таблички тест и тест2

  - добавил запись в таблицу

  - для таблицы тест2 создал триггер, который не дает изменять таблицу

  - > create function do_not_change()
    >   returns trigger
    > as
    > $$
    > begin
    >   raise exception 'Cannot modify table.';
    > end;
    > $$
    > language plpgsql;
    >
    > create trigger no_change_trigger
    >   before insert or update or delete on "test2"
    >   execute procedure do_not_change();

  - ![image-20210818215001853](Untitled.assets/image-20210818215001853.png)

- Создаем публикацию таблицы test и подписываемся на публикацию таблицы test2 с ВМ №2.

  - ALTER SYSTEM SET wal_level = logical;
  -  sudo pg_ctlcluster 13 main restart
  -  CREATE PUBLICATION test_pub FOR TABLE test;
  - ![image-20210818215134494](Untitled.assets/image-20210818215134494.png)
  - подпишусь на изменения после работы на втором кластере.

- На 2 ВМ создаем таблицы test2 для записи, test для запросов на чтение. 

  - создал второй кластер sudo pg_createcluster -d /var/lib/postgresql/13/main2 13 main2
  - ![image-20210818195122890](Untitled.assets/image-20210818195122890.png)
  - и запустил его
  - ![image-20210818195237789](Untitled.assets/image-20210818195237789.png)
  - повторил действия с первого класстера на втором, за тем исключением, что тригер на отказ доступа изменений добавил к 1 таблице, а значение добавил во вторую
  - ![image-20210818215523610](Untitled.assets/image-20210818215523610.png)

- Создаем публикацию таблицы test2 и подписываемся на публикацию таблицы test1 с ВМ №1. 

  - на втором кластере ALTER SYSTEM SET wal_level = logical;
  - sudo pg_ctlcluster 13 main2 restart
  - CREATE PUBLICATION test2_pub FOR TABLE test2;
  - ![image-20210818215650597](Untitled.assets/image-20210818215650597.png)
  - CREATE SUBSCRIPTION test_sub
  - CONNECTION 'host=localhost port=5432 user=postgres password=123 dbname=rep' 
  - PUBLICATION test_pub WITH (copy_data = true);
  - ![image-20210818215918645](Untitled.assets/image-20210818215918645.png)

- подписываемся на публикацию таблицы test2 с ВМ №2.

  - CREATE SUBSCRIPTION test2_sub
  - CONNECTION 'host=localhost port=5433 user=postgres password=123 dbname=rep' 
  - PUBLICATION test2_pub WITH (copy_data = true);
  - ![image-20210818220107157](Untitled.assets/image-20210818220107157.png)
  - проверил что данные обновляются добавив 12
  - ![image-20210818220158628](Untitled.assets/image-20210818220158628.png)

- 3 ВМ использовать как реплику для чтения и бэкапов (подписаться на таблицы из ВМ №1 и №2 ). 

  - создал третий кластер sudo pg_createcluster -d /var/lib/postgresql/13/main3 13 main3 и запустил его
  - ![image-20210818220547730](Untitled.assets/image-20210818220547730.png)
  - создал database и таблицы
  - ![image-20210818220850701](Untitled.assets/image-20210818220850701.png)
  - CREATE SUBSCRIPTION test2_sub
  - CONNECTION 'host=localhost port=5433 user=postgres password=123 dbname=rep' 
  - PUBLICATION test2_pub WITH (copy_data = true);
  - CREATE SUBSCRIPTION test_sub
  - CONNECTION 'host=localhost port=5432 user=postgres password=123 dbname=rep' 
  - PUBLICATION test_pub WITH (copy_data = true);
  - попытался подписаться не получилось
  - ![image-20210818221121183](Untitled.assets/image-20210818221121183.png)
  - ALTER SYSTEM SET wal_level = logical;
  - sudo pg_ctlcluster 13 main3 restart
  - ![image-20210818221551107](Untitled.assets/image-20210818221551107.png)

- Небольшое описание, того, что получилось.

  - создал три кластера и настроил логическую репликацию между ними

---

- реализовать горячее реплицирование для высокой доступности на 4ВМ. Источником должна выступать ВМ №3. Написать с какими проблемами столкнулись.
  - задание со * на данный момент не стал выполнять, возможно вернусь позже

