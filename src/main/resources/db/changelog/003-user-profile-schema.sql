create table user_profile
(
    id SERIAL constraint user_profile_id_pk primary key,
    user_id INTEGER NOT NULL,
    full_name TEXT NOT NULL,
    terms_of_agreement BOOLEAN NOT NULL DEFAULT false,
    FOREIGN KEY (user_id) REFERENCES user_credentials (id)
);

