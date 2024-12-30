CREATE TABLE product
(
    oid           BIGINT NOT NULL PRIMARY KEY,
    book_title    VARCHAR NOT NULL,
    book_price    DECIMAL,
    book_qty      INTEGER

);

CREATE TABLE customer
(
    oid               BIGINT NOT NULL PRIMARY KEY,
    first_name        VARCHAR NOT NULL,
    last_name         VARCHAR NOT NULL,
    personal_email    VARCHAR,
    office_email      VARCHAR
);

CREATE SEQUENCE s_customer START WITH 1 INCREMENT BY 1 CACHE 50 NO CYCLE;
CREATE SEQUENCE s_product START WITH 1 INCREMENT BY 1 CACHE 50 NO CYCLE;