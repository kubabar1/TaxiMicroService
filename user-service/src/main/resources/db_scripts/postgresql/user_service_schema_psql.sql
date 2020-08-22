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
    CONSTRAINT fk_users FOREIGN KEY(user_id) REFERENCES users(id),
    CONSTRAINT fk_roles FOREIGN KEY(role_id) REFERENCES roles(id)
);

CREATE TABLE languages (
    language_code VARCHAR PRIMARY KEY NOT NULL,
    name VARCHAR NOT NULL
);

CREATE TABLE appearances (
    appearance_code VARCHAR PRIMARY KEY NOT NULL,
    name VARCHAR NOT NULL
);

CREATE TABLE user_settings (
    user_id INT PRIMARY KEY NOT NULL,
    language_code VARCHAR NOT NULL,
    appearance_code VARCHAR NOT NULL,
    CONSTRAINT fk_languages FOREIGN KEY(language_code) REFERENCES languages(language_code),
    CONSTRAINT fk_appearances FOREIGN KEY(appearance_code) REFERENCES appearances(appearance_code)
);
