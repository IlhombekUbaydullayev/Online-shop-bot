CREATE TABLE IF NOT EXISTS products
(
    id bigserial primary key,
    name character varying(255) NULL,
    amount character varying(255) NULL,
    price character varying(255) NULL,
    color character varying(255) NULL,
    category_id bigint,
    file_storage_id bigint
--     FOREIGN KEY (category_id) REFERENCES Category(id) ON DELETE CASCADE ON UPDATE CASCADE
);