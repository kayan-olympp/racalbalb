insert into PASSENGER
values(1,'passenger1', 'ahah');

insert into PASSENGER
values(2,'passenger2', 'uhuh');

insert into DRIVER
values(1,'driver1', 'ohoh');

insert into DRIVER
values(2,'driver2', 'eheh');

insert into JOURNEY
values(1, 'PARIS', 'LONDON', 1);

insert into JOURNEY
values(2, 'LONDON', 'PARIS', 1);

insert into JOURNEY
values(3, 'TOKYO', 'PARIS', 1);

insert into JOURNEY
values(4, 'KYOTO', 'TOKYO', 2);

insert into JOURNEY
values(5, 'TOKYO', 'PARIS', 2);

insert into JOURNEY_PASSENGER
values(2,3);

insert into JOURNEY_PASSENGER
values(1,3);

insert into JOURNEY_PASSENGER
values(2,1);