DROP TABLE IF EXISTS messages;
DROP TABLE IF EXISTS message_status;

CREATE TABLE message_status (
    id VARCHAR PRIMARY KEY NOT NULL,
    name VARCHAR NOT NULL
);

CREATE TABLE messages (
    id SERIAL PRIMARY KEY NOT NULL,
    sender_username VARCHAR NOT NULL,
    content TEXT NOT NULL,
    creation_date TIMESTAMP NOT NULL,
    status VARCHAR NOT NULL,
    booking_id VARCHAR NOT NULL,
    foreign key (status) references message_status(id)
);
