# Write your MySQL query statement below
Select emp.name as Employee 
FROM Employee as emp, Employee as mgr
WHERE emp.managerId = mgr.Id AND emp.salary > mgr.salary;

