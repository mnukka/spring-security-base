INSERT INTO sector (sector)
VALUES ('Manufacturing'),
       ('Service'),
       ('Construction materials'),
       ('Electronics and Optics'),
       ('Food and Beverage'),
       ('Bakery & confectionery products'),
       ('Beverages'),
       ('Fish & fish products'),
       ('Meat & meat products'),
       ('Milk & dairy products'),
       ('Other (Food and Beverage)'),
       ('Sweets & snack food'),
       ('Furniture'),
       ('Bathroom/sauna'),
       ('Bedroom'),
       ('Children’s room'),
       ('Kitchen'),
       ('Living room'),
       ('Office'),
       ('Other (Furniture)'),
       ('Outdoor'),
       ('Project furniture'),
       ('Machinery'),
       ('Machinery components'),
       ('Machinery equipment/tools'),
       ('Manufacture of machinery'),
       ('Maritime'),
       ('Aluminium and steel workboats'),
       ('Boat/Yacht building'),
       ('Ship repair and conversion'),
       ('Metal structures'),
       ('Other (Machinery)'),
       ('Repair and maintenance service'),
       ('Metalworking'),
       ('Construction of metal structures'),
       ('Houses and buildings'),
       ('Metal products'),
       ('Metal works'),
       ('CNC-machining'),
       ('Forgings, Fasteners'),
       ('Gas, Plasma, Laser cutting'),
       ('MIG, TIG, Aluminum welding'),
       ('Plastic and Rubber'),
       ('Packaging'),
       ('Plastic goods'),
       ('Plastic processing technology'),
       ('Blowing'),
       ('Moulding'),
       ('Plastics welding and processing'),
       ('Plastic profiles'),
       ('Printing'),
       ('Advertising'),
       ('Book/Periodicals printing'),
       ('Labelling and packaging printing'),
       ('Textile and Clothing'),
       ('Clothing'),
       ('Textile'),
       ('Wood'),
       ('Other (Wood)'),
       ('Wooden building materials'),
       ('Wooden houses'),
       ('Creative industries'),
       ('Energy technology'),
       ('Environment'),
       ('Business services'),
       ('Engineering'),
       ('Tourism'),
       ('Translation services'),
       ('Transport and Logistics'),
       ('Air'),
       ('Rail'),
       ('Road'),
       ('Water'),
       ('Information Technology and Telecommunications'),
       ('Data processing, Web portals, E-marketing'),
       ('Programming, Consultancy'),
       ('Software, Hardware'),
       ('Telecommunications'),
       ('Other');

UPDATE sector set parent_id = (SELECT id from sector where sector = 'Manufacturing') where sector != 'Manufacturing';

UPDATE sector SET parent_id = (SELECT id FROM sector WHERE sector = 'Food and Beverage') WHERE sector = 'Bakery & confectionery products';
UPDATE sector SET parent_id = (SELECT id FROM sector WHERE sector = 'Food and Beverage') WHERE sector = 'Fish & fish products';
UPDATE sector SET parent_id = (SELECT id FROM sector WHERE sector = 'Food and Beverage') WHERE sector = 'Beverages';
UPDATE sector SET parent_id = (SELECT id FROM sector WHERE sector = 'Food and Beverage') WHERE sector = 'Milk & dairy products';
UPDATE sector SET parent_id = (SELECT id FROM sector WHERE sector = 'Food and Beverage') WHERE sector = 'Meat & meat products';

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
UPDATE sector set parent_id = (SELECT id FROM sector WHERE sector = 'Machinery') WHERE sector = 'Manufacture of machinery';
UPDATE sector set parent_id = (SELECT id FROM sector WHERE sector = 'Machinery') WHERE sector = 'Maritime';
UPDATE sector set parent_id = (SELECT id FROM sector WHERE sector = 'Machinery') WHERE sector = 'Metal structures';
UPDATE sector set parent_id = (SELECT id FROM sector WHERE sector = 'Machinery') WHERE sector = 'Other (Machinery)';
UPDATE sector set parent_id = (SELECT id FROM sector WHERE sector = 'Machinery') WHERE sector = 'Repair and maintenance service';

UPDATE sector set parent_id = (SELECT id FROM sector WHERE sector = 'Maritime') WHERE sector = 'Aluminium and steel workboats';
UPDATE sector set parent_id = (SELECT id FROM sector WHERE sector = 'Maritime') WHERE sector = 'Boat/Yacht building';
UPDATE sector set parent_id = (SELECT id FROM sector WHERE sector = 'Maritime') WHERE sector = 'Ship repair and conversion';

UPDATE sector set parent_id = (SELECT id FROM sector WHERE sector = 'Metalworking') WHERE sector = 'Construction of metal structures';
UPDATE sector set parent_id = (SELECT id FROM sector WHERE sector = 'Metalworking') WHERE sector = 'Houses and buildings';
UPDATE sector set parent_id = (SELECT id FROM sector WHERE sector = 'Metalworking') WHERE sector = 'Metal products';
UPDATE sector set parent_id = (SELECT id FROM sector WHERE sector = 'Metalworking') WHERE sector = 'Metal works';

