CREATE TABLE mm_markets (
    server_id BIGINT PRIMARY KEY
);

CREATE TABLE mm_users (
    server_id BIGINT,
    user_id BIGINT,
    PRIMARY KEY (server_id, user_id),
    FOREIGN KEY (server_id) REFERENCES mm_markets (server_id) ON DELETE CASCADE
);

CREATE TABLE mm_user_reviews (
    server_id BIGINT,
    user_id BIGINT,
    rating BIT(5) NOT NULL,
    rating_reason VARCHAR(128) NULL
);