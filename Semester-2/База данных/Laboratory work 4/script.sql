-- 1 запрос без индексов
EXPLAIN (ANALYZE) SELECT Н_ЛЮДИ.ИД AS "id человека", Н_СЕССИЯ.УЧГОД AS "учебный год"
FROM Н_ЛЮДИ
RIGHT JOIN Н_СЕССИЯ ON Н_ЛЮДИ.ИД = Н_СЕССИЯ.ЧЛВК_ИД
WHERE Н_ЛЮДИ.ИМЯ = 'Александр'
AND Н_СЕССИЯ.ЧЛВК_ИД > 105948
AND Н_СЕССИЯ.ЧЛВК_ИД = 100012;

-- 2 запрос без индексов
EXPLAIN ANALYZE SELECT
Н_ЛЮДИ.ОТЧЕСТВО AS "отчество человека",
Н_ОБУЧЕНИЯ.ЧЛВК_ИД AS "id человека",
Н_УЧЕНИКИ.ГРУППА AS "группа студента"
FROM Н_ЛЮДИ
INNER JOIN Н_ОБУЧЕНИЯ ON Н_ЛЮДИ.ИД = Н_ОБУЧЕНИЯ.ЧЛВК_ИД
INNER JOIN Н_УЧЕНИКИ ON Н_УЧЕНИКИ.ЧЛВК_ИД = Н_ОБУЧЕНИЯ.ЧЛВК_ИД
WHERE Н_ЛЮДИ.ИД < 142095
AND Н_ОБУЧЕНИЯ.ЧЛВК_ИД < 163276;
