# Работа с уровнями изоляции транзакции в PostgreSQL

## Цель:

- научиться работать с Google Cloud Platform на уровне Google Compute Engine (IaaS)
- научиться управлять уровнем изолции транзации в PostgreSQL и понимать особенность работы уровней read commited и repeatable read

## Выполнение задания:

- создать новый проект в Google Cloud Platform, например postgres2021-, где yyyymmdd год, месяц и день вашего рождения (имя проекта должно быть уникально на уровне GCP) 
  - ![image-20210809140703796](2%20-%20SQL%20%D0%B8%20%D1%80%D0%B5%D0%BB%D1%8F%D1%86%D0%B8%D0%BE%D0%BD%D0%BD%D1%8B%D0%B5%20%D0%A1%D0%A3%D0%91%D0%94.%20%D0%92%D0%B2%D0%B5%D0%B4%D0%B5%D0%BD%D0%B8%D0%B5%20%D0%B2%20PostgreSQL.assets/image-20210809140703796.png)
- дать возможность доступа к этому проекту пользователю [ifti@yandex.ru](mailto:ifti@yandex.ru) с ролью Project Editor
  - ![image-20210809141037055](2%20-%20SQL%20%D0%B8%20%D1%80%D0%B5%D0%BB%D1%8F%D1%86%D0%B8%D0%BE%D0%BD%D0%BD%D1%8B%D0%B5%20%D0%A1%D0%A3%D0%91%D0%94.%20%D0%92%D0%B2%D0%B5%D0%B4%D0%B5%D0%BD%D0%B8%D0%B5%20%D0%B2%20PostgreSQL.assets/image-20210809141037055.png)
  - ![image-20210809141254558](2%20-%20SQL%20%D0%B8%20%D1%80%D0%B5%D0%BB%D1%8F%D1%86%D0%B8%D0%BE%D0%BD%D0%BD%D1%8B%D0%B5%20%D0%A1%D0%A3%D0%91%D0%94.%20%D0%92%D0%B2%D0%B5%D0%B4%D0%B5%D0%BD%D0%B8%D0%B5%20%D0%B2%20PostgreSQL.assets/image-20210809141254558.png)
- далее создать инстанс виртуальной машины Compute Engine с дефолтными параметрами
  - ![image-20210809135359397](2%20-%20SQL%20%D0%B8%20%D1%80%D0%B5%D0%BB%D1%8F%D1%86%D0%B8%D0%BE%D0%BD%D0%BD%D1%8B%D0%B5%20%D0%A1%D0%A3%D0%91%D0%94.%20%D0%92%D0%B2%D0%B5%D0%B4%D0%B5%D0%BD%D0%B8%D0%B5%20%D0%B2%20PostgreSQL.assets/image-20210809135359397.png)
  - ![image-20210809140010832](2%20-%20SQL%20%D0%B8%20%D1%80%D0%B5%D0%BB%D1%8F%D1%86%D0%B8%D0%BE%D0%BD%D0%BD%D1%8B%D0%B5%20%D0%A1%D0%A3%D0%91%D0%94.%20%D0%92%D0%B2%D0%B5%D0%B4%D0%B5%D0%BD%D0%B8%D0%B5%20%D0%B2%20PostgreSQL.assets/image-20210809140010832.png)
  - ![image-20210809135946598](2%20-%20SQL%20%D0%B8%20%D1%80%D0%B5%D0%BB%D1%8F%D1%86%D0%B8%D0%BE%D0%BD%D0%BD%D1%8B%D0%B5%20%D0%A1%D0%A3%D0%91%D0%94.%20%D0%92%D0%B2%D0%B5%D0%B4%D0%B5%D0%BD%D0%B8%D0%B5%20%D0%B2%20PostgreSQL.assets/image-20210809135946598.png)
  - ![image-20210809140140612](2%20-%20SQL%20%D0%B8%20%D1%80%D0%B5%D0%BB%D1%8F%D1%86%D0%B8%D0%BE%D0%BD%D0%BD%D1%8B%D0%B5%20%D0%A1%D0%A3%D0%91%D0%94.%20%D0%92%D0%B2%D0%B5%D0%B4%D0%B5%D0%BD%D0%B8%D0%B5%20%D0%B2%20PostgreSQL.assets/image-20210809140140612.png)
