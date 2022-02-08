DROP TABLE IF EXISTS agent;

CREATE TABLE agent
(
    id                     SERIAL PRIMARY KEY,
    uuid                   varchar(100),
    name                   varchar(150),
    description          varchar(300)
);