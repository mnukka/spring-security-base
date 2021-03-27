create table sector
(
  id INTEGER constraint sector_id_pk primary key autoincrement,
  parent_id INTEGER DEFAULT NULL,
  sector TEXT NOT NULL,
  enabled BOOLEAN DEFAULT true
);

