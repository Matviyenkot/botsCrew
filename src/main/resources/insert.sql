insert into Degrees(name)
values ('assistant'),
       ('associate professor'),
       ('professor');

insert into Lector(name, degree_id, salary)
values ('Cash', 1, 1234),
       ('Smith', 2, 3500),
       ('Adam', 2, 4444),
       ('Jack', 3, 6666),
       ('Jessica', 3, 5600),
       ('Amber', 3, 67777);

insert into Department(name, head_lector_id)
values ('Math', 2),
       ('History', 1),
       ('Architecture', 3),
       ('Physic', 4),
       ('Law', 5);

insert into Lector_in_Department(lector_id, department_id)
values
    (1, 1),
    (1, 2),
    (2, 3),
    (2, 4),
    (3, 5),
    (3, 1),
    (3, 2),
    (4, 3),
    (4, 4),
    (5, 5),
    (6, 1);