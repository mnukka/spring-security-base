create table test_data_user
(
  id INTEGER constraint test_data_users_id_pk primary key autoincrement,
  username TEXT NOT NULL,
  password TEXT NOT NULL
);

