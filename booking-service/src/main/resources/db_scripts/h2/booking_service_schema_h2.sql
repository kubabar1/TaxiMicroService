DROP TABLE IF EXISTS booking_status;
DROP TABLE IF EXISTS localisations;
DROP TABLE IF EXISTS bookings;

CREATE TABLE booking_status (
    id VARCHAR PRIMARY KEY NOT NULL,
    name VARCHAR NOT NULL,
    description VARCHAR NOT NULL
);

CREATE TABLE localisations (
    id SERIAL PRIMARY KEY NOT NULL,
    latitude decimal NOT NULL,
    longitude decimal NOT NULL
);

CREATE TABLE bookings (
    id SERIAL PRIMARY KEY NOT NULL,
    start_point INT NOT NULL,
    finish_point INT NOT NULL,
    passenger_id INT NOT NULL,
    driver_id INT,
    creation_date TIMESTAMP NOT NULL,
    status VARCHAR NOT NULL,
    foreign key (status) references booking_status(id),
    foreign key (start_point) references localisations(id),
    foreign key (finish_point) references localisations(id)
);