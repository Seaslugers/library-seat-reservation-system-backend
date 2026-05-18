-- RBAC schema draft
-- S-001: 管理端用户表
create table if not exists tbl_admin_user (
    id            int          not null auto_increment,
    username      varchar(32)  not null,
    password_hash varchar(128) not null,
    display_name  varchar(32)  not null,
    status        tinyint      not null default 1,
    created_at    timestamp    not null default current_timestamp,
    updated_at    timestamp    not null default current_timestamp,
    primary key (id),
    unique (username)
);

-- S-002: 角色表
create table if not exists tbl_role (
    id          int          not null auto_increment,
    role_code   varchar(64)  not null,
    role_name   varchar(64)  not null,
    role_desc   varchar(255),
    primary key (id),
    unique (role_code)
);

-- S-003: 权限表
create table if not exists tbl_permission (
    id           int          not null auto_increment,
    perm_code    varchar(64)  not null,
    perm_name    varchar(64)  not null,
    perm_desc    varchar(255),
    primary key (id),
    unique (perm_code)
);

-- S-004: 用户-角色关联
create table if not exists tbl_admin_user_role (
    id            int not null auto_increment,
    admin_user_id int not null,
    role_id       int not null,
    primary key (id),
    unique (admin_user_id, role_id)
);

-- S-005: 角色-权限关联
create table if not exists tbl_role_permission (
    id            int not null auto_increment,
    role_id       int not null,
    permission_id int not null,
    primary key (id),
    unique (role_id, permission_id)
);

-- S-006: 种子角色
merge into tbl_role (id, role_code, role_name, role_desc) key (id) values
(1, 'ROLE_SUPER_ADMIN', '超级管理员', '全权限'),
(2, 'ROLE_OPERATOR', '运营管理员', '业务操作权限'),
(3, 'ROLE_AUDITOR', '审计员', '只读权限');

-- S-007: 种子权限
merge into tbl_permission (id, perm_code, perm_name, perm_desc) key (id) values
(1, 'PERM_BOOKING_VIEW', '查看预约记录', '查看预约记录'),
(2, 'PERM_VIOLATION_VIEW', '查看违约记录', '查看违约记录'),
(3, 'PERM_BOOKING_CREATE_FOR_USER', '代用户预约', '代用户预约'),
(4, 'PERM_BOOKING_CANCEL_FOR_USER', '代用户取消预约', '代用户取消预约'),
(5, 'PERM_SEAT_MANAGE', '座位管理', '座位登记/注销'),
(6, 'PERM_STUDYROOM_MANAGE', '自习室管理', '自习室登记/注销'),
(7, 'PERM_SYSTEM_PARAM_MANAGE', '系统参数管理', '调整系统参数'),
(8, 'PERM_USER_ROLE_ASSIGN', '用户角色分配', '分配管理角色');

-- S-008: 种子角色-权限关联
merge into tbl_role_permission (role_id, permission_id) key (role_id, permission_id) values
(1, 1),(1, 2),(1, 3),(1, 4),(1, 5),(1, 6),(1, 7),(1, 8),
(2, 1),(2, 2),(2, 3),(2, 4),(2, 5),(2, 6),
(3, 1),(3, 2);

-- S-009: 种子管理员账号（密码哈希在实现阶段替换为真实 BCrypt 值）
merge into tbl_admin_user (id, username, password_hash, display_name, status) key (id) values
(1, 'admin', '{bcrypt}REPLACE_ME', '系统管理员', 1),
(2, 'operator1', '{bcrypt}REPLACE_ME', '运营一号', 1),
(3, 'auditor1', '{bcrypt}REPLACE_ME', '审计一号', 1);

-- S-010: 种子用户-角色关联
merge into tbl_admin_user_role (admin_user_id, role_id) key (admin_user_id, role_id) values
(1, 1),
(2, 2),
(3, 3);
