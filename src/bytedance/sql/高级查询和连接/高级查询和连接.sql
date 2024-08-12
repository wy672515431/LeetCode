-- 连续空余座位
Select distinct a.seat_id
from Cinema a
         inner join Cinema b on abs(a.seat_id - b.seat_id) = 1 and a.free = true and b.free = true
order by a.seat_id ASC;

-- 每个产品在不同商店的价格
Select product_id, 'store1' as store, store1 as price
from Products
where store1 is not null
union
Select product_id, 'store2' as store, store2 as price
from Products
where store2 is not null
union
Select product_id, 'store3' as store, store3 as price
from Products
where store3 is not null;

-- 直线上的最短距离
Select MIN(ABS(p1.x - p2.x)) as shortest
From Point p1,
     Point p2
where p1.x != p2.x

-- 丢失信心的演员
-- left join 求出Employees表中不在Salaries表中的employee_id， right join 求出Salaries表中不在Employees表中的employee_id
-- union 和 union all 的区别：union会去重，union all不会去重
Select Employees.employee_id
from Employees
         left join Salaries on Employees.employee_id = Salaries.employee_id
where Salaries.employee_id is null
union
Select Salaries.employee_id
from Employees
         right join Salaries on Employees.employee_id = Salaries.employee_id
where Employees.employee_id is null
order by employee_id;

-- 页面推荐
-- 第一步获得用户一的所有好友
select user2_id as user_id
from Friendship
where user1_id = 1
union
select user1_id as user_id
from Friendship
where user2_id = 1

-- 用 case when 来代替
select (
           case
               when user1_id = 1 then user2_id
               when user2_id = 1 then user1_id
               end
           ) as user_id
from Friendship
WHERE user1_id = 1
   OR user2_id = 1

-- 获得所有page
select distinct page_id as recommended_page
from Likes
where user_id in (select user2_id as user_id
                  from Friendship
                  where user1_id = 1
                  union
                  select user1_id as user_id
                  from Friendship
                  where user2_id = 1)
  and page_id not in (select page_id
                      from Likes
                      where user_id = 1)

-- 树节点
select id,
       IF(p_id is NULL, 'Root',
          IF(id not in (select distinct p_id from Tree where p_id is not null), 'Leaf', 'Inner')) as type
from Tree;

-- 游戏玩法分析
select a1.player_id, a1.event_date, SUM(a2.games_played) as games_played_so_far
from Activity a1
         inner join Activity a2 on a1.player_id = a2.player_id and a1.event_date >= a2.event_date
group by a1.player_id, a1.event_date

-- 大满贯数量
select player_id,
       player_name,
       SUM(Wimbledon = player_id) + SUM(Fr_open = player_id) + SUM(US_open = player_id) + SUM(Au_open = player_id) as grand_slams_count
from Players,
     Championships
group by player_id
having grand_slams_count > 0


-- 应该被禁止的Leetflex账户
select distinct a.account_id
from LogInfo a
         inner join LogInfo b on a.account_id = b.account_id and a.ip_address != b.ip_address
where not (a.logout < b.login or a.login > b.logout)