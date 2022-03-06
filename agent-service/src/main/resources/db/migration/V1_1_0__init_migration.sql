DROP TABLE IF EXISTS agent;

CREATE TABLE agent
(
    id                   SERIAL PRIMARY KEY,
    uuid                 varchar(100) UNIQUE NOT NULL,
    business_name        varchar(150)        NOT NULL,
    business_email       varchar(150)        NOT NULL,
    business_description text                NOT NULL,
    business_address1    varchar(400)        NOT NULL,
    business_address2    varchar(400),
    business_postcode    varchar(20),
    business_country     varchar(20)         NOT NULL
);