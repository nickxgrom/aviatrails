use aviatrails;

set character set utf8;

insert into location(id, country, airport_name, airport_code, city_code, latitude, longitude)
values
    (default, "kz", "almaty", 322,1337, 1337, 1);

insert into route(id, amount, coast, distance, flight_startutc, flight_time_minutes, departure, destination)
values
    (default, 1337, 1337, 1337, 1, 1, 1, 1),
    (default, 1337, 1337, 1337, 1, 1, 1, 1),
    (default, 1337, 1337, 1337, 1, 1, 1, 1);

insert into user(id, balance, email, name, role)
values
    (default, 1337, "sadfasd", "dsafadsf", null),
    (default, 1337, "sadfasd", "dsafadsf", null);
