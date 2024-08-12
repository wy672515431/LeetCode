-- 银行账户概要II
Select u.name as name, SUM(t.amount) as balance
from Users u
         left join Transactions t on u.account = t.account
where balance > 10000
group by u.account
having balance > 10000

-- 查找重复的电子邮箱
Select email as Email
from Person
group by email
having count(email) > 1

-- 合作过至少三次的演员和导演
Select actor_id, director_id
from ActorDirector
group by actor_id, director_id
having COUNT(*) >= 3

-- 消费者下单频率
-- 超时
-- Select o.customer_id, c.name from Orders o left join Customers c on o.customer_id = c.customer_id
-- left join Product p on o.product_id = p.product_id
-- where year(o.order_date) = 2020 and month(o.order_date) = 6
-- group by o.customer_id
-- having SUM(o.quantity * p.price) >= 100
-- intersect
-- Select o.customer_id, c.name from Orders o left join Customers c on o.customer_id = c.customer_id
--                                            left join Product p on o.product_id = p.product_id
-- where year(o.order_date) = 2020 and month(o.order_date) = 7
-- group by o.customer_id
-- having SUM(o.quantity * p.price) >= 100

select distinct c.customer_id, c.name
from customers c
         left join Orders o
                   on o.customer_id = c.customer_id
         left join product p
                   on p.product_id = o.product_id
where year (o.order_date) = 2020 and month (o.order_date) in (6, 7)
group by c.customer_id, c.name
having SUM (IF(month (o.order_date) = 6
     , p.price * o.quantity
     , 0)) >= 100
   and SUM (IF(month (o.order_date) = 7
     , p.price * o.quantity
     , 0)) >= 100

-- 每天的领导和合伙人
Select date_id, make_name, count(distinct lead_id) as unique_leads, count(distinct partner_id) as unique_partners
from DailySales
group by date_id, make_name

-- 上月播放的儿童适宜电影
Select distinct c.title from Content c left join TVProgram t on c.content_id = t.content_id
where year(t.program_date) = 2020 and month(t.program_date) = 6 and c.Kids_content = 'Y' and c.content_type = 'Movies';

-- 可以放心投资的国家

-- 先求出全球平均通话时长
Select AVG(duration) from Calls
-- 进行笛卡尔积
SELECT c.name AS country
FROM Calls, Person, Country c
WHERE (caller_id = id OR callee_id = id) AND country_code = LEFT(phone_number, 3)
GROUP BY country_code
HAVING AVG(duration) > (SELECT AVG(duration) FROM Calls);

