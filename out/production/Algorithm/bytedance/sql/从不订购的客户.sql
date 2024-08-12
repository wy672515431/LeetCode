Select name as 'Customers' from Customers where id not in (select customerId from Orders);

-- Left Join
Select name as 'Customers' from Customers LEFT JOIN Orders on Customers.id = Orders.customerId where Orders.customerId is null;