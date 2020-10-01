insert into
    message_status (id, name)
values
    ('SENT', 'Notification sent to user and saved in database'),
    ('READ', 'Notification read by user');

insert into
    messages (status, sender_id, content, booking_id, creation_date)
values
    ('READ', 'adam123', 'Where are You now?', 1, '2020-09-14 09:01:44'),
    ('READ', 'tpoli28', 'Nearby train station, and You?', 1, '2020-09-14 09:02:50'),
    ('SENT', 'adam123', 'In front of the shopping centre.', 1, '2020-09-14 09:04:14');
