
create table if not exists users
(
    id       bigint auto_increment
        primary key,
    name     varchar(255) not null,
    email    varchar(255) not null,
    password varchar(255) not null
);

create table if not exists addresses
(
    id      bigint auto_increment
        primary key,
    street  varchar(255) not null,
    city    varchar(255) not null,
    zip     varchar(255)  null,
    user_id bigint      not null,
    state varchar(255) not null,
    constraint adresses_users_id_fk
        foreign key (user_id) references users (id)
);

create table if not exists profiles
(
    id              bigint primary key ,
    bio             text null,
    phone_number    varchar(255) null,
    date_of_birth   date null,
    loyality_points int unsigned default 0,
    foreign key (id) references users(id)

);

create table if not exists categories(
                                         id tinyint PRIMARY KEY auto_increment ,
                                         name varchar(255) not null
);

create table if not exists products(
                                       id bigint PRIMARY KEY auto_increment ,
                                       name varchar(255) not null,
                                       price decimal(10,2) not null,
                                        description TEXT null ,
                                       category_id tinyint not null,
                                       Constraint fk_category
                                           FOREIGN KEY (category_id) references categories (id)
                                               ON DELETE RESTRICT
);

CREATE TABLE wishlist
(
    product_id BIGINT NOT NULL,
    user_id    BIGINT NOT NULL,
    CONSTRAINT pk_wishlist PRIMARY KEY (product_id, user_id),
    CONSTRAINT fk_wishlist_on_product FOREIGN KEY (product_id) REFERENCES products (id) on delete cascade ,
    CONSTRAINT fk_wishlist_on_user FOREIGN KEY (user_id) REFERENCES users (id)
);


DELIMITER //

CREATE PROCEDURE findProductsByPrice (
    min DECIMAL(10,2),
    max DECIMAL(10,2)
)

BEGIN
    select id, name, price, category_id
    from products
    where price between min and max
    order by name;
end //

DELIMITER ;