--Test data
INSERT INTO post (id_post, title, content) VALUES (1, 'Post 1', 'This is the content of Post 1.');
INSERT INTO post (id_post, title, content) VALUES (2, 'Post 2', 'This is the content of Post 2.');
INSERT INTO post (id_post, title, content) VALUES (3, 'Post 3', 'This is the content of Post 3.');
ALTER SEQUENCE POST_SEQ RESTART WITH 53

INSERT INTO tag (id_tag, name) VALUES (1, 'AI');
INSERT INTO tag (id_tag, name) VALUES (2, 'Java');
INSERT INTO tag (id_tag, name) VALUES (3, 'Cats');
ALTER SEQUENCE TAG_SEQ RESTART WITH 53

INSERT INTO post_tag (id_post, id_tag) VALUES (1, 1);
INSERT INTO post_tag (id_post, id_tag) VALUES (1, 2);
INSERT INTO post_tag (id_post, id_tag) VALUES (2, 1);
INSERT INTO post_tag (id_post, id_tag) VALUES (3, 2);
INSERT INTO post_tag (id_post, id_tag) VALUES (3, 3);
