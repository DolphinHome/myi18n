create table t_i18n_allocate
(
    pid    int auto_increment,
    type   varchar(30) charset utf8  not null comment '类型',
    module varchar(30) charset utf8  null comment '模块',
    label  varchar(100) charset utf8 not null comment '标签',
    langs  json                      not null comment '国际化json',
    to_web int default 0             not null comment '0为后端，1为前端',
    constraint t_i18n_allocate_pid_uindex
        unique (pid)
)
    comment '国际化配置表';

alter table t_i18n_allocate
    add primary key (pid);

INSERT INTO myi18n.t_i18n_allocate (pid, type, module, label, langs, to_web) VALUES (1, 'Web', null, 'home', '{"CN": "家", "US": "home"}', 1);
INSERT INTO myi18n.t_i18n_allocate (pid, type, module, label, langs, to_web) VALUES (2, 'Web', null, 'hello', '{"CN": "你好", "US": "hello"}', 1);
INSERT INTO myi18n.t_i18n_allocate (pid, type, module, label, langs, to_web) VALUES (3, 'Java', 'category', '99999', '{"CN": "旅行用品", "US": "travel accessories"}', 0);
INSERT INTO myi18n.t_i18n_allocate (pid, type, module, label, langs, to_web) VALUES (4, 'Java', 'category', '88888', '{"CN": "办公用品", "US": "office supplies"}', 0);
INSERT INTO myi18n.t_i18n_allocate (pid, type, module, label, langs, to_web) VALUES (5, 'Java', 'category', '77777', '{"CN": "生活用品", "US": "articles of daily use"}', 0);
INSERT INTO myi18n.t_i18n_allocate (pid, type, module, label, langs, to_web) VALUES (6, 'Java', 'products', '11111', '{"CN": "牙刷", "US": "water glass"}', 0);
INSERT INTO myi18n.t_i18n_allocate (pid, type, module, label, langs, to_web) VALUES (7, 'Java', 'products', '22222', '{"CN": "水杯", "US": "articles of daily use"}', 0);
INSERT INTO myi18n.t_i18n_allocate (pid, type, module, label, langs, to_web) VALUES (8, 'Java', 'products', '33333', '{"CN": "拖鞋", "US": "slipper"}', 0);
INSERT INTO myi18n.t_i18n_allocate (pid, type, module, label, langs, to_web) VALUES (9, 'Java', 'products', '44444', '{"CN": "笔记本", "US": "jotter"}', 0);
INSERT INTO myi18n.t_i18n_allocate (pid, type, module, label, langs, to_web) VALUES (10, 'Java', 'products', '55555', '{"CN": "笔", "US": "pen"}', 0);
INSERT INTO myi18n.t_i18n_allocate (pid, type, module, label, langs, to_web) VALUES (11, 'Java', 'products', '66666', '{"CN": "办公桌", "US": "desk"}', 0);
INSERT INTO myi18n.t_i18n_allocate (pid, type, module, label, langs, to_web) VALUES (12, 'Java', 'products', '77777', '{"CN": "显示器", "US": "display"}', 0);
INSERT INTO myi18n.t_i18n_allocate (pid, type, module, label, langs, to_web) VALUES (13, 'Java', 'products', '88888', '{"CN": "被子", "US": "quilt"}', 0);
INSERT INTO myi18n.t_i18n_allocate (pid, type, module, label, langs, to_web) VALUES (14, 'Java', 'products', '99999', '{"CN": "一次性浴巾", "US": "Disposable bath towel"}', 0);
INSERT INTO myi18n.t_i18n_allocate (pid, type, module, label, langs, to_web) VALUES (15, 'Web', '', 'hi', '{"CN": "嗨", "US": "hi"}', 0);