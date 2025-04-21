CREATE SEQUENCE wallet_id_seq;

CREATE TABLE accounts
(
    id                       BIGSERIAL PRIMARY KEY,
    password                 VARCHAR(255),
    login                    VARCHAR(255),
    active                   BOOLEAN DEFAULT true,
    deleted                  BOOLEAN DEFAULT false,
    create_date_time         TIMESTAMP WITHOUT TIME ZONE,
    update_date_time         TIMESTAMP WITHOUT TIME ZONE
);

CREATE TABLE permissions
(
    id                       BIGSERIAL PRIMARY KEY,
    role                     VARCHAR(50),
    account_id               BIGINT REFERENCES accounts(id),
    active                   BOOLEAN DEFAULT true,
    deleted                  BOOLEAN DEFAULT false,
    create_date_time         TIMESTAMP WITHOUT TIME ZONE,
    update_date_time         TIMESTAMP WITHOUT TIME ZONE
);


CREATE TABLE users
(
    id                       BIGSERIAL PRIMARY KEY,
    account_id               BIGINT REFERENCES accounts(id)
);

CREATE TABLE persons
(
    id                       BIGSERIAL PRIMARY KEY,
    first_name               VARCHAR(100),
    middle_name              VARCHAR(100),
    last_name                VARCHAR(100)
);

CREATE TABLE companies
(
    id                       BIGSERIAL PRIMARY KEY
);


CREATE TABLE profiles
(
    id                       BIGSERIAL PRIMARY KEY,
    name                     VARCHAR(255),
    email                    VARCHAR(100),
    phone                    VARCHAR(20),
    inn                      VARCHAR(12),
    active                   BOOLEAN DEFAULT true,
    deleted                  BOOLEAN DEFAULT false,
    create_date_time         TIMESTAMP WITHOUT TIME ZONE,
    update_date_time         TIMESTAMP WITHOUT TIME ZONE
);





