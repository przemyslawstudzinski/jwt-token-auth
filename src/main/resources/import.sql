--admin
INSERT INTO app_user (id, email, first_name, last_name, password, username) VALUES ('8dc6a8e3-b322-4145-b9c3-117829533c76', 'testemail1@gmail.com', 'Mark', 'Bell', '$2a$04$9oEKUqJfYWKsc4vl51QCrO3f40Ae3XmEOjiENF2Q/zJ0bNI8cI5N.', 'mark');

--supervisor
INSERT INTO app_user (id, email, first_name, last_name, password, username) VALUES ('8846e7c4-a3f3-4199-998f-b06806ec8c57', 'testemail2@gmail.com', 'Michael', 'Adams', '$2a$04$9oEKUqJfYWKsc4vl51QCrO3f40Ae3XmEOjiENF2Q/zJ0bNI8cI5N.', 'michael');

--manager
INSERT INTO app_user (id, email, first_name, last_name, password, username) VALUES ('f3d07a1d-894f-450f-9b4d-c20fce98f82c', 'testemail3@gmail.com', 'John', 'Allen', '$2a$04$9oEKUqJfYWKsc4vl51QCrO3f40Ae3XmEOjiENF2Q/zJ0bNI8cI5N.', 'john');

--employee
INSERT INTO app_user (id, email, first_name, last_name, password, username) VALUES ('a39bdfad-8b55-4e0c-b92a-a67ba3510f47', 'testemail4@gmail.com', 'Mary', 'Baker', '$2a$04$9oEKUqJfYWKsc4vl51QCrO3f40Ae3XmEOjiENF2Q/zJ0bNI8cI5N.', 'mary');

--guest
INSERT INTO app_user (id, email, first_name, last_name, password, username) VALUES ('2239433a-de80-47bf-8172-2246c163785a', 'testemail5@gmail.com', 'Ben', 'Foster', '$2a$04$9oEKUqJfYWKsc4vl51QCrO3f40Ae3XmEOjiENF2Q/zJ0bNI8cI5N.', 'ben');

INSERT INTO role (id, decryption, name) VALUES ('aae53eb7-d4fe-4207-ac97-4ae153fd22b5', null, 'ADMIN');
INSERT INTO role (id, decryption, name) VALUES ('14cb0f9c-b54a-41e6-84c7-7b111e61a237', null, 'MANAGER');
INSERT INTO role (id, decryption, name) VALUES ('96a16680-23de-4712-8422-48e66f759fdb', null, 'SUPERVISOR');
INSERT INTO role (id, decryption, name) VALUES ('84f9c8d7-2997-4d80-96d9-fb4efe72e9ff', null, 'EMPLOYEE');
INSERT INTO role (id, decryption, name) VALUES ('e793f3e9-2e04-4ea6-8e7c-30179e0bfe47', null, 'GUEST');

INSERT INTO permission (id, decryption, name) VALUES ('2c8782a8-2a7b-4f6f-a656-15df6c85e65f', null, 'INFO');
INSERT INTO permission (id, decryption, name) VALUES ('9d0be514-cf75-4a5f-ad54-8f9a1c27ef9b', null, 'READ');
INSERT INTO permission (id, decryption, name) VALUES ('c6547efa-ed96-49d1-b3d8-40673bc40772', null, 'CREATE');
INSERT INTO permission (id, decryption, name) VALUES ('3032afb6-218e-4bee-9766-33fad2446d80', null, 'DELETE');

--guest -> info
INSERT INTO permission_assignment (role_id, permission_id) VALUES ('e793f3e9-2e04-4ea6-8e7c-30179e0bfe47', '2c8782a8-2a7b-4f6f-a656-15df6c85e65f');

--employee -> read
INSERT INTO permission_assignment (role_id, permission_id) VALUES ('84f9c8d7-2997-4d80-96d9-fb4efe72e9ff', '9d0be514-cf75-4a5f-ad54-8f9a1c27ef9b');

--supervisor and manager -> read, create
INSERT INTO permission_assignment (role_id, permission_id) VALUES ('96a16680-23de-4712-8422-48e66f759fdb', '9d0be514-cf75-4a5f-ad54-8f9a1c27ef9b');
INSERT INTO permission_assignment (role_id, permission_id) VALUES ('96a16680-23de-4712-8422-48e66f759fdb', 'c6547efa-ed96-49d1-b3d8-40673bc40772');

INSERT INTO permission_assignment (role_id, permission_id) VALUES ('14cb0f9c-b54a-41e6-84c7-7b111e61a237', '9d0be514-cf75-4a5f-ad54-8f9a1c27ef9b');
INSERT INTO permission_assignment (role_id, permission_id) VALUES ('14cb0f9c-b54a-41e6-84c7-7b111e61a237', 'c6547efa-ed96-49d1-b3d8-40673bc40772');

--admin -> info, read, create, delete
INSERT INTO permission_assignment (role_id, permission_id) VALUES ('aae53eb7-d4fe-4207-ac97-4ae153fd22b5', '2c8782a8-2a7b-4f6f-a656-15df6c85e65f');
INSERT INTO permission_assignment (role_id, permission_id) VALUES ('aae53eb7-d4fe-4207-ac97-4ae153fd22b5', '9d0be514-cf75-4a5f-ad54-8f9a1c27ef9b');
INSERT INTO permission_assignment (role_id, permission_id) VALUES ('aae53eb7-d4fe-4207-ac97-4ae153fd22b5', 'c6547efa-ed96-49d1-b3d8-40673bc40772');
INSERT INTO permission_assignment (role_id, permission_id) VALUES ('aae53eb7-d4fe-4207-ac97-4ae153fd22b5', '3032afb6-218e-4bee-9766-33fad2446d80');


INSERT INTO role_assignment (user_id, role_id) VALUES ('2239433a-de80-47bf-8172-2246c163785a', 'e793f3e9-2e04-4ea6-8e7c-30179e0bfe47');
INSERT INTO role_assignment (user_id, role_id) VALUES ('a39bdfad-8b55-4e0c-b92a-a67ba3510f47', '84f9c8d7-2997-4d80-96d9-fb4efe72e9ff');
INSERT INTO role_assignment (user_id, role_id) VALUES ('f3d07a1d-894f-450f-9b4d-c20fce98f82c', '96a16680-23de-4712-8422-48e66f759fdb');
INSERT INTO role_assignment (user_id, role_id) VALUES ('8846e7c4-a3f3-4199-998f-b06806ec8c57', '14cb0f9c-b54a-41e6-84c7-7b111e61a237');
INSERT INTO role_assignment (user_id, role_id) VALUES ('8dc6a8e3-b322-4145-b9c3-117829533c76', 'aae53eb7-d4fe-4207-ac97-4ae153fd22b5');
