create table sector
(
  id SERIAL constraint sector_id_pk primary key,
  parent_id INTEGER DEFAULT NULL,
  sector TEXT NOT NULL,
  enabled BOOLEAN DEFAULT true,
  UNIQUE(parent_id, sector)
);