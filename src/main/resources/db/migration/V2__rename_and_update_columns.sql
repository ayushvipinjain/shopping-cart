-- Rename columns by removing the 'product' prefix
ALTER TABLE product_details
    RENAME COLUMN product_id TO id;

ALTER TABLE product_details
    RENAME COLUMN product_name TO name;

ALTER TABLE product_details
    RENAME COLUMN product_description TO description;

ALTER TABLE product_details
    RENAME COLUMN product_price TO price;

ALTER TABLE product_details
    RENAME COLUMN product_quantity TO quantity;

ALTER TABLE product_details
    RENAME COLUMN product_category TO category;

-- Update data types (example adjustments)
ALTER TABLE product_details
ALTER COLUMN price TYPE NUMERIC(15, 4);

ALTER TABLE product_details
ALTER COLUMN description TYPE VARCHAR(500);
