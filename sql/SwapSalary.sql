# Write your MySQL query statement below
UPDATE salary
SET sex = (
    case when sex = 'm'
        then 'f'
        else 'm'    
    END);
