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