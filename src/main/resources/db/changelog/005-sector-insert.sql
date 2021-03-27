INSERT INTO sector (sector)
VALUES ('Manufacturing'),
    ('Construction materials'),
    ('Electronics and Optics'),
    ('Food and Beverage'),
    ('Bakery &amp; confectionery products'),
    ('Beverages'),
    ('Fish &amp; fish products '),
    ('Meat &amp; meat products'),
    ('Milk &amp; dairy products '),
    ('Other (Food and Beverage)'),
    ('Sweets &amp; snack food'),
    ('Furniture'),
    ('Bathroom/sauna '),
    ('Bedroom'),
    ('Children’s room '),
    ('Kitchen '),
    ('Living room '),
    ('Office'),
    ('Other (Furniture)'),
    ('Outdoor '),
    ('Project furniture'),
    ('Machinery'),
    ('Machinery components'),
    ('Machinery equipment/tools'),
    ('Manufacture of machinery '),
    ('Maritime'),
    ('Aluminium and steel workboats '),
    ('Boat/Yacht building'),
    ('Ship repair and conversion'),
    ('Metal structures'),
    ('Other (Machinery)'),
    ('Repair and maintenance service');

UPDATE sector set parent_id = (SELECT id from sector where sector = 'Manufacturing') where sector != 'Manufacturing';

UPDATE sector SET parent_id = (SELECT id FROM sector WHERE sector = 'Food and Beverage') WHERE sector = 'Bakery &amp; confectionery products';
UPDATE sector SET parent_id = (SELECT id FROM sector WHERE sector = 'Food and Beverage') WHERE sector = 'Fish &amp; fish products ';
UPDATE sector SET parent_id = (SELECT id FROM sector WHERE sector = 'Food and Beverage') WHERE sector = 'Beverages';
UPDATE sector SET parent_id = (SELECT id FROM sector WHERE sector = 'Food and Beverage') WHERE sector = 'Milk &amp; dairy products ';
UPDATE sector SET parent_id = (SELECT id FROM sector WHERE sector = 'Food and Beverage') WHERE sector = 'Meat &amp; meat products';

UPDATE sector SET parent_id = (SELECT id FROM sector where sector = 'Furniture') WHERE sector = 'Bathroom/sauna';
UPDATE sector SET parent_id = (SELECT id FROM sector where sector = 'Furniture') WHERE sector = 'Bedroom';
UPDATE sector SET parent_id = (SELECT id FROM sector where sector = 'Furniture') WHERE sector = 'Children’s room';
UPDATE sector SET parent_id = (SELECT id FROM sector where sector = 'Furniture') WHERE sector = 'Kitchen';
UPDATE sector SET parent_id = (SELECT id FROM sector where sector = 'Furniture') WHERE sector = 'Living room';
UPDATE sector SET parent_id = (SELECT id FROM sector where sector = 'Furniture') WHERE sector = 'Office';
UPDATE sector SET parent_id = (SELECT id FROM sector where sector = 'Furniture') WHERE sector = 'Other (Furniture)';
UPDATE sector SET parent_id = (SELECT id FROM sector where sector = 'Furniture') WHERE sector = 'Outdoor';
UPDATE sector SET parent_id = (SELECT id FROM sector where sector = 'Furniture') WHERE sector = 'Project furniture';

UPDATE sector set parent_id = (SELECT id FROM sector WHERE sector = 'Machinery') WHERE sector = 'Machinery components';
UPDATE sector set parent_id = (SELECT id FROM sector WHERE sector = 'Machinery') WHERE sector = 'Machinery equipment/tools';
UPDATE sector set parent_id = (SELECT id FROM sector WHERE sector = 'Machinery') WHERE sector = 'Manufacture of machinery ';
UPDATE sector set parent_id = (SELECT id FROM sector WHERE sector = 'Machinery') WHERE sector = 'Maritime';
UPDATE sector set parent_id = (SELECT id FROM sector WHERE sector = 'Machinery') WHERE sector = 'Metal structures';
UPDATE sector set parent_id = (SELECT id FROM sector WHERE sector = 'Machinery') WHERE sector = 'Other (Machinery)';
UPDATE sector set parent_id = (SELECT id FROM sector WHERE sector = 'Machinery') WHERE sector = 'Repair and maintenance service';

UPDATE sector set parent_id = (SELECT id FROM sector WHERE sector = 'Maritime') WHERE sector = 'Aluminium and steel workboats';
UPDATE sector set parent_id = (SELECT id FROM sector WHERE sector = 'Maritime') WHERE sector = 'Boat/Yacht building';
UPDATE sector set parent_id = (SELECT id FROM sector WHERE sector = 'Maritime') WHERE sector = 'Ship repair and conversion';