- добавить свой ssh ключ в GCE metadata
  - ![image-20210809144413154](2%20-%20SQL%20%D0%B8%20%D1%80%D0%B5%D0%BB%D1%8F%D1%86%D0%B8%D0%BE%D0%BD%D0%BD%D1%8B%D0%B5%20%D0%A1%D0%A3%D0%91%D0%94.%20%D0%92%D0%B2%D0%B5%D0%B4%D0%B5%D0%BD%D0%B8%D0%B5%20%D0%B2%20PostgreSQL.assets/image-20210809144413154.png)
  - ![image-20210809145334319](2%20-%20SQL%20%D0%B8%20%D1%80%D0%B5%D0%BB%D1%8F%D1%86%D0%B8%D0%BE%D0%BD%D0%BD%D1%8B%D0%B5%20%D0%A1%D0%A3%D0%91%D0%94.%20%D0%92%D0%B2%D0%B5%D0%B4%D0%B5%D0%BD%D0%B8%D0%B5%20%D0%B2%20PostgreSQL.assets/image-20210809145334319.png)
  - ![image-20210809145852745](2%20-%20SQL%20%D0%B8%20%D1%80%D0%B5%D0%BB%D1%8F%D1%86%D0%B8%D0%BE%D0%BD%D0%BD%D1%8B%D0%B5%20%D0%A1%D0%A3%D0%91%D0%94.%20%D0%92%D0%B2%D0%B5%D0%B4%D0%B5%D0%BD%D0%B8%D0%B5%20%D0%B2%20PostgreSQL.assets/image-20210809145852745.png)
- зайти удаленным ssh (первая сессия), не забывайте про ssh-add
  - ![image-20210809161717735](2%20-%20SQL%20%D0%B8%20%D1%80%D0%B5%D0%BB%D1%8F%D1%86%D0%B8%D0%BE%D0%BD%D0%BD%D1%8B%D0%B5%20%D0%A1%D0%A3%D0%91%D0%94.%20%D0%92%D0%B2%D0%B5%D0%B4%D0%B5%D0%BD%D0%B8%D0%B5%20%D0%B2%20PostgreSQL.assets/image-20210809161717735.png)
- поставить PostgreSQL
  - ![image-20210809163515254](2%20-%20SQL%20%D0%B8%20%D1%80%D0%B5%D0%BB%D1%8F%D1%86%D0%B8%D0%BE%D0%BD%D0%BD%D1%8B%D0%B5%20%D0%A1%D0%A3%D0%91%D0%94.%20%D0%92%D0%B2%D0%B5%D0%B4%D0%B5%D0%BD%D0%B8%D0%B5%20%D0%B2%20PostgreSQL.assets/image-20210809163515254.png)
  - ![image-20210809164052009](2%20-%20SQL%20%D0%B8%20%D1%80%D0%B5%D0%BB%D1%8F%D1%86%D0%B8%D0%BE%D0%BD%D0%BD%D1%8B%D0%B5%20%D0%A1%D0%A3%D0%91%D0%94.%20%D0%92%D0%B2%D0%B5%D0%B4%D0%B5%D0%BD%D0%B8%D0%B5%20%D0%B2%20PostgreSQL.assets/image-20210809164052009.png)
- зайти вторым ssh (вторая сессия)
  - ![image-20210809164248047](2%20-%20SQL%20%D0%B8%20%D1%80%D0%B5%D0%BB%D1%8F%D1%86%D0%B8%D0%BE%D0%BD%D0%BD%D1%8B%D0%B5%20%D0%A1%D0%A3%D0%91%D0%94.%20%D0%92%D0%B2%D0%B5%D0%B4%D0%B5%D0%BD%D0%B8%D0%B5%20%D0%B2%20PostgreSQL.assets/image-20210809164248047.png)
