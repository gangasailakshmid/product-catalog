CREATE TABLE IF NOT EXISTS style (
  id BIGSERIAL PRIMARY KEY,
  style_code VARCHAR(32) NOT NULL UNIQUE,
  name VARCHAR(120) NOT NULL
);

CREATE INDEX IF NOT EXISTS idx_style_name ON style(name);

CREATE TABLE IF NOT EXISTS product (
  id BIGSERIAL PRIMARY KEY,
  product_code VARCHAR(32) NOT NULL UNIQUE,
  description VARCHAR(255) NOT NULL,
  instore_date DATE,
  style_id BIGINT NOT NULL REFERENCES style(id)
);

CREATE INDEX IF NOT EXISTS idx_product_style_id ON product(style_id);
CREATE INDEX IF NOT EXISTS idx_product_instore_date ON product(instore_date);

CREATE TABLE IF NOT EXISTS ticket_price_history (
  id BIGSERIAL PRIMARY KEY,
  product_id BIGINT NOT NULL REFERENCES product(id),
  ticket_price NUMERIC(12,2) NOT NULL,
  effective_date DATE NOT NULL,
  CONSTRAINT uk_tph_product_effective UNIQUE (product_id, effective_date)
);

CREATE INDEX IF NOT EXISTS idx_tph_product_effective ON ticket_price_history(product_id, effective_date);
CREATE INDEX IF NOT EXISTS idx_tph_effective ON ticket_price_history(effective_date);
