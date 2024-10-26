# База данных | Программная инженерия

### Лабораторные
[1 - Инфологическая и даталогическая модели, их реализация в PostgreSQL](https://github.com/karillisa/Databases/tree/main/Laboratory%20work%201)

[2 - Запросы к базе данных "Учебный процесс"](https://github.com/karillisa/Databases/tree/main/Laboratory%20work%202)

[3 - Функциональные зависимости и NF](https://github.com/karillisa/Databases/tree/main/Laboratory%20work%203)

[4 - Планы выполнения запросов](https://github.com/karillisa/Databases/tree/main/Laboratory%20work%204)


## Как работать с БД на Helios?

Давайте разберёмся как работать на helios:

1. Для начала создайте новый файл, у меня `script.sql` и наполните его каким-нибудь содержимым (или создайте новый файл прямо на гелиосе, переходите сразу к пункту 3). Например:
```sql
CREATE TABLE test (
    id BIGSERIAL PRIMARY KEY,
    description TEXT NOT NULL UNIQUE
);

INSERT INTO test (description) VALUES
    ('test1'),
    ('test2');

SELECT * FROM test;
```
Закиньте файл с запросом на Helios, находясь в директории, где он лежит
```
scp -P 2222 test.sql sXXXXXX@helios.cs.ifmo.ru:~/
```
2. Поключитель к Helios
```
ssh sXXXXXX@helios.cs.ifmo.ru -p 2222
```
3. Создайте файл с запросом (если вы решили не выполнять 1-2 пункты, иначе пропустите этот пункт)
- Создайте новый файл:
```
touch test.sql
```
- Откройте редактор:
```
vim script.sql
```
- Впишите сюда содержимое запроса, например, из 1 пункта (чтобы начать писать в виме, нажмите клавишу `i`)
- Чтобы сохранить файл и выйти из редактора, надо нажать `Esc` и набрать `:wq` (иногда требуется поставить ! в конце), `Enter`. Если вы хотите выйти без сохранения наберите `:q!`
- Если понадобится вставить скопированный текст в vim-е, то нужно нажать клавиши `Shift + Ins`
  
5. Пропишите
```
psql -h pg -d studs
```
или 
```
psql -h pg -d ucheb
```
Смотря какая лаба

5. Основные команды:
- `\i script.sql` - выполнить запросы из файла
- `\d` - посмотреть все существующие таблицы и тд.
- `\h` - посмотреть справку по SQL операторам
- `\?` - посмотреть справку по другим командам
- `\q` - выйти из psql
