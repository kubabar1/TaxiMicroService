DROP TABLE IF EXISTS users;

CREATE TABLE users (
    id SERIAL PRIMARY KEY NOT NULL,
    user_name VARCHAR UNIQUE NOT NULL,
    email VARCHAR UNIQUE NOT NULL,
    name VARCHAR NOT NULL,
    surname VARCHAR NOT NULL,
    pesel VARCHAR UNIQUE NOT NULL,
    birth_date DATE NOT NULL,
    password VARCHAR NOT NULL,
    password_salt VARCHAR NOT NULL,
    creation_date TIMESTAMP NOT NULL
);

CREATE TABLE roles (
    id SERIAL PRIMARY KEY NOT NULL,
    name VARCHAR NOT NULL
);

CREATE TABLE user_roles (
    id SERIAL PRIMARY KEY NOT NULL,
    user_id INT NOT NULL,
    role_id INT NOT NULL,
    foreign key (user_id) references users(id),
    foreign key (role_id) references roles(id)
);

CREATE TABLE languages (
    language_code VARCHAR PRIMARY KEY NOT NULL,
    name VARCHAR NOT NULL
);

CREATE TABLE appearances (
    id SERIAL PRIMARY KEY NOT NULL,
    name VARCHAR NOT NULL
);

CREATE TABLE user_settings (
    user_id INT PRIMARY KEY NOT NULL,
    language_code VARCHAR NOT NULL,
    appearance_id INT NOT NULL,
    foreign key (language_code) references languages(language_code),
    foreign key (appearance_id) references appearances(id)
);
