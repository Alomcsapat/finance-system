CREATE TABLE banks
(
    id                       BIGSERIAL PRIMARY KEY,
    name                     VARCHAR(255),
    active                   BOOLEAN DEFAULT true,
    deleted                  BOOLEAN DEFAULT false,
    create_date_time         TIMESTAMP WITHOUT TIME ZONE,
    update_date_time         TIMESTAMP WITHOUT TIME ZONE
);

CREATE TABLE bank_accounts
(
    id                       BIGSERIAL PRIMARY KEY,
    holder_id                BIGINT REFERENCES profiles(id),
    bank_id                  BIGINT REFERENCES banks(id),
    balance                  NUMERIC(19, 4) DEFAULT 0.0,
    account_number           VARCHAR(64),
    title                    VARCHAR(255),
    active                   BOOLEAN DEFAULT true,
    deleted                  BOOLEAN DEFAULT false,
    create_date_time         TIMESTAMP WITHOUT TIME ZONE,
    update_date_time         TIMESTAMP WITHOUT TIME ZONE
);