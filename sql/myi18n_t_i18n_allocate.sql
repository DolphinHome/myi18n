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

