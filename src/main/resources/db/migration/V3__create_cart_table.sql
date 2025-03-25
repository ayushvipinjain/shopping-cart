CREATE TABLE carts (
                       cart_id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
  user_id BIGINT REFERENCES users(userid),
                       sub_total DECIMAL(10,2) not null ,
    tax_rate DECIMAL(10,2) not null ,
    total DECIMAL(15,2),
                       CONSTRAINT pk_carts PRIMARY KEY (cart_id)
);