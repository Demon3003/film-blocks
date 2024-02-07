CREATE TABLE permission
(
    id bigint NOT NULL,
    permission_name character varying COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT permissions_pkey PRIMARY KEY (id),
    CONSTRAINT permission_name_unique UNIQUE (permission_name)
);
CREATE TABLE role
(
    id bigint NOT NULL,
    name character varying(100) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT role_pkey PRIMARY KEY (id),
    CONSTRAINT role_name_unique UNIQUE (name)
);
CREATE TABLE role_permission
(
    role_id bigint NOT NULL,
    permission_id bigint NOT NULL,
    CONSTRAINT role_perm_pk PRIMARY KEY (role_id, permission_id),
    CONSTRAINT permission_id_fk FOREIGN KEY (permission_id)
        REFERENCES public.permission (id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
        NOT VALID,
    CONSTRAINT role_id_fk FOREIGN KEY (role_id)
        REFERENCES public.role (id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
        NOT VALID
);
CREATE TABLE users
(
    id bigint NOT NULL,
    login character varying(20) NOT NULL,
    password character varying(200) NOT NULL,
    email character varying(40),
    status_id integer NOT NULL,
    role_id bigint NOT NULL,
    CONSTRAINT user_pkey PRIMARY KEY (id),
    CONSTRAINT user_role_fk FOREIGN KEY (role_id)
        REFERENCES public.role (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
);