-- MySQL 聚合函数
-- 聚合函数是对一组值进行计算并返回单个值的函数。常见的聚合函数有 COUNT、SUM、AVG、MAX、MIN 等。
-- 2020年最后一次登录
Select user_id, MAX(time_stamp) as last_stamp from Logins
where year(time_stamp) = 2020 group by user_id;

-- 游戏玩法分析I
Select player_id, MIN(event_date) as first_login from Activity
group by player_id;

-- 仓库经理
Select w.name as warehouse_name, SUM(w.units * p.Width * p.Length * p.Height) as volume
from WareHouse w
left join Products p on w.product_id = p.product_id
group by w.name;

-- 订单最多的客户, 通过count函数计算订单数量
Select customer_number from Orders
group by customer_number
order by COUNT(order_number) DESC limit 1;

-- 查找每个员工花费的总时间
Select event_day as day, emp_id, SUM(out_time - in_time) as total_time
From Employees group by emp_id, event_day;

-- 即时食物配送I
-- MySQL 保留两位小数 round(数值, 2)
Select ROUND( (Select count(*) from Delivery d1 where d1.order_date = d1.customer_pref_delivery_date) /
              (Select count(*) from Delivery) * 100, 2)  as immediate_percentage;

-- AVG用于计算数值列或计算结果为数值的表达式的平均值
-- order_date = customer_pref_delivery_date 为真时，返回1，否则返回0
-- AVG(order_date = customer_pref_delivery_date)
Select ROUND(100 * AVG(order_date = customer_pref_delivery_date), 2) as immediate_percentage
From Delivery;

-- 苹果和橘子
Select sale_date, SUM(
        case
            when fruit = 'apples' then sold_num
            when fruit = 'oranges' then -sold_num
            else 0
            end
                  ) as diff
From Sales
group by sale_date
order by sale_date;

-- 两人之间的通话次数
-- mysql 执行顺序： from -> where -> group by -> select -> order by
-- group by可以使用别名
Select (case when from_id < to_id then from_id else to_id end) as person1,
       (case when from_id > to_id then from_id else to_id end) as person2,
       count(*) as call_count,
       SUM(duration) as total_duration
From Calls
group by person1, person2
