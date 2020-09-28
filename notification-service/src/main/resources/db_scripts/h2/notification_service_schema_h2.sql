DROP TABLE IF EXISTS notifications;
DROP TABLE IF EXISTS notification_type;
DROP TABLE IF EXISTS notification_status;

CREATE TABLE notification_type (
    id VARCHAR PRIMARY KEY NOT NULL,
    name VARCHAR NOT NULL
);

CREATE TABLE notification_status (
    id VARCHAR PRIMARY KEY NOT NULL,
    name VARCHAR NOT NULL
);

CREATE TABLE notifications (
    id SERIAL PRIMARY KEY NOT NULL,
    type VARCHAR NOT NULL,
    status VARCHAR NOT NULL,
    content TEXT NOT NULL,
    sender VARCHAR NOT NULL,
    receiver_username VARCHAR NOT NULL,
    creation_date TIMESTAMP NOT NULL,
    foreign key (type) references notification_type(id),
    foreign key (status) references notification_status(id)
);
