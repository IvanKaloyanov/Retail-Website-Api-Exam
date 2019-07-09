
CREATE DATABASE retail;

CREATE TABLE users (
    id INT AUTO_INCREMENT,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    UNIQUE(username),
    PRIMARY KEY (id)
);

CREATE TABLE user_roles (
    user_id INT,
    roles VARCHAR(255)
);