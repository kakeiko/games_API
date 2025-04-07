CREATE TABLE franchise (
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    developer_id UUID,
    FOREIGN KEY (developer_id) REFERENCES developer(id),
    description VARCHAR(255) NOT NULL
);