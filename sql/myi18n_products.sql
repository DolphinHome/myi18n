create table products
(
    pid         int auto_increment,
    pname       varchar(50) null,
    price       double      null,
    flag        varchar(2)  null,
    category_id int         null,
    constraint products_pid_uindex
        unique (pid)
);

create index products_fk
    on products (category_id);

alter table products
    add primary key (pid);

INSERT INTO myi18n.products (pid, pname, price, flag, category_id) VALUES (1, '${products.11111}', 5000, '1', 1);
INSERT INTO myi18n.products (pid, pname, price, flag, category_id) VALUES (2, '${products.22222}', 3000, '1', 1);
INSERT INTO myi18n.products (pid, pname, price, flag, category_id) VALUES (3, '${products.33333}', 7000, '1', 1);
INSERT INTO myi18n.products (pid, pname, price, flag, category_id) VALUES (4, '${products.44444}', 800, '1', 2);
INSERT INTO myi18n.products (pid, pname, price, flag, category_id) VALUES (5, '${products.55555}', 200, '1', 2);
INSERT INTO myi18n.products (pid, pname, price, flag, category_id) VALUES (6, '${products.66666}', 450, '1', 2);
INSERT INTO myi18n.products (pid, pname, price, flag, category_id) VALUES (7, '${products.77777}', 700, '1', 2);
INSERT INTO myi18n.products (pid, pname, price, flag, category_id) VALUES (8, '${products.88888}', 2680, '1', 3);
INSERT INTO myi18n.products (pid, pname, price, flag, category_id) VALUES (9, '${products.99999}', 590, '1', 3);
INSERT INTO myi18n.products (pid, pname, price, flag, category_id) VALUES (10, '锅碗瓢盆', 590, '1', 3);