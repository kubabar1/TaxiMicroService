insert into
    notification_type (id, name)
values
    ('INFO', 'Info message'),
    ('WARNING', 'Warning message'),
    ('ERROR', 'Error message');

insert into
    notification_status (id, name)
values
    ('SENT', 'Notification sent to user and saved in database'),
    ('READ', 'Notification read by user'),
    ('DELETED', 'Notification deleted by user');

insert into
    notifications (type, status, content, sender, receiver_username, creation_date)
values
    ('INFO', 'SENT', 'Driver Bruce Wayne assigned to your booking', 'booking-service', 'adam123', '2020-09-14 09:01:44'),
    ('INFO', 'SENT', 'Driver John Wayne assigned to your booking', 'booking-service', 'adam123', '2020-09-15 10:02:11'),
    ('INFO', 'SENT', 'Driver John Wayne assigned to your booking', 'booking-service', 'john321', '2020-09-11 16:03:21'),
    ('INFO', 'SENT', 'Payment for booking booking123 accepted', 'payment-service', 'adam123', '2020-09-17 12:04:22'),
    ('INFO', 'READ', 'Payment for booking booking123 accepted', 'payment-service', 'adam123', '2020-09-18 13:05:43'),
    ('INFO', 'READ', 'Payment for booking booking456 accepted', 'payment-service', 'adam123', '2020-09-18 14:05:43'),
    ('INFO', 'READ', 'Payment for booking booking456 accepted', 'payment-service', 'adam1234', '2020-09-18 14:05:43'),
    ('INFO', 'DELETED', 'Payment for booking booking123 accepted', 'payment-service', 'adam123', '2020-09-19 14:06:21'),
    ('INFO', 'DELETED', 'Payment for booking booking123 accepted', 'payment-service', 'adam123', '2020-09-20 14:06:21'),
    ('ERROR', 'SENT', 'Payment for booking booking123 not accepted', 'payment-service', 'adam123', '2020-09-20 15:07:15'),
    ('ERROR', 'SENT', 'Payment for booking booking123 not accepted', 'payment-service', 'adam123', '2020-09-20 15:07:15'),
    ('WARNING', 'READ', 'Someone logged to Your account', 'user-service', 'adam123', '2020-09-21 17:12:11'),
    ('WARNING', 'READ', 'Someone logged to Your account', 'user-service', 'adam123', '2020-09-21 17:12:11');
