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
    (ST_GeographyFromText('POINT(22.183206 112.305145)', 4326), ST_GeographyFromText('POINT(31.938731 113.165197)', 4326), 1, 51, '2020-08-20 06:01:11', 'CREATED'),
    (ST_GeographyFromText('POINT(32.417346 105.238901)', 4326), ST_GeographyFromText('POINT(36.83408 10.04057)', 4326), 2, 52, '2020-08-21 07:02:12', 'ASSIGNED'),
    (ST_GeographyFromText('POINT(40.9178949 -8.4250467)', 4326), ST_GeographyFromText('POINT(40.7832844 22.2735926)', 4326), 3, 53, '2020-08-22 08:03:13', 'IN_PROGRESS'),
    (ST_GeographyFromText('POINT(45.5973849 5.8745347)', 4326), ST_GeographyFromText('POINT(25.8516667 114.7766667)', 4326), 4, 54, '2020-08-23 09:04:14', 'CANCELED'),
    (ST_GeographyFromText('POINT(50.7720302 46.0479668)', 4326), ST_GeographyFromText('POINT(31.69948 35.18047)', 4326), 5, 55, '2020-08-24 10:05:15', 'FINISHED'),
    (ST_GeographyFromText('POINT(15.4726227 -86.2945627)', 4326), ST_GeographyFromText('POINT(49.5882649 17.3525487)', 4326), 6, 56, '2020-08-25 11:06:16', 'ABORTED'),
    (ST_GeographyFromText('POINT(43.285793 17.0893798)', 4326), ST_GeographyFromText('POINT(35.1852624 35.9533322)', 4326), 7, null, '2020-08-26 12:07:17', 'CREATED');