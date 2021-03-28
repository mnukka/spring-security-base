INSERT INTO user_sector (user_id, sector_id, enabled)
VALUES (
        (SELECT id FROM user_credentials where username = 'Juku'),
        (SELECT min(id) from sector),
        true
);

INSERT INTO user_sector (user_id, sector_id, enabled)
VALUES (
        (SELECT id FROM user_credentials where username = 'Elon'),
        (SELECT min(id) from sector),
        true
);

INSERT INTO user_sector (user_id, sector_id, enabled)
VALUES (
        (SELECT id FROM user_credentials where username = 'Taavi'),
        (SELECT min(id) from sector),
        true
);