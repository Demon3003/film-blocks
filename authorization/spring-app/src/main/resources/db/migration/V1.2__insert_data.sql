-- PERMISSION
insert into PERMISSION values (nextval('permission_seq'), 'sysadm');
insert into PERMISSION values (2, 'all:read');
insert into PERMISSION values (3, 'all:write');

-- ROLE
insert into ROLE(id, name) values (nextval('role_seq'), 'Admin');
insert into ROLE(id, name) values (2, 'Support team');
insert into ROLE(id, name) values (3, 'CBM');

-- ROLE_PERMISSION
insert into ROLE_PERMISSION(role_id, permission_id) values (1, 1);
insert into ROLE_PERMISSION(role_id, permission_id) values (1, 3);
insert into ROLE_PERMISSION(role_id, permission_id) values (2, 1);
insert into ROLE_PERMISSION(role_id, permission_id) values (2, 2);
insert into ROLE_PERMISSION(role_id, permission_id) values (3, 2);

-- USERS

insert into USERS(id, login, password, email, status_id, role_id)
values (nextval('user_seq'), 'Dmytro', '$2a$10$DTYpeWMC9MSvrFWCd/zaM.Pfdpu5kNC1QXLDZSLWL0cjcntv7hc.2', 'zhurawell@gmail.com', 2, 1);

insert into USERS(id, login, password, email, status_id, role_id)
values (2, 'Test', '$2a$10$DTYpeWMC9MSvrFWCd/zaM.Pfdpu5kNC1QXLDZSLWL0cjcntv7hc.2', 'test@gmail.com', 2, 3);

insert into USERS(id, login, password, email, status_id, role_id)
values (3, 'Alex', '$2a$10$DTYpeWMC9MSvrFWCd/zaM.Pfdpu5kNC1QXLDZSLWL0cjcntv7hc.2', 'alex@gmail.com', 2, 2);