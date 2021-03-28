create table user_credentials
(
    id SERIAL constraint user_credentials_id_pk primary key,
    username TEXT NOT NULL,
    password TEXT NOT NULL
);