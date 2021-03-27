create table customer
(
    id INTEGER constraint customer_id_pk primary key autoincrement,
    first_name TEXT NOT NULL,
    last_name  TEXT NOT NULL,
    username   TEXT NOT NULL,
    email      TEXT,
    address    TEXT NOT NULL,
    user_id INTEGER NOT NULL,
    sector_id INTEGER NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (sector_id) REFERENCES sector (id)
);