- запустить везде psql из под пользователя postgres
  - ![image-20210809165826495](2%20-%20SQL%20%D0%B8%20%D1%80%D0%B5%D0%BB%D1%8F%D1%86%D0%B8%D0%BE%D0%BD%D0%BD%D1%8B%D0%B5%20%D0%A1%D0%A3%D0%91%D0%94.%20%D0%92%D0%B2%D0%B5%D0%B4%D0%B5%D0%BD%D0%B8%D0%B5%20%D0%B2%20PostgreSQL.assets/image-20210809165826495.png)
- выключить auto commit
  - ![image-20210809171300004](2%20-%20SQL%20%D0%B8%20%D1%80%D0%B5%D0%BB%D1%8F%D1%86%D0%B8%D0%BE%D0%BD%D0%BD%D1%8B%D0%B5%20%D0%A1%D0%A3%D0%91%D0%94.%20%D0%92%D0%B2%D0%B5%D0%B4%D0%B5%D0%BD%D0%B8%D0%B5%20%D0%B2%20PostgreSQL.assets/image-20210809171300004-16285147837051.png)
- сделать в первой сессии новую таблицу и наполнить ее данными create table persons(id serial, first_name text, second_name text); insert into persons(first_name, second_name) values('ivan', 'ivanov'); insert into persons(first_name, second_name) values('petr', 'petrov'); commit;
  - ![image-20210809173150738](2%20-%20SQL%20%D0%B8%20%D1%80%D0%B5%D0%BB%D1%8F%D1%86%D0%B8%D0%BE%D0%BD%D0%BD%D1%8B%D0%B5%20%D0%A1%D0%A3%D0%91%D0%94.%20%D0%92%D0%B2%D0%B5%D0%B4%D0%B5%D0%BD%D0%B8%D0%B5%20%D0%B2%20PostgreSQL.assets/image-20210809173150738.png)
  - ![image-20210809173206466](2%20-%20SQL%20%D0%B8%20%D1%80%D0%B5%D0%BB%D1%8F%D1%86%D0%B8%D0%BE%D0%BD%D0%BD%D1%8B%D0%B5%20%D0%A1%D0%A3%D0%91%D0%94.%20%D0%92%D0%B2%D0%B5%D0%B4%D0%B5%D0%BD%D0%B8%D0%B5%20%D0%B2%20PostgreSQL.assets/image-20210809173206466.png)
  - ![image-20210809173216962](2%20-%20SQL%20%D0%B8%20%D1%80%D0%B5%D0%BB%D1%8F%D1%86%D0%B8%D0%BE%D0%BD%D0%BD%D1%8B%D0%B5%20%D0%A1%D0%A3%D0%91%D0%94.%20%D0%92%D0%B2%D0%B5%D0%B4%D0%B5%D0%BD%D0%B8%D0%B5%20%D0%B2%20PostgreSQL.assets/image-20210809173216962.png)
  - 
- посмотреть текущий уровень изоляции: show transaction isolation level
  - ![image-20210809173244778](2%20-%20SQL%20%D0%B8%20%D1%80%D0%B5%D0%BB%D1%8F%D1%86%D0%B8%D0%BE%D0%BD%D0%BD%D1%8B%D0%B5%20%D0%A1%D0%A3%D0%91%D0%94.%20%D0%92%D0%B2%D0%B5%D0%B4%D0%B5%D0%BD%D0%B8%D0%B5%20%D0%B2%20PostgreSQL.assets/image-20210809173244778.png)
- начать новую транзакцию в обоих сессиях с дефолтным (не меняя) уровнем изоляции
  - ![image-20210809173706555](2%20-%20SQL%20%D0%B8%20%D1%80%D0%B5%D0%BB%D1%8F%D1%86%D0%B8%D0%BE%D0%BD%D0%BD%D1%8B%D0%B5%20%D0%A1%D0%A3%D0%91%D0%94.%20%D0%92%D0%B2%D0%B5%D0%B4%D0%B5%D0%BD%D0%B8%D0%B5%20%D0%B2%20PostgreSQL.assets/image-20210809173706555.png)
