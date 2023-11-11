CREATE TABLE IF NOT EXISTS public.address
(
    id bigint NOT NULL DEFAULT nextval('address_id_seq'::regclass),
    latitude double precision,
    longitude double precision,
    users_id bigint,
    CONSTRAINT address_pkey PRIMARY KEY (id),
    CONSTRAINT fkls1hr62ne1ffxvi7yayu99wdk FOREIGN KEY (users_id)
    REFERENCES public.users (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
);

CREATE TABLE IF NOT EXISTS public.category
(
    file_storage_id bigint,
    id bigint NOT NULL DEFAULT nextval('category_id_seq'::regclass),
    parent_id bigint,
    name character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT category_pkey PRIMARY KEY (id),
    CONSTRAINT category_file_storage_id_key UNIQUE (file_storage_id),
    CONSTRAINT fk2mt7g1i6lmvmmrpqy1aghdu15 FOREIGN KEY (file_storage_id)
    REFERENCES public.file_storage (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
);

CREATE TABLE IF NOT EXISTS public.file_storage
(
    file_size bigint,
    id bigint NOT NULL DEFAULT nextval('file_storage_id_seq'::regclass),
    content_type character varying(255) COLLATE pg_catalog."default",
    extension character varying(255) COLLATE pg_catalog."default",
    file_storage_status character varying(255) COLLATE pg_catalog."default",
    hash_id character varying(255) COLLATE pg_catalog."default",
    name character varying(255) COLLATE pg_catalog."default",
    upload_path character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT file_storage_pkey PRIMARY KEY (id),
    CONSTRAINT file_storage_file_storage_status_check CHECK (file_storage_status::text = ANY (ARRAY['ACTIVE'::character varying, 'DRAFT'::character varying]::text[]))
);

CREATE TABLE IF NOT EXISTS public.users
(
    id bigint NOT NULL DEFAULT nextval('users_id_seq'::regclass),
    chat_id bigint,
    name character varying(255) COLLATE pg_catalog."default",
    state character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT users_pkey PRIMARY KEY (id),
    CONSTRAINT users_state_check CHECK (state::text = ANY (ARRAY['START'::character varying, 'MENU'::character varying, 'INLINE'::character varying, 'REGISTER'::character varying, 'LOCATION'::character varying, 'MY_LOCATION'::character varying, 'PRODUCTS'::character varying]::text[]))
);
