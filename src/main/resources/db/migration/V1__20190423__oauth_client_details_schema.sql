DROP TABLE IF EXISTS oauth_client_details;
CREATE TABLE oauth_client_details(
    client_id VARCHAR(256),
    resource_id VARCHAR(256),
    client_secret VARCHAR(256),
    scope VARCHAR(256),
    authorized_grant_types VARCHAR(256),
    authorities VARCHAR(256),
    access_token_validity INTEGER,
    refresh_token_validity INTEGER,
    additional_information VARCHAR(4096),
    autoapprove VARCHAR(256),
    PRIMARY KEY  (client_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

# insert client details

INSERT INTO oauth_client_details(
    client_id, client_secret, scope, authorized_grant_types, authorities, access_token_validity, refresh_token_validity)
VALUES (
    'tracom', '$2y$11$TJYcCIg2EdCZjU.uVKMD9uvTa2tVYFgtiPtWvnHQCYqlwURGck8X', 'all', 'password,refresh_token', 'ROLE_CLIENT,ROLE_TRUSTED_CLIENT', 900, 2592000
);

