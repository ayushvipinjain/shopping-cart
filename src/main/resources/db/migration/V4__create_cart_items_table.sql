CREATE TABLE cartitems(
  cart_item_id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL ,
  product_id BIGINT REFERENCES productdetails(productid),
  cart_id BIGINT REFERENCES carts(cart_id),
  quantity BIGINT not null ,
  total_Price DECIMAL(10,2) not null,
  CONSTRAINT pk_cartitems PRIMARY KEY (cart_item_id)
);