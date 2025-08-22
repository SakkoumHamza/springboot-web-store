create table if not exists tags (
    id int auto_increment primary key,
    name varchar(255) not null
)
;

# Creating the join table

create table if not exists user_tags
(
    user_id bigint not null ,
    tag_id int not null ,
    PRIMARY KEY (user_id,tag_id),
    foreign key (tag_id) references tags(id) on delete cascade ,
    foreign key (user_id) references users(id) on delete cascade

)