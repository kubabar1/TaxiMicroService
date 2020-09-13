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
    ('POINT(20.989324 52.246456)', 'POINT(21.096957 52.192845)', 1, 51, '2020-08-20 06:01:11', 'ASSIGNED'),
    ('POINT(20.998274 52.251428)', 'POINT(21.104665 52.246442)', 2, 52, '2020-08-21 07:02:12', 'ASSIGNED'),
    ('POINT(21.027313 52.248855)', 'POINT(21.102365 52.246422)', 12, 52, '2020-08-21 07:02:12', 'ASSIGNED'),
    ('POINT(21.026713 52.248911)', 'POINT(20.947007 52.353646)', 3, 53, '2020-08-22 08:03:13', 'IN_PROGRESS'),
    ('POINT(21.020324 52.267547)', 'POINT(20.817593 52.373052)', 4, 54, '2020-08-23 09:04:14', 'CANCELED'),
    ('POINT(20.951091 52.288041)', 'POINT(20.857871 52.217071)', 5, 55, '2020-08-24 10:05:15', 'FINISHED'),
    ('POINT(20.991563 52.159067)', 'POINT(21.040312 52.146710)', 6, 56, '2020-08-25 11:06:16', 'ABORTED'),
    ('POINT(21.066712 52.184786)', 'POINT(21.032565 52.157512)', 7, null, '2020-08-26 12:07:17', 'CREATED'),
    ('POINT(21.026713 52.248911)', 'POINT(20.947007 52.353646)', 22, 59, '2020-08-22 08:03:13', 'IN_PROGRESS');