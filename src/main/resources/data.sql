INSERT INTO roles(role_name) VALUES ('ROLE_USER'),
                                    ('ROLE_ADMIN');

INSERT INTO users(age, lastname,name, password) VALUES (22, 'user', 'user','$2a$12$7pIopZ3eKtdMaicIbedBwOmagM4/XmElKhwvaj6uIEDtWeq6tWTRi'),
                                                       (23, 'admin', 'admin','$2a$12$QLMRL99R4vJDDttsSGFRR.0e60HUllQrCfltfUrfbQy2J4hX2MRqW');

INSERT INTO users_roles VALUES (1, 1),
                               (2, 1),
                               (2, 2);