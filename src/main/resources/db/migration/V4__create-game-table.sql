CREATE TABLE game (
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    year_release INTEGER NOT NULL,
    gender VARCHAR(255),
    developer_id UUID,
    FOREIGN KEY (developer_id) REFERENCES developer(id),
    publisher_id UUID,
    FOREIGN KEY (publisher_id) REFERENCES publisher(id),
    franchise_id UUID,
    FOREIGN KEY (franchise_id) REFERENCES franchise(id),
    description VARCHAR(255) NOT NULL,
    platform VARCHAR(255) NOT NULL
);