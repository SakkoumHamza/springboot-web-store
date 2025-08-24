create table if not exists products(
    id bigint PRIMARY KEY auto_increment ,
    name varchar(255) not null,
    price decimal(10,2) not null,
    category_id tinyint not null,
    Constraint fk_category
        FOREIGN KEY (category_id) references categories (id)
                                ON DELETE RESTRICT
);