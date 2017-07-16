INSERT INTO users (id, username, password, enabled) values (1, 'admin', '$2a$10$B2LrIkEXmztSlQu2OhVFNeP6EfbQNvrTNr7gBBkIPh9NlBxDN4nVe', true);
INSERT INTO authorities (id, username, authority) values (1, 'admin', 'ROLE_ADMIN');
INSERT INTO users (id, username, password, enabled) values (2, 'tom', '$2a$10$B2LrIkEXmztSlQu2OhVFNeP6EfbQNvrTNr7gBBkIPh9NlBxDN4nVe', true);
INSERT INTO authorities (id, username, authority) values (2, 'tom', 'ROLE_USER');
