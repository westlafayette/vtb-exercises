CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE person
(
    "id"   uuid PRIMARY KEY DEFAULT uuid_generate_v4(),
    "name" varchar(256) NOT NULL UNIQUE
);

CREATE TABLE product
(
    "id"    uuid PRIMARY KEY DEFAULT uuid_generate_v4(),
    "name"  varchar(256) NOT NULL UNIQUE,
    "price" bigint       NOT NULL
);

CREATE TABLE person_product
(
    "id"          uuid PRIMARY KEY DEFAULT uuid_generate_v4(),
    "person_id" uuid REFERENCES person (id) ON DELETE CASCADE,
    "product_id"  uuid REFERENCES product (id) ON DELETE CASCADE
);

INSERT INTO person(name)
VALUES ('max'),
       ('doni'),
       ('galik'),
       ('eroha');

INSERT INTO product(name, price)
VALUES ('macbook', 1500),
       ('ps5', 600);