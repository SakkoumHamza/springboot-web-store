CREATE TABLE revoked_tokens (
                                jti VARCHAR(255) PRIMARY KEY,
                                expiry_date TIMESTAMP NOT NULL
);
