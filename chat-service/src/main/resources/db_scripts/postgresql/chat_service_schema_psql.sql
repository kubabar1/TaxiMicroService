DROP TABLE IF EXISTS messages;
DROP TABLE IF EXISTS message_status;

CREATE TABLE message_status (
    id VARCHAR PRIMARY KEY NOT NULL,
    name VARCHAR NOT NULL
);

CREATE TABLE messages (
    id SERIAL PRIMARY KEY NOT NULL,
    content TEXT NOT NULL,
    sender VARCHAR NOT NULL,
    creation_date TIMESTAMP NOT NULL,
    status VARCHAR NOT NULL,
    booking_id VARCHAR NOT NULL,
    CONSTRAINT fk_status FOREIGN KEY(status) REFERENCES message_status(id)
);
