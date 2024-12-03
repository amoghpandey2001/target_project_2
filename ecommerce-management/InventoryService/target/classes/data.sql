-- Insert initial products
INSERT INTO product (id, name, description, price) VALUES
    ('p1', 'Laptop', 'Powerful laptop with high-resolution display', 1299.99),
    ('p2', 'Smartphone', 'Latest model with dual-camera setup', 899.99),
    ('p3', 'Headphones', 'Wireless noise-canceling headphones', 249.99),
    ('p4', 'Tablet', 'Lightweight tablet with 10-inch screen', 499.99),
    ('p5', 'Smartwatch', 'Wearable device with heart rate monitor', 199.99),
    ('p6', 'Camera', 'Digital camera with 24MP resolution', 799.99),
    ('p7', 'Printer', 'All-in-one printer with wireless connectivity', 149.99),
    ('p8', 'Monitor', '27-inch monitor with 4K resolution', 399.99),
    ('p9', 'Keyboard', 'Mechanical keyboard with backlit keys', 99.99),
    ('p10', 'Mouse', 'Wireless mouse with ergonomic design', 49.99);

-- Insert initial inventory quantities
INSERT INTO inventory (product_id, quantity) VALUES
    ('p1', 10),
    ('p2', 20),
    ('p3', 30),
    ('p4', 15),
    ('p5', 25),
    ('p6', 10),
    ('p7', 20),
    ('p8', 5),
    ('p9', 50),
    ('p10', 60);
