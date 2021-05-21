insert into client values (1, 'Very Big Client, inc.');
SELECT setval('client_id_seq', 2);

insert into project values (1, 'Test Project', 1);
insert into project values (2, 'Another Test Project', 1);
SELECT setval('project_id_seq', 3);

insert into team values (1, 'The Best Team', 1);
SELECT setval('team_id_seq', 2);

insert into team_member values (1, 'Melissa');
insert into team_member values (2, 'John');
SELECT setval('team_member_id_seq', 3);

insert into team_member_team_link values (1, 1);
insert into team_member_team_link values (1, 2);




