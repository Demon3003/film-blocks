-- PERMISSION
insert into PERMISSION values (1, 'sysadm');
insert into PERMISSION values (2, 'all:read');
insert into PERMISSION values (3, 'all:write');

-- ROLE
insert into ROLE(id, name) values (1, 'Admin');
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
values (1, 'Dmytro', 'L,l,sdsdc32dc_sd', 'zhurawell@gmail.com', 1, 1);

insert into USERS(id, login, password, email, status_id, role_id)
values (2, 'Test', '1234', 'test@gmail.com', 1, 3);

insert into USERS(id, login, password, email, status_id, role_id)
values (3, 'Alex', '1234', 'alex@gmail.com', 1, 2);