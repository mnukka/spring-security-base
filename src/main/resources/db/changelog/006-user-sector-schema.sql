create table user_sector
(
    id SERIAL constraint user_sector_id_pk primary key,
    user_id INTEGER NOT NULL,
    sector_id INTEGER NOT NULL,
    enabled BOOLEAN NOT NULL DEFAULT TRUE,

    FOREIGN KEY (user_id) REFERENCES user_credentials (id),
    FOREIGN KEY (sector_id) REFERENCES sector (id)
);

