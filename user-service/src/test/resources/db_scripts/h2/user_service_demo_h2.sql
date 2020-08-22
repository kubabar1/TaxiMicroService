insert into
    users (user_name, email, name, surname, pesel, birth_date, password, password_salt, creation_date)
values
    ('adam123', 'adam@qwerty.com', 'Adam', 'Kowalski', '92111000000', '1992-11-10', 'qwerty', 'dsfEfds3', '2013-02-14 06:01:17'),
    ('jtytcomb0', 'jtytcomb0@narod.ru', 'Josias', 'Tytcomb', '95636516335', '1993-11-17', '7a1QTI6c', 'tI8ZAC0J', '2013-02-14 06:01:17'),
	('tdoggerell1', 'tdoggerell1@usnews.com', 'Tedd', 'Doggerell', '58669896492', '2007-09-30', 'cwPn2KDV', 'mkMDbhOC', '2016-08-07 12:05:09'),
	('bheikkinen2', 'bheikkinen2@independent.co.uk', 'Blair', 'Heikkinen', '40316263281', '1993-12-07', 'Mt81kTlO', '0624ThJZ3k', '2014-10-04 23:42:14'),
	('ndowning3', 'ndowning3@imdb.com', 'Nate', 'Downing', '44811174502', '2000-11-01', 'UXOXlkU6w', 'YD1KWHPgz8', '2019-10-29 10:51:55'),
	('ktomczykowski4', 'ktomczykowski4@icq.com', 'Keith', 'Tomczykowski', '19044065094', '1992-08-20', 'FaW45K', 'rCc7d4ROjz', '2018-12-20 00:56:37'),
	('eabelovitz5', 'eabelovitz5@sciencedaily.com', 'Efrem', 'Abelovitz', '96819407430', '2015-04-15', 'xyhSuGTWud', 'VzNqBJ1N', '2013-01-21 02:27:30'),
	('abaccus6', 'abaccus6@webnode.com', 'Andonis', 'Baccus', '50214187615', '1992-08-27', 'pNvXbt', 'PTSFnF4clD', '2018-10-06 23:38:18'),
	('iwawer7', 'iwawer7@qq.com', 'Iago', 'Wawer', '75414513921', '1999-11-20', '2gjCKImyZxH', 'dZBLPZ', '2015-10-19 21:05:04'),
	('jbelhomme8', 'jbelhomme8@cbsnews.com', 'Jen', 'Belhomme', '37071874422', '1990-08-31', 'Ev1WCH6nGH', 'PnUeDT', '2013-04-26 08:19:13'),
	('qwedsasdf', 'sdf@cbsnews.com', 'Asdf', 'sdf', '32145678909', '1991-04-22', 'DFSDGdadfs', 'Dfdsdf', '2012-11-23 04:15:12');

insert into
    roles(name)
values
    ('passenger'),
    ('driver'),
    ('admin'),
    ('accountant');

insert into
    user_roles(user_id, role_id)
values
	(1, 1),
	(1, 2),
	(1, 3),
	(1, 4),
	(2, 1),
	(3, 1),
	(4, 1),
	(5, 1),
	(6, 1),
	(7, 1),
	(8, 1),
	(9, 1),
	(5, 2),
	(5, 3),
	(5, 4);

insert into
    languages(language_code,name)
values
    ('en', 'English'),
    ('pl', 'Polish'),
    ('de', 'German'),
    ('ru', 'Russian'),
    ('es', 'Spanish');

insert into
    appearances(appearance_code, name)
values
    ('lt', 'light'),
    ('dk', 'dark');

insert into
    user_settings (user_id, language_code, appearance_code)
values
	(1, 'en', 'lt'),
	(2, 'en', 'lt'),
	(3, 'en', 'lt'),
	(4, 'en', 'lt'),
	(5, 'en', 'lt'),
	(6, 'en', 'lt'),
	(7, 'en', 'lt'),
	(8, 'en', 'lt'),
	(9, 'en', 'dk'),
	(10, 'en', 'dk');