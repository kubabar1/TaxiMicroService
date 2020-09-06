insert into
    booking_status (id, description)
values
    ('CREATED', 'Booking created'),
    ('ASSIGNED', 'Booking assigned to driver'),
    ('IN_PROGRESS', 'Booking in progress'),
    ('CANCELED', 'Booking canceled'),
    ('FINISHED', 'Booking finished'),
    ('ABORTED', 'Booking aborted');

insert into
    bookings (start_point, finish_point, passenger_id, driver_id, creation_date, status)
values
    (POINT(22.183206, 112.305145), POINT(31.938731, 113.165197), 1, 51, '2020-08-20 06:01:11', 'CREATED'),
    (POINT(32.417346, 105.238901), POINT(36.83408, 10.04057), 2, 52, '2020-08-21 07:02:12', 'ASSIGNED'),
    (POINT(40.9178949, -8.4250467), POINT(40.7832844, 22.2735926), 3, 53, '2020-08-22 08:03:13', 'IN_PROGRESS'),
    (POINT(45.5973849, 5.8745347), POINT(25.8516667, 114.7766667), 4, 54, '2020-08-23 09:04:14', 'CANCELED'),
    (POINT(50.7720302, 46.0479668), POINT(31.69948, 35.18047), 5, 55, '2020-08-24 10:05:15', 'FINISHED'),
    (POINT(15.4726227, -86.2945627), POINT(49.5882649, 17.3525487), 6, 56, '2020-08-25 11:06:16', 'ABORTED'),
    (POINT(43.285793, 17.0893798), POINT(35.1852624, 35.9533322), 7, null, '2020-08-26 12:07:17', 'CREATED');