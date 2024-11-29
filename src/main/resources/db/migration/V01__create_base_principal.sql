create table orders (
    id                          varchar(36) primary key,
    external_id                 varchar(36) not null,
    status                      varchar(50) not null,
    amount                      decimal(10, 2),
    created_at                  timestamp with time zone,
    updated_at                  timestamp with time zone
);

create table order_item (
    id                          varchar(36) primary key,
    description                 varchar(10000) not null,
    payment_value               decimal(10, 2) not null,
    created_at                  timestamp with time zone,
    updated_at                  timestamp with time zone,
    order_id                    varchar(36) not null,
    foreign key (order_id)      references orders (id)
);
