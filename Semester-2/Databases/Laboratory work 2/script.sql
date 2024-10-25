-- 1
select Н_ЛЮДИ.ИД, Н_ВЕДОМОСТИ.ДАТА from Н_ЛЮДИ 
left join Н_ВЕДОМОСТИ ON (Н_ВЕДОМОСТИ.ЧЛВК_ИД = Н_ЛЮДИ.ИД)
where Н_ЛЮДИ.ИД = 152862 and Н_ВЕДОМОСТИ.ЧЛВК_ИД < 153285
and Н_ВЕДОМОСТИ.ЧЛВК_ИД > 117219;

-- 2
select Н_ЛЮДИ.ИМЯ, Н_ВЕДОМОСТИ.ДАТА, Н_СЕССИЯ.УЧГОД
from Н_ЛЮДИ left join Н_ВЕДОМОСТИ ON (Н_ВЕДОМОСТИ.ЧЛВК_ИД = Н_ЛЮДИ.ИД)
   left join Н_СЕССИЯ ON (Н_ЛЮДИ.ИД = Н_СЕССИЯ.ЧЛВК_ИД)
where Н_ЛЮДИ.ИД = 152862 and Н_ВЕДОМОСТИ.ИД > 39921;

-- 3
SELECT COUNT(*) AS cnt FROM Н_ЛЮДИ
LEFT JOIN Н_УЧЕНИКИ ON (Н_УЧЕНИКИ.ЧЛВК_ИД = Н_ЛЮДИ.ИД)
LEFT JOIN Н_ПЛАНЫ ON (Н_УЧЕНИКИ.ПЛАН_ИД = Н_ПЛАНЫ.ИД)
LEFT JOIN Н_ФОРМЫ_ОБУЧЕНИЯ ON (Н_ПЛАНЫ.ФО_ИД = Н_ФОРМЫ_ОБУЧЕНИЯ.ИД)
WHERE Н_ЛЮДИ.ИНН IS NULL AND Н_ФОРМЫ_ОБУЧЕНИЯ.НАИМЕНОВАНИЕ = 'Очно-заочная(вечерняя)';

-- 4 
select Н_ГРУППЫ_ПЛАНОВ.ПЛАН_ИД from Н_ГРУППЫ_ПЛАНОВ
   left join Н_ПЛАНЫ ON (Н_ГРУППЫ_ПЛАНОВ.ПЛАН_ИД = Н_ПЛАНЫ.ИД)
   JOIN Н_ФОРМЫ_ОБУЧЕНИЯ ON Н_ПЛАНЫ.ФО_ИД = Н_ФОРМЫ_ОБУЧЕНИЯ.ИД AND Н_ФОРМЫ_ОБУЧЕНИЯ.НАИМЕНОВАНИЕ = 'Очная'
       GROUP BY Н_ГРУППЫ_ПЛАНОВ.ПЛАН_ИД HAVING COUNT(*) > 2;

-- 5 

SELECT * FROM(
                SELECT Н_ЛЮДИ.ИД,
                       Н_ЛЮДИ.ФАМИЛИЯ,
                       Н_ЛЮДИ.ИМЯ,
                       Н_ЛЮДИ.ОТЧЕСТВО,
                       AVG(CAST("Н_ВЕДОМОСТИ"."ОЦЕНКА" AS FLOAT))
                           as avrg FROM
                    Н_ЛЮДИ
                        JOIN Н_ВЕДОМОСТИ
                            ON (Н_ЛЮДИ.ИД = Н_ВЕДОМОСТИ.ЧЛВК_ИД)
                        JOIN Н_УЧЕНИКИ
                            ON (Н_УЧЕНИКИ.ЧЛВК_ИД = Н_ВЕДОМОСТИ.ЧЛВК_ИД)
                WHERE Н_ВЕДОМОСТИ.ОЦЕНКА ~ '[0-5]'
                  AND Н_УЧЕНИКИ.ГРУППА = '4100'
                GROUP BY Н_ЛЮДИ.ИД, Н_ЛЮДИ.ФАМИЛИЯ, Н_ЛЮДИ.ИМЯ,
                         Н_ЛЮДИ.ОТЧЕСТВО
            ) AS tmp
WHERE avrg = (
   SELECT MIN(CAST(Н_ВЕДОМОСТИ.ОЦЕНКА AS FLOAT))
   FROM Н_УЧЕНИКИ
       JOIN Н_ВЕДОМОСТИ
           ON (Н_УЧЕНИКИ.ЧЛВК_ИД = Н_ВЕДОМОСТИ.ЧЛВК_ИД)
   WHERE Н_ВЕДОМОСТИ.ОЦЕНКА ~ '[0-5]'
     AND Н_УЧЕНИКИ.ГРУППА = '1100'
);

-- 6
SELECT Н_ЛЮДИ.ИД, Н_УЧЕНИКИ.ГРУППА, Н_ЛЮДИ.ФАМИЛИЯ, Н_ЛЮДИ.ИМЯ,
       Н_ЛЮДИ.ОТЧЕСТВО, Н_УЧЕНИКИ.КОНЕЦ_ПО_ПРИКАЗУ
FROM Н_УЧЕНИКИ
JOIN Н_ПЛАНЫ ON (Н_УЧЕНИКИ.ПЛАН_ИД = Н_ПЛАНЫ.ИД)
JOIN Н_НАПРАВЛЕНИЯ_СПЕЦИАЛ ON (Н_ПЛАНЫ.НАПС_ИД = Н_НАПРАВЛЕНИЯ_СПЕЦИАЛ.ИД)
JOIN Н_ЛЮДИ ON (Н_УЧЕНИКИ.ЧЛВК_ИД = Н_ЛЮДИ.ИД)
JOIN Н_НАПР_СПЕЦ ON (Н_НАПРАВЛЕНИЯ_СПЕЦИАЛ.НС_ИД = Н_НАПР_СПЕЦ.ИД)
JOIN Н_ФОРМЫ_ОБУЧЕНИЯ ON (Н_ПЛАНЫ.ФО_ИД = Н_ФОРМЫ_ОБУЧЕНИЯ.ИД AND Н_ФОРМЫ_ОБУЧЕНИЯ.НАИМЕНОВАНИЕ = 'Очная')
WHERE CAST(Н_УЧЕНИКИ.КОНЕЦ_ПО_ПРИКАЗУ as DATE) < '2012-09-01'
AND Н_НАПР_СПЕЦ.НАИМЕНОВАНИЕ = 'Программная инженерия';

-- 7 
select COUNT(*) from Н_ЛЮДИ join(
   select Н_ВЕДОМОСТИ.ЧЛВК_ИД
   from Н_ВЕДОМОСТИ
   WHERE Н_ВЕДОМОСТИ.ОЦЕНКА ~ '[0-5]'
   GROUP BY Н_ВЕДОМОСТИ.ЧЛВК_ИД
   HAVING AVG(CAST(Н_ВЕДОМОСТИ.ОЦЕНКА as FLOAT)) = 3
) as troe ON Н_ЛЮДИ.ИД = troe.ЧЛВК_ИД;	