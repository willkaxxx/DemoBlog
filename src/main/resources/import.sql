----Tables creation
--CREATE TABLE post (
--    id_post INT PRIMARY KEY AUTO_INCREMENT,
--    title VARCHAR(64) NOT NULL,
--    content TEXT
--);
--CREATE TABLE tag (
--    id_tag INT PRIMARY KEY,
--    name VARCHAR(32) NOT NULL
--);
--CREATE TABLE post_tag (
--    id_post INT,
--    id_tag INT,
--    FOREIGN KEY (id_post) REFERENCES post(id_post),
--    FOREIGN KEY (id_tag) REFERENCES tag(id_tag),
--    PRIMARY KEY (id_post, id_tag)
--);

--Test data
INSERT INTO post (id_post, title, content) VALUES (1, 'Post 1', 'This is the content of Post 1.');
INSERT INTO post (id_post, title, content) VALUES (2, 'Post 2', 'This is the content of Post 2.');
INSERT INTO post (id_post, title, content) VALUES (3, 'Post 3', 'This is the content of Post 3.');

INSERT INTO tag (id_tag, name) VALUES (1, 'AI');
INSERT INTO tag (id_tag, name) VALUES (2, 'Java');
INSERT INTO tag (id_tag, name) VALUES (3, 'Cats');

INSERT INTO post_tag (id_post, id_tag) VALUES (1, 1);
INSERT INTO post_tag (id_post, id_tag) VALUES (1, 2);
INSERT INTO post_tag (id_post, id_tag) VALUES (2, 1);
INSERT INTO post_tag (id_post, id_tag) VALUES (3, 2);
INSERT INTO post_tag (id_post, id_tag) VALUES (3, 3);

----Test data
--INSERT INTO post (title, content)
--VALUES ('Post 1', 'This is the content of Post 1.'),
--       ('Post 2', 'This is the content of Post 2.'),
--       ('Post 3', 'This is the content of Post 3.');
--
--INSERT INTO tag (name)
--VALUES ('AI'),
--       ('Java'),
--       ('Cats');
--
--INSERT INTO post_tag (id_post, id_tag)
--VALUES (1, 1),
--       (1, 2),
--       (2, 1),
--       (3, 2),
--       (3, 3);