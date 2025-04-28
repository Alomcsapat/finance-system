 CREATE TABLE categories
 (
     id                       BIGSERIAL PRIMARY KEY,
     profile_id               BIGSERIAL REFERENCES profiles(id),
     title                    VARCHAR NOT NULL,
     active                   BOOLEAN DEFAULT true,
     deleted                  BOOLEAN DEFAULT false,
     create_date_time         TIMESTAMP WITHOUT TIME ZONE,
     update_date_time         TIMESTAMP WITHOUT TIME ZONE
 );
