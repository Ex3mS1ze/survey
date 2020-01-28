INSERT INTO public.users (id, email, password, first_name, activated, phone_number, registration_date, gender,
                          second_name, last_visit_date)
VALUES (1, 'TESTING@mail.ru', '$2a$10$nBqVCXJA2YPMIL2XBPgriu/SvvCiMJAzzJ/DjjGRB7iCc1SSz4yli', 'Владимир', false,
        '8999999999', '2019-11-08 23:11:37.65+07', 'М', 'Круглов', '2020-01-22 18:03:04.182+07');
insert into public.activation_code (code, creation_date, user_id)
values (111222333444555, '2019-11-08 23:11:37.65+07', 1);