insert into client values (1, 'Very Big Client, Inc.');
select setval('client_id_seq', 2);

insert into project values (1, 'Test Project', 1);
insert into project values (2, 'Another Test Project', 1);
select setval('project_id_seq', 3);

insert into contact values (1, 'John', 'john@verybigclient.com', 1);
insert into contact values (2, 'Melissa', 'melissa@verybigclient.com', 1);
select setval('contact_id_seq', 3);




