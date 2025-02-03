-- 集合的三个操作: union, intersect, except
-- union all: 不去重
Select Customers.customer_id, customer_name from Customers
    where customer_id in (
           select customer_id from Orders where product_name = 'A'
           intersect
           select customer_id from Orders where product_name = 'B'
           except
           select customer_id from Orders where product_name = 'C'
        );


-- join
Select o.customer_id, c.customer_name from Orders o left join Customers c on o.customer_id = c.customer_id
    group by customer_id having IF(SUM(product_name = 'A') > 0, 1, 0) = 1 and IF(SUM(product_name = 'B') > 0, 1, 0) = 1
        and IF(SUM(product_name = 'C') > 0, 1, 0) = 0;