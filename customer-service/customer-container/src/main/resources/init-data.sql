-- Commented because the customer must be created with rest API to use CQRS implementation
delete from customer.customers;

INSERT INTO customer.customers(id, username, first_name, last_name)
VALUES ('d215b5f8-0249-4dc5-89a3-51fd148cfb41', 'user_1', 'First', 'User');
--
INSERT INTO customer.customers(id, username, first_name, last_name)
VALUES ('d215b5f8-0249-4dc5-89a3-51fd148cfb42', 'user_2', 'Second', 'User');

INSERT INTO "order".customers(id, username, first_name, last_name)
VALUES ('d215b5f8-0249-4dc5-89a3-51fd148cfb43', 'user_3', 'Third', 'User');