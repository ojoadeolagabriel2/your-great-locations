DROP TABLE IF EXISTS listings;
DROP TABLE IF EXISTS listing_addresses;
DROP TABLE IF EXISTS listing_key_features;
DROP TABLE IF EXISTS listing_images;
DROP TABLE IF EXISTS listing_image_thumbnails;
DROP TABLE IF EXISTS listing_owner;

CREATE TABLE listings
(
    id                     SERIAL PRIMARY KEY,
    uuid                   varchar(100),
    listing_ownership_type varchar(50),
    listing_title          varchar(100),
    listing_currency       varchar(5),
    listed_price_per_month double precision,
    listed_price_per_week  double precision,
    created                timestamp default NULL,
    listed_date            timestamp default NULL,
    let_type               varchar(50),
    furnished_type         varchar(50),
    property_type          varchar(50),
    room_count             smallint,
    bathroom_count         smallint,
    property_description   varchar(1000),
    property_detail        varchar(2000)
);

CREATE TABLE listing_addresses
(
    id                     SERIAL PRIMARY KEY,
    post_code              varchar(20),
    address_line varchar(500)
);

CREATE TABLE listing_key_features
(
    id                  SERIAL PRIMARY KEY,
    feature_description varchar(2000)
);

CREATE TABLE listing_images
(
    id   SERIAL PRIMARY KEY,
    code varchar(100)
);

CREATE TABLE listing_image_thumbnails
(
    id   SERIAL PRIMARY KEY,
    code varchar(100)
);

CREATE TABLE listing_owner
(
    id                   SERIAL PRIMARY KEY,
    name                 varchar(200),
    phone_number         varchar(200),
    post_code            varchar(10),
    address_line         varchar(300),
    business_description varchar(2000),
    location_latitude    varchar(20),
    location_longitude   varchar(20)
);