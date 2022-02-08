DROP TABLE IF EXISTS offers;

CREATE TABLE offers
(
    id           SERIAL PRIMARY KEY,
    uuid         varchar(100),
    listing_id   bigint,
    offer_status varchar(50),
    expiry_date  timestamp default NULL
);