- в первой сессии добавить новую запись insert into persons(first_name, second_name) values('sergey', 'sergeev');
  - ![image-20210809173721678](2%20-%20SQL%20%D0%B8%20%D1%80%D0%B5%D0%BB%D1%8F%D1%86%D0%B8%D0%BE%D0%BD%D0%BD%D1%8B%D0%B5%20%D0%A1%D0%A3%D0%91%D0%94.%20%D0%92%D0%B2%D0%B5%D0%B4%D0%B5%D0%BD%D0%B8%D0%B5%20%D0%B2%20PostgreSQL.assets/image-20210809173721678.png)
- сделать select * from persons во второй сессии
  - ![image-20210809173651146](2%20-%20SQL%20%D0%B8%20%D1%80%D0%B5%D0%BB%D1%8F%D1%86%D0%B8%D0%BE%D0%BD%D0%BD%D1%8B%D0%B5%20%D0%A1%D0%A3%D0%91%D0%94.%20%D0%92%D0%B2%D0%B5%D0%B4%D0%B5%D0%BD%D0%B8%D0%B5%20%D0%B2%20PostgreSQL.assets/image-20210809173651146.png)
- видите ли вы новую запись и если да то почему?
  - Не вижу так как транзакция еще не завершена.
- завершить первую транзакцию - commit;
  - ![image-20210809173814643](2%20-%20SQL%20%D0%B8%20%D1%80%D0%B5%D0%BB%D1%8F%D1%86%D0%B8%D0%BE%D0%BD%D0%BD%D1%8B%D0%B5%20%D0%A1%D0%A3%D0%91%D0%94.%20%D0%92%D0%B2%D0%B5%D0%B4%D0%B5%D0%BD%D0%B8%D0%B5%20%D0%B2%20PostgreSQL.assets/image-20210809173814643.png)
- сделать select * from persons во второй сессии
  - ![image-20210809173833794](2%20-%20SQL%20%D0%B8%20%D1%80%D0%B5%D0%BB%D1%8F%D1%86%D0%B8%D0%BE%D0%BD%D0%BD%D1%8B%D0%B5%20%D0%A1%D0%A3%D0%91%D0%94.%20%D0%92%D0%B2%D0%B5%D0%B4%D0%B5%D0%BD%D0%B8%D0%B5%20%D0%B2%20PostgreSQL.assets/image-20210809173833794.png)
- видите ли вы новую запись и если да то почему?
  - Вижу, потому что транзакция успешно завершенна и уровень изоляции составил read committed
- завершите транзакцию во второй сессии
  - ![image-20210809174029842](2%20-%20SQL%20%D0%B8%20%D1%80%D0%B5%D0%BB%D1%8F%D1%86%D0%B8%D0%BE%D0%BD%D0%BD%D1%8B%D0%B5%20%D0%A1%D0%A3%D0%91%D0%94.%20%D0%92%D0%B2%D0%B5%D0%B4%D0%B5%D0%BD%D0%B8%D0%B5%20%D0%B2%20PostgreSQL.assets/image-20210809174029842.png)
- начать новые но уже repeatable read транзации - set transaction isolation level repeatable read;
  - ![image-20210809174744753](2%20-%20SQL%20%D0%B8%20%D1%80%D0%B5%D0%BB%D1%8F%D1%86%D0%B8%D0%BE%D0%BD%D0%BD%D1%8B%D0%B5%20%D0%A1%D0%A3%D0%91%D0%94.%20%D0%92%D0%B2%D0%B5%D0%B4%D0%B5%D0%BD%D0%B8%D0%B5%20%D0%B2%20PostgreSQL.assets/image-20210809174744753.png)
- в первой сессии добавить новую запись insert into persons(first_name, second_name) values('sveta', 'svetova');
  - ![image-20210809174933814](2%20-%20SQL%20%D0%B8%20%D1%80%D0%B5%D0%BB%D1%8F%D1%86%D0%B8%D0%BE%D0%BD%D0%BD%D1%8B%D0%B5%20%D0%A1%D0%A3%D0%91%D0%94.%20%D0%92%D0%B2%D0%B5%D0%B4%D0%B5%D0%BD%D0%B8%D0%B5%20%D0%B2%20PostgreSQL.assets/image-20210809174933814.png)
