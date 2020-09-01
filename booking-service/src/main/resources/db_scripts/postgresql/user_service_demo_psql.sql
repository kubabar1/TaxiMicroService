insert into
    booking_status (name, description)
values
    (''CREATED'', ''Booking created''),
    (''ASSIGNED'', ''Booking assigned to driver''),
    (''IN_PROGRESS'', ''Booking in progress''),
    (''CANCELED'', ''Booking canceled''),
    (''FINISHED'', ''Booking finished''),
    (''ABORTED'', ''Booking aborted'');


insert into
    localisations (latitude, longitude)
values
	(22.183206, 112.305145),
	(31.938731, 113.165197),
	(32.417346, 105.238901),
	(40.9178949, -8.4250467),
	(45.5973849, 5.8745347),
	(50.7720302, 46.0479668),
	(15.4726227, -86.2945627),
	(43.285793, 17.0893798),
	(35.1852624, 35.9533322),
	(36.83408, 10.04057),
	(40.7832844, 22.2735926),
	(25.8516667, 114.7766667),
	(31.69948, 35.18047),
	(-13.0719408, -39.5099186),
	(49.5882649, 17.3525487),
	(-18.8424187, -42.8247877),
	(45.2068153, 73.9309807),
	(54.7305189, 25.2361291),
	(43.8533322, 25.9522679),
	(46.748522, 82.978928),
	(38.3237571, -108.1821561),
	(-41.1822198, 174.8294181),
	(35.3179014, 136.9037455),
	(31.869937, 35.065202),
	(34.198019, 108.879265),
	(35.6868993, 74.6291131),
	(23.460728, 113.151596),
	(48.5556837, -71.3205493),
	(12.225759, 21.4126837),
	(55.6152783, 37.9416367),
	(45.396383, 4.3258963),
	(-7.4417327, 111.1056539),
	(39.7284944, -121.8374777),
	(21.3926035, -77.9053182),
	(35.8523575, 66.5223902),
	(35.84763, 14.46824),
	(13.6132538, 100.8282161),
	(-6.898384, 111.885002),
	(53.5733796, 18.0101779),
	(11.2975389, 6.4956455),
	(18.7853365, 105.6418428),
	(42.1333129, 24.5376727),
	(21.956492, 112.805131),
	(-10.2122281, -36.8371852),
	(-7.2026914, 108.1013637),
	(15.9475779, -85.6871451),
	(47.6264367, 34.5297956),
	(13.8781715, -89.1610748),
	(-16.4962793, -68.1372784),
	(29.079175, 119.64742),
	(52.08234, 5.1175293),
	(-22.1507823, 166.8808338),
	(36.4203181, 140.5177769),
	(46.8435126, 74.9809805),
	(-18.3349525, -59.7602664),
	(62.2245877, 40.2634504),
	(49.2515468, 17.514286),
	(27.550779, 14.2686658),
	(28.098056, 101.579377),
	(35.9928004, 37.0507676),
	(37.9510555, 23.5280223),
	(34.0624686, 51.4782228),
	(35.3837308, 36.4851204),
	(52.476835, 17.0263373),
	(14.6099284, 121.0348405),
	(39.9535181, 44.5519782),
	(-28.9510773, 28.2526623),
	(-6.5301408, -76.7206652),
	(-14.3322488, -56.6513853),
	(-1.6635508, -78.654646),
	(42.021602, 121.670273),
	(37.1766607, 42.1355904),
	(-8.2637032, 113.5371368),
	(6.6560144, 126.0755806),
	(56.7034937, 41.3503153),
	(49.9344975, 13.390365),
	(51.3948213, 6.6518329),
	(28.133945, 111.656407),
	(40.7493096, -73.8213213),
	(64.9142998, 77.7663297),
	(44.904219, 78.2166516),
	(-8.0212573, -77.7294705),
	(2.7121164, 97.9157099),
	(50.4254568, 30.5176371),
	(34.08611, 106.585112),
	(-14.0932177, -75.1716066),
	(-8.370899, 123.7014716),
	(58.7132844, 15.753473),
	(45.51596, 83.867628),
	(30.262907, 84.475645),
	(36.3681952, 138.3175811),
	(35.8925, 14.48278),
	(51.55352, 118.3446),
	(53.5650926, -7.7655701),
	(44.61685, -65.76555),
	(30.378326, 111.450006),
	(13.7768017, 100.5593109),
	(43.3815181, 133.9055702),
	(39.4825368, -8.609605),
	(46.4922368, 15.8777956);


insert into
    bookings (start_point, finish_point, user_id, driver_id, creation_date, status)
values
    (1, 2, 1, 51, ''2020-08-20 06:01:11'', 1),
    (3, 4, 2, 52, ''2020-08-21 07:02:12'', 2),
    (5, 6, 3, 53, ''2020-08-22 08:03:13'', 3),
    (7, 8, 4, 54, ''2020-08-23 09:04:14'', 4),
    (9, 10, 5, 55, ''2020-08-24 10:05:15'', 5),
    (11, 12, 6, 56, ''2020-08-25 11:06:16'', 6),
    (13, 14, 7, null, ''2020-08-26 12:07:17'', 1);