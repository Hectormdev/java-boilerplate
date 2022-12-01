-- liquibase formatted sql

-- changeset hector:1669908482605-1
CREATE TABLE public.contacts (contact_id VARCHAR(255) NOT NULL, name VARCHAR(255), phone_number_digits VARCHAR(255), phone_number_prefix VARCHAR(255), surname VARCHAR(255), user_user_id VARCHAR(255) NOT NULL, CONSTRAINT "contactsPK" PRIMARY KEY (contact_id));

-- changeset hector:1669908482605-2
CREATE TABLE public.users (user_id VARCHAR(255) NOT NULL, created_at TIMESTAMP WITHOUT TIME ZONE, name VARCHAR(255), phone_number_digits VARCHAR(255), phone_number_prefix VARCHAR(255), surname VARCHAR(255), CONSTRAINT "usersPK" PRIMARY KEY (user_id));

-- changeset hector:1669908482605-3
ALTER TABLE public.contacts ADD CONSTRAINT "FKq5e70m8oo7o113hmo9jn29tmt" FOREIGN KEY (user_user_id) REFERENCES public.users (user_id);