- сделать select * from persons во второй сессии
  - ![image-20210809174937198](2%20-%20SQL%20%D0%B8%20%D1%80%D0%B5%D0%BB%D1%8F%D1%86%D0%B8%D0%BE%D0%BD%D0%BD%D1%8B%D0%B5%20%D0%A1%D0%A3%D0%91%D0%94.%20%D0%92%D0%B2%D0%B5%D0%B4%D0%B5%D0%BD%D0%B8%D0%B5%20%D0%B2%20PostgreSQL.assets/image-20210809174937198.png)
- видите ли вы новую запись и если да то почему?
  - Не вижу так как транзакция не завершена
- завершить первую транзакцию - commit;
  - ![image-20210809175015611](2%20-%20SQL%20%D0%B8%20%D1%80%D0%B5%D0%BB%D1%8F%D1%86%D0%B8%D0%BE%D0%BD%D0%BD%D1%8B%D0%B5%20%D0%A1%D0%A3%D0%91%D0%94.%20%D0%92%D0%B2%D0%B5%D0%B4%D0%B5%D0%BD%D0%B8%D0%B5%20%D0%B2%20PostgreSQL.assets/image-20210809175015611.png)
- сделать select * from persons во второй сессии
  - ![image-20210809180441007](2%20-%20SQL%20%D0%B8%20%D1%80%D0%B5%D0%BB%D1%8F%D1%86%D0%B8%D0%BE%D0%BD%D0%BD%D1%8B%D0%B5%20%D0%A1%D0%A3%D0%91%D0%94.%20%D0%92%D0%B2%D0%B5%D0%B4%D0%B5%D0%BD%D0%B8%D0%B5%20%D0%B2%20PostgreSQL.assets/image-20210809180441007.png)
- видите ли вы новую запись и если да то почему?
  - Не вижу, хоть первая транзакция была завершена, вторая находится в изоляции так как используется transaction isolation level repeatable read 
- завершить вторую транзакцию
  - ![image-20210809175201364](2%20-%20SQL%20%D0%B8%20%D1%80%D0%B5%D0%BB%D1%8F%D1%86%D0%B8%D0%BE%D0%BD%D0%BD%D1%8B%D0%B5%20%D0%A1%D0%A3%D0%91%D0%94.%20%D0%92%D0%B2%D0%B5%D0%B4%D0%B5%D0%BD%D0%B8%D0%B5%20%D0%B2%20PostgreSQL.assets/image-20210809175201364.png)
- сделать select * from persons во второй сессии
  - ![image-20210809175143689](2%20-%20SQL%20%D0%B8%20%D1%80%D0%B5%D0%BB%D1%8F%D1%86%D0%B8%D0%BE%D0%BD%D0%BD%D1%8B%D0%B5%20%D0%A1%D0%A3%D0%91%D0%94.%20%D0%92%D0%B2%D0%B5%D0%B4%D0%B5%D0%BD%D0%B8%D0%B5%20%D0%B2%20PostgreSQL.assets/image-20210809175143689.png)
- видите ли вы новую запись и если да то почему?
  - Вижу так как вторая транзакция была завершена и теперь изменения доступны для второый сессии
- остановите виртуальную машину но не удаляйте ее



---

p.s. При первой попытки я не совсем правильно исполнил задания и работал вне транзакции. Поэтому на скринах и в бд присутсвуют 
  5 | sergey1    | sergeev1
  6 | sveta_1    | svetova_1

![image-20210809180952703](2%20-%20SQL%20%D0%B8%20%D1%80%D0%B5%D0%BB%D1%8F%D1%86%D0%B8%D0%BE%D0%BD%D0%BD%D1%8B%D0%B5%20%D0%A1%D0%A3%D0%91%D0%94.%20%D0%92%D0%B2%D0%B5%D0%B4%D0%B5%D0%BD%D0%B8%D0%B5%20%D0%B2%20PostgreSQL.assets/image-20210809180952703.png)

p.s.s. работал в DATABASE iso;