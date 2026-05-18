insert into tbl_student (stuNum, name, password)
values ('01010101', '01010101', 'test123'),
       ('01010102', '01010102', 'test123'),
       ('01010103', '01010103', 'test123'),
       ('01010104', '01010104', 'test123'),
       ('01010105', '01010105', 'test123');

insert into tbl_role (id, role_code, role_name, role_desc)
values (1, 'ROLE_SUPER_ADMIN', '超级管理员', '全权限'),
       (2, 'ROLE_OPERATOR', '运营管理员', '业务操作权限'),
       (3, 'ROLE_AUDITOR', '审计员', '只读权限');

insert into tbl_permission (id, perm_code, perm_name, perm_desc)
values (1, 'PERM_BOOKING_VIEW', '查看预约记录', '查看预约记录'),
       (2, 'PERM_VIOLATION_VIEW', '查看违约记录', '查看违约记录'),
       (3, 'PERM_BOOKING_CREATE_FOR_USER', '代用户预约', '代用户预约'),
       (4, 'PERM_BOOKING_CANCEL_FOR_USER', '代用户取消预约', '代用户取消预约'),
       (5, 'PERM_SEAT_MANAGE', '座位管理', '座位登记/注销'),
       (6, 'PERM_STUDYROOM_MANAGE', '自习室管理', '自习室登记/注销'),
       (7, 'PERM_SYSTEM_PARAM_MANAGE', '系统参数管理', '调整系统参数'),
       (8, 'PERM_USER_ROLE_ASSIGN', '用户角色分配', '分配管理角色');

insert into tbl_role_permission (role_id, permission_id)
values (1, 1), (1, 2), (1, 3), (1, 4), (1, 5), (1, 6), (1, 7), (1, 8),
       (2, 1), (2, 2), (2, 3), (2, 4), (2, 5), (2, 6),
       (3, 1), (3, 2);

insert into tbl_admin_user (id, username, password_hash, display_name, status)
values (1, 'admin', 'test123', '系统管理员', 1),
       (2, 'operator1', 'test123', '运营一号', 1),
       (3, 'auditor1', 'test123', '审计一号', 1);

insert into tbl_admin_user_role (admin_user_id, role_id)
values (1, 1),
       (2, 2),
       (3, 3);
