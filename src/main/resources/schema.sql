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

drop table if exists tbl_reservation_rules;
create table tbl_reservation_rules
(
    id                            int not null auto_increment,
    maxReservationHours           int not null,
    minAdvanceMinutes             int not null,
    openingTime                   varchar(8) not null,
    closingTime                   varchar(8) not null,
    preReservationReminderMinutes  int not null,
    postReservationReminderMinutes int not null,
    autoCancelMinutes             int not null,
    reminderEmail                 boolean not null,
    reminderWeChat                boolean not null,
    reminderSMS                   boolean not null,
    checkinCodeWeb                boolean not null,
    checkinQRCode                 boolean not null,
    checkinCodeRefreshMinutes     int not null,
    violationRecordEnabled        boolean not null,
    violationPolicy               varchar(16) not null,
    violationThreshold            int not null,
    primary key (id)
);

drop table if exists tbl_study_room;
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
    id                   int not null auto_increment,
    studyRoomId          int not null,
    seatNumber           varchar(16) not null,
    status               varchar(16) not null,
    reservedBy           varchar(32),
    reservationTime      timestamp,
    checkinTime          timestamp,
    createdAt            timestamp default current_timestamp,
    hasPowerSocket       boolean not null default false,
    hasMobileTrackSocket boolean not null default false,
    isNearWindow         boolean not null default false,
    primary key (id),
    constraint fk_seat_study_room foreign key (studyRoomId) references tbl_study_room (id)
);