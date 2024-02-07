CREATE TABLE IF NOT EXISTS slugs(
    slug_id UUID PRIMARY KEY NOT NULL,
    slug_value VARCHAR(255)
);

-- INSERT INTO slugs(slug_id, slug_value)
--     VALUES ('99b1ff8f-1178-3541-b7f8-9f9bd41c4a17', 'hello_world');
--
-- INSERT INTO slugs(slug_id, slug_value)
--     VALUES ('ad1526dc-bed7-326b-ae56-e1e11e6de419', 'works_perfect');
