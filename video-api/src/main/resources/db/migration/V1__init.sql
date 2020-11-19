CREATE TABLE IF NOT EXISTS rooms(
        id INT NOT NULL AUTO_INCREMENT,
        public_id UUID NOT NULL,
        name VARCHAR(64) NOT NULL,
        max_size INT,
        create_date TIMESTAMP WITH TIME ZONE,
        PRIMARY KEY(id)
);
CREATE UNIQUE INDEX ON rooms(name);

CREATE TABLE IF NOT EXISTS users(
    id INT NOT NULL AUTO_INCREMENT,
    public_id UUID NOT NULL,
    user_name VARCHAR(64),
    create_date TIMESTAMP WITH TIME ZONE,
    PRIMARY KEY(id)
);


CREATE TABLE IF NOT EXISTS rooms_users(
    id INT NOT NULL AUTO_INCREMENT,
    public_id UUID NOT NULL,
    user_id INT NOT NULL,
    room_id INT NOT NULL,
    create_date TIMESTAMP WITH TIME ZONE,
    PRIMARY KEY(id),
    FOREIGN KEY(user_id) REFERENCES users(id),
    FOREIGN KEY(room_id) REFERENCES rooms(id)
);
CREATE UNIQUE INDEX ON rooms_users(user_id, room_id);
CREATE INDEX ON rooms_users(user_id);