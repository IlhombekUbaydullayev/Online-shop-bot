CREATE TABLE IF NOT EXISTS basket
(
    id bigserial primary key,
    desciption character varying(255) NULL,
    price INT DEFAULT '0',
    delivery INT DEFAULT '0',
    total INT DEFAULT '0',
    chatId bigint DEFAULT 0,
    status boolean DEFAULT false,
    count INT DEFAULT '1'
);