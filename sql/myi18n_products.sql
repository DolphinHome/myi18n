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

INSERT INTO myi18n.products (pid, pname, price, flag, category_id) VALUES (1, '联想', 5000, '1', 1);
INSERT INTO myi18n.products (pid, pname, price, flag, category_id) VALUES (2, '海尔', 3000, '1', 1);
INSERT INTO myi18n.products (pid, pname, price, flag, category_id) VALUES (3, '雷神', 7000, '1', 1);
INSERT INTO myi18n.products (pid, pname, price, flag, category_id) VALUES (4, 'JACK_JONES', 800, '1', 2);
INSERT INTO myi18n.products (pid, pname, price, flag, category_id) VALUES (5, '真维斯', 200, '1', 2);
INSERT INTO myi18n.products (pid, pname, price, flag, category_id) VALUES (6, '花花公子', 450, '1', 2);
INSERT INTO myi18n.products (pid, pname, price, flag, category_id) VALUES (7, '劲霸', 700, '1', 2);
INSERT INTO myi18n.products (pid, pname, price, flag, category_id) VALUES (8, '香奈儿', 2680, '1', 3);
INSERT INTO myi18n.products (pid, pname, price, flag, category_id) VALUES (9, '相宜本草', 590, '1', 3);