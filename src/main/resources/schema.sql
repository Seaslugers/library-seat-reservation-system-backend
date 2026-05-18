drop table if exists tbl_student;
create table tbl_student
(
    id       int         not null auto_increment,
    stuNum   varchar(16) not null,
    name     varchar(16) not null,
    password varchar(16) not null,
    primary key (id),
    unique (stuNum)
);

drop table if exists tbl_studyroom;
create table tbl_study_room
(
    id           int         not null auto_increment,
    buildingNum  varchar(16) not null,
    classRoomNum varchar(16) not null,
    primary key (id)
);

drop table if exists tbl_seat;
create table tbl_seat
(
    id          int not null auto_increment,
    studyRoomId int not null,
    primary key (id, studyRoomId)
);

drop table if exists tbl_booking_xxx;
create table tbl_booking_xxx
(
    id            int not null auto_increment,
    seatId        int not null,
    bookingPeriod int not null,
    stuId         int not null,
    primary key (id)
);

drop table if exists tbl_admin_user_role;
drop table if exists tbl_role_permission;
drop table if exists tbl_admin_user;
drop table if exists tbl_role;
drop table if exists tbl_permission;

create table tbl_admin_user
(
    id            int          not null auto_increment,
    username      varchar(32)  not null,
    password_hash varchar(128) not null,
    display_name  varchar(32)  not null,
    status        tinyint      not null default 1,
    primary key (id),
    unique (username)
);

create table tbl_role
(
    id        int         not null auto_increment,
    role_code varchar(64) not null,
    role_name varchar(64) not null,
    role_desc varchar(255),
    primary key (id),
    unique (role_code)
);

create table tbl_permission
(
    id        int         not null auto_increment,
    perm_code varchar(64) not null,
    perm_name varchar(64) not null,
    perm_desc varchar(255),
    primary key (id),
    unique (perm_code)
);

create table tbl_admin_user_role
(
    id            int not null auto_increment,
    admin_user_id int not null,
    role_id       int not null,
    primary key (id),
    unique (admin_user_id, role_id)
);

create table tbl_role_permission
(
    id            int not null auto_increment,
    role_id       int not null,
    permission_id int not null,
    primary key (id),
    unique (role_id, permission_id)
);
