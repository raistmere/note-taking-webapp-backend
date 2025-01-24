CREATE TABLE IF NOT EXISTS Users (
    id int PRIMARY KEY,
    name VARCHAR(20),
    password VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS Notes (
    id int PRIMARY KEY,
    userId int,
    title VARCHAR(50),
    note TEXT,
    FOREIGN KEY (userId) REFERENCES Users(id)
        ON DELETE CASCADE
);