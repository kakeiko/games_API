CREATE TABLE developer (
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    year_foundation INTEGER NOT NULL,
    size VARCHAR(255) NOT NULL,
    owner VARCHAR(255),
    description VARCHAR(255) NOT NULL
);