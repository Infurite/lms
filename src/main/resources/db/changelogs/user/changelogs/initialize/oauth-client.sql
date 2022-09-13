INSERT INTO oauth_client (client_id, resource_ids, client_secret, scope, grant_types, authorities, access_token_expiration, refresh_token_expiration, auto_approve)
VALUES
('APPLICATION', 'lms-application', '$2a$10$pm6qTwODhL/ksvkHTY1NmOhOHOU9Ou1tDKmsQ/Gr9EQy7iJpA2I9e', 'test:all', 'password,refresh_token', '', 86400, 1209600, '*')