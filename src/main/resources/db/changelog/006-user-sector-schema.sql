create table user_sector
(
    id            SERIAL constraint user_sector_id_pk primary key,
    user_id       INTEGER NOT NULL,
    sector_id     INTEGER NOT NULL,
    enabled       BOOLEAN NOT NULL DEFAULT TRUE,
    created_dtime TIMESTAMP        DEFAULT NOW(),
    updated_dtime TIMESTAMP        DEFAULT NOW(),

    FOREIGN KEY (user_id) REFERENCES user_credentials (id),
    FOREIGN KEY (sector_id) REFERENCES sector (id)
);

CREATE OR REPLACE FUNCTION set_timestamp_updated_dtime()
    RETURNS TRIGGER AS
$$
BEGIN
    NEW.updated_dtime = now();
    RETURN NEW;
END;
$$ language 'plpgsql';

CREATE TRIGGER update_user_sector_changetimestamp
    BEFORE UPDATE
    ON user_sector
    FOR EACH ROW
EXECUTE PROCEDURE
    set_timestamp_updated_dtime();