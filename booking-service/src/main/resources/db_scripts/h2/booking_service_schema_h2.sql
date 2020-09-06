DROP TABLE IF EXISTS booking_status;
DROP TABLE IF EXISTS bookings;

CREATE TABLE booking_status (
    id VARCHAR PRIMARY KEY NOT NULL,
    description VARCHAR NOT NULL
);

CREATE TABLE bookings (
    id SERIAL PRIMARY KEY NOT NULL,
    start_point GEOMETRY NOT NULL,
    finish_point GEOMETRY NOT NULL,
    passenger_id INT NOT NULL,
    driver_id INT,
    creation_date TIMESTAMP NOT NULL,
    status VARCHAR NOT NULL,
    foreign key (status) references booking_status(id),
);