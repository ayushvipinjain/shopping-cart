-- Update data types
-- Ensure id remains as a primary key and serial type
ALTER TABLE product_details
ALTER COLUMN id SET DATA TYPE BIGINT;

-- Update name to increase name length
ALTER TABLE product_details
ALTER COLUMN name TYPE VARCHAR(150);

-- Update quantity to support larger values
ALTER TABLE product_details
    ALTER COLUMN quantity SET DATA TYPE BIGINT;

-- Update category to allow more categories
ALTER TABLE product_details
ALTER COLUMN category TYPE VARCHAR(100);