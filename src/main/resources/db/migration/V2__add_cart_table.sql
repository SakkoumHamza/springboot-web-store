create table carts
(
    id binary(16) default (uuid_to_bin(uuid())) PRIMARY KEY not null,
    date_created date default (current_date) not null
);

create table cart_items(
    id bigint auto_increment primary key ,
    cart_id binary(16) not null unique ,
    product_id bigint not null,
    quantity int default 1 not null,
    CONSTRAINT FOREIGN KEY cart_items_product_id_fk (product_id) references products (id) on delete cascade,
    CONSTRAINT FOREIGN KEY cart_items_cart_id_fk (cart_id) references carts (id) on delete cascade
);