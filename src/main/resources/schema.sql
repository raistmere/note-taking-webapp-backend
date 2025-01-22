CREATE TABLE IF NOT EXISTS Users (
    id int PRIMARY KEY,
    name VARCHAR(20),
    password VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS Notes (
    id int PRIMARY KEY,
    userID int,
    note TEXT,
    FOREIGN KEY (userID) REFERENCES Users(id)
        ON DELETE CASCADE
);