create table if not exists profiles
(
    id              bigint primary key ,
    bio             text,
    phone_number    varchar(255),
    date_of_birth   date,
    loyality_points int unsigned default 0,
    foreign key (id) references users(id)

);
