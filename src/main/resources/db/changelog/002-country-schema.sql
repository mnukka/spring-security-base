create table test_data_country
(
  id INTEGER constraint test_data_country_id_pk primary key autoincrement,
  country TEXT NOT NULL,
  enabled BOOLEAN DEFAULT true
);

