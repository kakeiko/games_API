CREATE TABLE publisher (
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    year_foundation INTEGER NOT NULL,
    size VARCHAR(255) NOT NULL,
    description VARCHAR(255) NOT NULL
);