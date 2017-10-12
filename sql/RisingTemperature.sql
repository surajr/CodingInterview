# Write your MySQL query statement below
SELECT cur.id as Id
FROM Weather cur, Weather pre
WHERE cur.temperature > pre.temperature 
AND TO_DAYS(cur.date) - TO_DAYS(pre.date) = 1;

