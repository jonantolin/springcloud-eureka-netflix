INSERT INTO `usuarios` (username, password, enabled, nombre, apellido, email) VALUES ('jon', '12345', 1, 'Jon', 'Antolin', 'jonantolin@gmail.com');
INSERT INTO `usuarios` (username, password, enabled, nombre, apellido, email) VALUES ('rosi', '12345', 1, 'Rosi', 'Mulas', 'rosimulas@catmail.com');

INSERT INTO `roles` (nombre) VALUES ('ROLE_USER');
INSERT INTO `roles` (nombre) VALUES ('ROLE_ADMIN');

INSERT INTO `usuarios_roles` (usuario_id, rol_id) VALUES (1, 1);
INSERT INTO `usuarios_roles` (usuario_id, rol_id) VALUES (1, 2);
INSERT INTO `usuarios_roles` (usuario_id, rol_id) VALUES (2, 1);