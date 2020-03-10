create table test_data_customer
(
    id INTEGER constraint test_data_customer_id_pk primary key autoincrement,
    first_name TEXT NOT NULL,
    last_name  TEXT NOT NULL,
    username   TEXT NOT NULL,
    email      TEXT,
    address    TEXT NOT NULL,
    user_id INTEGER NOT NULL,
    country_id INTEGER NOT NULL,
    FOREIGN KEY (user_id) REFERENCES test_data_users (id),
    FOREIGN KEY (country_id) REFERENCES test_data_country (id)
);