UPDATE sector set parent_id = (SELECT id FROM sector WHERE sector = 'Metal works') WHERE sector = 'CNC-machining';
UPDATE sector set parent_id = (SELECT id FROM sector WHERE sector = 'Metal works') WHERE sector = 'Forgings, Fasteners';
UPDATE sector set parent_id = (SELECT id FROM sector WHERE sector = 'Metal works') WHERE sector = 'Gas, Plasma, Laser cutting';
UPDATE sector set parent_id = (SELECT id FROM sector WHERE sector = 'Metal works') WHERE sector = 'MIG, TIG, Aluminum welding';

UPDATE sector set parent_id = (SELECT id FROM sector WHERE sector = 'Plastic and Rubber') WHERE sector = 'Packaging';
UPDATE sector set parent_id = (SELECT id FROM sector WHERE sector = 'Plastic and Rubber') WHERE sector = 'Plastic goods';
UPDATE sector set parent_id = (SELECT id FROM sector WHERE sector = 'Plastic and Rubber') WHERE sector = 'Plastic processing technology';
UPDATE sector set parent_id = (SELECT id FROM sector WHERE sector = 'Plastic and Rubber') WHERE sector = 'Plastic profiles';

UPDATE sector set parent_id = (SELECT id FROM sector WHERE sector = 'Plastic processing technology') WHERE sector = 'Blowing';
UPDATE sector set parent_id = (SELECT id FROM sector WHERE sector = 'Plastic processing technology') WHERE sector = 'Moulding';
UPDATE sector set parent_id = (SELECT id FROM sector WHERE sector = 'Plastic processing technology') WHERE sector = 'Plastics welding and processing';

UPDATE sector set parent_id = (SELECT id FROM sector WHERE sector = 'Printing') WHERE sector = 'Advertising';
UPDATE sector set parent_id = (SELECT id FROM sector WHERE sector = 'Printing') WHERE sector = 'Book/Periodicals printing';
UPDATE sector set parent_id = (SELECT id FROM sector WHERE sector = 'Printing') WHERE sector = 'Labelling and packaging printing';

UPDATE sector set parent_id = (SELECT id FROM sector WHERE sector = 'Textile and Clothing') WHERE sector = 'Clothing';
UPDATE sector set parent_id = (SELECT id FROM sector WHERE sector = 'Textile and Clothing') WHERE sector = 'Textile';

UPDATE sector set parent_id = (SELECT id FROM sector WHERE sector = 'Wood') WHERE sector = 'Other (Wood)';
UPDATE sector set parent_id = (SELECT id FROM sector WHERE sector = 'Wood') WHERE sector = 'Wooden building materials';
UPDATE sector set parent_id = (SELECT id FROM sector WHERE sector = 'Wood') WHERE sector = 'Wooden houses';

UPDATE sector set parent_id = (SELECT id FROM sector WHERE sector = 'Other') WHERE sector = 'Creative industries';
UPDATE sector set parent_id = (SELECT id FROM sector WHERE sector = 'Other') WHERE sector = 'Energy technology';
UPDATE sector set parent_id = (SELECT id FROM sector WHERE sector = 'Other') WHERE sector = 'Environment';

UPDATE sector set parent_id = (SELECT id FROM sector WHERE sector = 'Service') WHERE sector = 'Business services';
UPDATE sector set parent_id = (SELECT id FROM sector WHERE sector = 'Service') WHERE sector = 'Engineering';
UPDATE sector set parent_id = (SELECT id FROM sector WHERE sector = 'Service') WHERE sector = 'Tourism';
UPDATE sector set parent_id = (SELECT id FROM sector WHERE sector = 'Service') WHERE sector = 'Translation services';
UPDATE sector set parent_id = (SELECT id FROM sector WHERE sector = 'Service') WHERE sector = 'Transport and Logistics';

UPDATE sector set parent_id = (SELECT id FROM sector WHERE sector = 'Transport and Logistics') WHERE sector = 'Air';
UPDATE sector set parent_id = (SELECT id FROM sector WHERE sector = 'Transport and Logistics') WHERE sector = 'Rail';
UPDATE sector set parent_id = (SELECT id FROM sector WHERE sector = 'Transport and Logistics') WHERE sector = 'Road';
UPDATE sector set parent_id = (SELECT id FROM sector WHERE sector = 'Transport and Logistics') WHERE sector = 'Water';

UPDATE sector set parent_id = (SELECT id FROM sector WHERE sector = 'Information Technology and Telecommunications') WHERE sector = 'Data processing, Web portals, E-marketing';
UPDATE sector set parent_id = (SELECT id FROM sector WHERE sector = 'Information Technology and Telecommunications') WHERE sector = 'Programming, Consultancy';
UPDATE sector set parent_id = (SELECT id FROM sector WHERE sector = 'Information Technology and Telecommunications') WHERE sector = 'Software, Hardware';
UPDATE sector set parent_id = (SELECT id FROM sector WHERE sector = 'Information Technology and Telecommunications') WHERE sector = 'Telecommunications';