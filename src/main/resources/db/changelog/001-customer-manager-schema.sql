create table customer_manager
(
    id SERIAL constraint customer_manager_id_pk primary key,
    username TEXT NOT NULL,
    password TEXT NOT NULL
);