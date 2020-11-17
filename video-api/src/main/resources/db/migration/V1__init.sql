CREATE TABLE IF NOT EXISTS room(
        id INT NOT NULL AUTO_INCREMENT,
        public_id UUID NOT NULL,
        name VARCHAR(64) NOT NULL,
        max_size INT,
        create_date TIMESTAMPTZ,
        PRIMARY KEY(id)
);
CREATE UNIQUE INDEX ON room(name);
