INSERT INTO product (name)
VALUES ('Product 1'),
       ('Product 2'),
       ('Product 3');

INSERT INTO product_price (price, datetime, product_id)
VALUES (10, '2022-03-14', 1),
       (20, '2022-03-14', 2),
       (30, '2022-03-14', 3),
       (12, now(), 1),
       (22, now(), 2),
       (33, now(), 3);
