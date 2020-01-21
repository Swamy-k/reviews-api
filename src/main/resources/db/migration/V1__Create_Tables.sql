CREATE TABLE product (
    `id` INT AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(30) NOT NULL,
    `description` TEXT NOT NULL,
    `company_name` VARCHAR(30) NOT NULL
);

CREATE TABLE review (
    `id` INT AUTO_INCREMENT PRIMARY KEY,
    `product_id` INT NOT NULL,
    `author_name` VARCHAR(30) NOT NULL,
    `title` VARCHAR(50) NOT NULL,
    `create_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `review` TEXT NOT NULL,
    CONSTRAINT product_fk FOREIGN KEY (product_id) REFERENCES product(id)
);

CREATE TABLE comment (
    `id` INT AUTO_INCREMENT PRIMARY KEY,
    `review_id` INT NOT NULL,
    `author_name` VARCHAR(30) NOT NULL,
    `create_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `comment` TEXT NOT NULL,
    CONSTRAINT review_fk FOREIGN KEY (review_id) REFERENCES review(id)
);
