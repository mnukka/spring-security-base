create table user
(
  id INTEGER constraint users_id_pk primary key autoincrement,
  username TEXT NOT NULL,
  password TEXT NOT NULL
);

