-- 使用IF ELSE语句
-- IF(condition, value1, value2)：如果condition为真，则返回value1，否则返回value2
Select employee_id, IF(employee_id % 2 = 1 and name not like 'M%', salary, 0) as 'bonus'
    from Employees order by employee_id;