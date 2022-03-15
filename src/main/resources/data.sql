INSERT INTO product (id, name)
VALUES (1, 'Product 1'),
       (2, 'Product 2'),
       (3, 'Product 3');

INSERT INTO product_price (id, price, datetime, product_id)
VALUES (1, 10, '2022-03-14', 1),
       (2, 20, '2022-03-14', 2),
       (3, 30, '2022-03-14', 3),
       (4, 12, now(), 1),
       (5, 22, now(), 2),
       (6, 33, now(), 3);
