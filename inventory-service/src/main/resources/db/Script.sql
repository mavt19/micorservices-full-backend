CREATE TABLE IF NOT EXISTS public.inventories (
    id SERIAL PRIMARY KEY,
    sku_code TEXT NOT null,
    quantity int 
);