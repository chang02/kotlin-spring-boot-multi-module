create table if not exists `users`
(
    `id`         bigint(20) unsigned not null auto_increment,
    `username`   varchar(100)        not null,
    `email`      varchar(100)        not null,
    `created_at` datetime(6)         not null default current_timestamp(6),
    `updated_at` datetime(6)         not null default current_timestamp(6),
    primary key (`id`)
);