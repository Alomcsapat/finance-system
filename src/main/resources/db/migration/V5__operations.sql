 CREATE TABLE operations
 (
     id                       BIGSERIAL PRIMARY KEY,
     account_id               BIGSERIAL REFERENCES bank_accounts(id),
     type                     VARCHAR NOT NULL,
     status                   VARCHAR NOT NULL,
     amount                   INT NOT NULL,
     category_id              BIGSERIAL REFERENCES categories(id),
     contact_id               BIGSERIAL REFERENCES profiles(id),
     description              VARCHAR NOT NULL,
     active                   BOOLEAN DEFAULT true,
     deleted                  BOOLEAN DEFAULT false,
     create_date_time         TIMESTAMP WITHOUT TIME ZONE,
     update_date_time         TIMESTAMP WITHOUT TIME ZONE
 );
