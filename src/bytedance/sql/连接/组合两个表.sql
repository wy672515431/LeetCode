-- sql中的join
-- left join: 左连接，左表中的所有记录都会被选中，右表中的记录只会被选中一次
-- right join: 右连接，右表中的所有记录都会被选中，左表中的记录只会被选中一次
-- inner join: 内连接，只有左表和右表中的记录都能匹配的时候才会被选中
-- full join: 全连接，左表和右表中的所有记录都会被选中

Select firstName, lastName, city, state from Person p left join Address a on a.personId = p.personId;

-- 没有卖出的卖家
Select seller_name from Seller where seller_id not in (Select seller_id from Orders where year(sale_date) = 2020)
order by seller_name ASC;

Select seller_name from Seller s left join Orders o on s.seller_id = o.seller_id and year(o.sale_date) = 2020
where o.seller_id is null order by s.seller_name ASC;

-- 排名靠前的旅行者
Select name, IF(SUM(distance) is Null, 0, SUM(distance)) as travelled_distance from Users u left join Rides r on u.id = r.user_id group by u.id
order by travelled_distance DESC, name ASC;

-- 销售员
-- 三个表做连接
Select name from Salesperson where sales_id not in (Select s.sales_id from SalesPerson s left join Orders o on s.sales_id = o.sales_id left join Company c on o.com_id = c.com_id where c.name = 'RED')

-- 计算布尔表达式的值
Select e.*, (
    case
        when e.operator = '>' then IF(v1.value > v2.value, 'true', 'false')
        when e.operator = '<' then IF(v1.value < v2.value, 'true', 'false')
        when e.operator = '=' then IF(v1.value = v2.value, 'true', 'false')
        else 'false'
    end
    ) as 'value' from Expressions e left join Variables v1 on e.left_operand = v1.name left join Variables v2 on e.right_operand = v2.name;

-- 查询球队积分
Select t.team_id, t.team_name, SUM((
        case
            when m.host_team = t.team_id and m.host_goals > m.guest_goals then 3
            when m.guest_team = t.team_id and m.guest_goals > m.host_goals then 3
            when m.host_goals = m.guest_goals then 1
            else 0
        end
    )) as 'num_points' from Teams t left join Matches m on t.team_id = m.host_team or t.team_id = m.guest_team
group by t.team_id order by num_points DESC, t.team_id ASC;