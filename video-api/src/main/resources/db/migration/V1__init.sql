CREATE TABLE IF NOT EXISTS room(
        id INT NOT NULL,
        name VARCHAR(64) NOT NULL,
        max_size INT,
        PRIMARY KEY(id)
);
CREATE UNIQUE INDEX ON room(name);
