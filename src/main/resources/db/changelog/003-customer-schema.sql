create table customer
(
    id SERIAL constraint customer_id_pk primary key,
    first_name TEXT NOT NULL,
    last_name  TEXT NOT NULL,
    username   TEXT NOT NULL,
    email      TEXT,
    address    TEXT NOT NULL,
    user_id INTEGER NOT NULL,
    sector_id INTEGER NOT NULL,
    FOREIGN KEY (user_id) REFERENCES customer_manager (id),
    FOREIGN KEY (sector_id) REFERENCES sector (id)
);