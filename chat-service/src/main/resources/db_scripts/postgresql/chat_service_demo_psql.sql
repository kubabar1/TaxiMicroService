insert into
    message_status (id, name)
values
    ('SENT', 'Notification sent to user and saved in database'),
    ('READ', 'Notification read by user');

insert into
    messages (status, content, sender, booking_id, creation_date)
values
    ('READ', 'Where are You now?', 'adam123', 1, '2020-09-14 09:01:44'),
    ('READ', 'Nearby train station, and You?', 'tpoli28', 1, '2020-09-14 09:02:50'),
    ('SENT', 'In front of the shopping centre.', 'adam123', 1, '2020-09-14 09:04:14');
