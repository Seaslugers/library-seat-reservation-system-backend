insert into tbl_student (stuNum, name, password)
values ('01010101', '01010101', 'test123'),
       ('01010102', '01010102', 'test123'),
       ('01010103', '01010103', 'test123'),
       ('01010104', '01010104', 'test123'),
       ('01010105', '01010105', 'test123');

insert into tbl_reservation_rules (
    id,
    maxReservationHours,
    minAdvanceMinutes,
    openingTime,
    closingTime,
    preReservationReminderMinutes,
    postReservationReminderMinutes,
    autoCancelMinutes,
    reminderEmail,
    reminderWeChat,
    reminderSMS,
    checkinCodeWeb,
    checkinQRCode,
    checkinCodeRefreshMinutes,
    violationRecordEnabled,
    violationPolicy,
    violationThreshold
)
values (
    1,
    4,
    0,
    '07:00',
    '22:00',
    15,
    10,
    15,
    true,
    true,
    false,
    true,
    true,
    1440,
    true,
    'limit',
    3
);

insert into tbl_study_room (id, buildingNum, classRoomNum)
values
    (1, '教学楼A', '101'),
    (2, '教学楼A', '102'),
    (3, '图书馆', '201');

insert into tbl_seat (
    id,
    studyRoomId,
    seatNumber,
    status,
    reservedBy,
    reservationTime,
    checkinTime,
    createdAt,
    hasPowerSocket,
    hasMobileTrackSocket,
    isNearWindow
)
values
    (1, 1, 'A101-001', 'available', null, null, null, timestamp '2026-05-18 08:00:00', true, false, true),
    (2, 1, 'A101-002', 'reserved', '张三', timestamp '2026-05-18 14:00:00', null, timestamp '2026-05-18 08:05:00', true, false, false),
    (3, 1, 'A101-003', 'occupied', '李四', timestamp '2026-05-18 13:30:00', timestamp '2026-05-18 13:32:00', timestamp '2026-05-18 08:10:00', false, true, false),
    (4, 2, 'A102-001', 'maintenance', null, null, null, timestamp '2026-05-18 08:15:00', false, false, true),
    (5, 2, 'A102-002', 'available', null, null, null, timestamp '2026-05-18 08:20:00', true, true, false),
    (6, 3, 'L201-001', 'reserved', '王五', timestamp '2026-05-18 15:00:00', null, timestamp '2026-05-18 08:25:00', false, false, true),
    (7, 3, 'L201-002', 'occupied', '赵六', timestamp '2026-05-18 15:30:00', timestamp '2026-05-18 15:35:00', timestamp '2026-05-18 08:30:00', true, false, false),
    (8, 3, 'L201-003', 'available', null, null, null, timestamp '2026-05-18 08:35:00', false, true, true);