ALTER TABLE contact
    DROP CONSTRAINT uc_contact_email;

ALTER TABLE contact
    ALTER COLUMN email TYPE VARCHAR USING (email::VARCHAR);

DO
$$
    DECLARE
        constraint_name varchar;
    BEGIN
        SELECT tc.CONSTRAINT_NAME
        into strict constraint_name
        FROM INFORMATION_SCHEMA.TABLE_CONSTRAINTS tc
        WHERE CONSTRAINT_TYPE = 'PRIMARY KEY'
          AND TABLE_NAME = 'contact'
          AND TABLE_SCHEMA = 'public';
        EXECUTE 'alter table public.contact drop constraint ' || constraint_name;
    END
$$;

ALTER TABLE contact
    ADD CONSTRAINT PK_CONTACT_EMAIL PRIMARY KEY (email);

ALTER TABLE contact
    DROP COLUMN id;