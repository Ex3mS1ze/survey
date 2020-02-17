--
-- PostgreSQL database dump
--

-- Dumped from database version 11.2
-- Dumped by pg_dump version 12.0

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.users (id, email, password, first_name, activated, phone_number, registration_date, gender, second_name, last_visit_date) VALUES (53, 'patient1@mail.ru', '$2a$10$nBqVCXJA2YPMIL2XBPgriu/SvvCiMJAzzJ/DjjGRB7iCc1SSz4yli', 'Сергей', true, '89994432424', '2019-11-08 23:11:37.65+07', 'М', 'Круглов', '2019-12-03 23:56:48.768+07');
INSERT INTO public.users (id, email, password, first_name, activated, phone_number, registration_date, gender, second_name, last_visit_date) VALUES (54, 'patient2@mail.ru', '$2a$10$nBqVCXJA2YPMIL2XBPgriu/SvvCiMJAzzJ/DjjGRB7iCc1SSz4yli', 'Анна', true, '89994432424', '2019-11-08 23:11:37.65+07', 'Ж', 'Совина', '2019-12-03 23:56:48.768+07');
INSERT INTO public.users (id, email, password, first_name, activated, phone_number, registration_date, gender, second_name, last_visit_date) VALUES (55, 'doctor2@mail.ru', '$2a$10$nBqVCXJA2YPMIL2XBPgriu/SvvCiMJAzzJ/DjjGRB7iCc1SSz4yli', 'Анастасия', true, '89994428239', '2019-11-22 20:14:47.696+07', 'Ж', 'Житник', '2019-12-04 00:07:30.881+07');
INSERT INTO public.users (id, email, password, first_name, activated, phone_number, registration_date, gender, second_name, last_visit_date) VALUES (28, 'doctor@mail.ru', '$2a$10$nBqVCXJA2YPMIL2XBPgriu/SvvCiMJAzzJ/DjjGRB7iCc1SSz4yli', 'Дмитрий', true, '899946666', '2019-11-22 20:14:47.696+07', 'М', 'Лукашев', '2020-02-05 16:37:02.304+07');
INSERT INTO public.users (id, email, password, first_name, activated, phone_number, registration_date, gender, second_name, last_visit_date) VALUES (56, 'ad@mail.ru', '$2a$10$/UH.sDum9x4cnnnqwNnNBemvNi.r67uPZS0e3GN6NrEZufu4Q0lDW', 'ad', false, '222222222222', '2019-12-05 11:47:32.84+07', 'Ж', 'ad', '2018-01-04 00:56:48.768+07');
INSERT INTO public.users (id, email, password, first_name, activated, phone_number, registration_date, gender, second_name, last_visit_date) VALUES (57, 'newone@mail.ruail', '$2a$10$QH3kpJvXMoTbwf30w.zx8O5hGmq.xL3ulUSoYtqozUQGqNmJuxEnq', 'aa', false, '2222222222222222', '2019-12-06 13:30:36.926+07', 'М', 'aa', '2019-07-04 02:55:48.768+07');
INSERT INTO public.users (id, email, password, first_name, activated, phone_number, registration_date, gender, second_name, last_visit_date) VALUES (3, 'patient@mail.ru', '$2a$10$PY6CfwUvvC6c9.dDm75XB.OKHaijs6r/ZRnXZogr3GEe9f3uQJMOG', 'Василий', true, '8999999999', '2019-11-08 23:11:37.65+07', 'М', 'Круглов', '2020-02-05 22:15:28.647+07');
INSERT INTO public.users (id, email, password, first_name, activated, phone_number, registration_date, gender, second_name, last_visit_date) VALUES (2, 'admin@mail.ru', '$2a$10$IYSECg3W61COY3ofsazV..OGZNYQHlobW2GaxFOxTAJEZVXsSL35S', 'Семен', true, '89994501020', '2019-11-07 14:43:21.939356+07', 'М', 'ВасильевЪ', '2020-02-17 23:37:38.498+07');


--
-- Data for Name: activation_code; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- Data for Name: diagnoses; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.diagnoses (id, text, score_for_low_chance, score_for_mid_chance, score_for_high_chance) VALUES (3, 'Рак кишечника', NULL, NULL, NULL);
INSERT INTO public.diagnoses (id, text, score_for_low_chance, score_for_mid_chance, score_for_high_chance) VALUES (4, 'Рак печени', NULL, NULL, NULL);
INSERT INTO public.diagnoses (id, text, score_for_low_chance, score_for_mid_chance, score_for_high_chance) VALUES (5, 'Рак пищевода', NULL, NULL, NULL);
INSERT INTO public.diagnoses (id, text, score_for_low_chance, score_for_mid_chance, score_for_high_chance) VALUES (6, 'Здоров', NULL, NULL, NULL);
INSERT INTO public.diagnoses (id, text, score_for_low_chance, score_for_mid_chance, score_for_high_chance) VALUES (1, 'Рак желудка', 5.000, 10.000, 25.000);
INSERT INTO public.diagnoses (id, text, score_for_low_chance, score_for_mid_chance, score_for_high_chance) VALUES (2, 'Рак печени', NULL, 5.000, 10.000);


--
-- Data for Name: questionnaire_types; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.questionnaire_types (id, type, description) VALUES (1, 'gastroenterological', 'Гастроэнтерологический опрос');
INSERT INTO public.questionnaire_types (id, type, description) VALUES (2, 'cardiovascular', 'Кардиологический опрос');


--
-- Data for Name: questionnaires; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.questionnaires (id, user_id, date, processed, diagnosis_id, type_id) VALUES (222, 2, '2019-12-14 01:54:53.445+07', false, NULL, 2);
INSERT INTO public.questionnaires (id, user_id, date, processed, diagnosis_id, type_id) VALUES (247, 3, '2020-01-22 16:39:18.676+07', false, NULL, 1);
INSERT INTO public.questionnaires (id, user_id, date, processed, diagnosis_id, type_id) VALUES (250, 3, '2020-01-22 16:49:44.615+07', false, NULL, 1);
INSERT INTO public.questionnaires (id, user_id, date, processed, diagnosis_id, type_id) VALUES (251, 3, '2020-01-22 16:50:37.794+07', false, NULL, 2);
INSERT INTO public.questionnaires (id, user_id, date, processed, diagnosis_id, type_id) VALUES (160, 2, '2019-12-04 16:25:31.054+07', true, 2, 1);
INSERT INTO public.questionnaires (id, user_id, date, processed, diagnosis_id, type_id) VALUES (162, 2, '2019-12-04 16:28:15.652+07', true, 5, 1);
INSERT INTO public.questionnaires (id, user_id, date, processed, diagnosis_id, type_id) VALUES (172, 2, '2019-12-06 00:46:48.437+07', false, NULL, 1);
INSERT INTO public.questionnaires (id, user_id, date, processed, diagnosis_id, type_id) VALUES (174, 3, '2019-12-06 12:50:51.943+07', false, NULL, 1);
INSERT INTO public.questionnaires (id, user_id, date, processed, diagnosis_id, type_id) VALUES (176, 3, '2019-12-06 13:40:49.507+07', false, NULL, 1);
INSERT INTO public.questionnaires (id, user_id, date, processed, diagnosis_id, type_id) VALUES (252, 3, '2020-01-22 16:54:08.315+07', true, 4, 2);
INSERT INTO public.questionnaires (id, user_id, date, processed, diagnosis_id, type_id) VALUES (253, 3, '2020-01-22 17:22:25.589+07', true, 3, 1);
INSERT INTO public.questionnaires (id, user_id, date, processed, diagnosis_id, type_id) VALUES (254, 2, '2020-01-22 17:31:26.164+07', false, NULL, 1);
INSERT INTO public.questionnaires (id, user_id, date, processed, diagnosis_id, type_id) VALUES (255, 2, '2020-01-22 19:03:19.891+07', false, NULL, 1);
INSERT INTO public.questionnaires (id, user_id, date, processed, diagnosis_id, type_id) VALUES (258, 2, '2020-01-22 19:10:29.374+07', true, 4, 2);
INSERT INTO public.questionnaires (id, user_id, date, processed, diagnosis_id, type_id) VALUES (164, 2, '2018-12-04 16:33:31.312+07', false, NULL, 1);
INSERT INTO public.questionnaires (id, user_id, date, processed, diagnosis_id, type_id) VALUES (266, 2, '2020-02-17 20:34:26.442+07', true, NULL, 1);


--
-- Data for Name: questions; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.questions (id, text, options, description, input_type, answer_type, category, depends_on_the_question_id) VALUES (23, 'Беспокоят ли вас запоры', 'нет (стул раз в день);стул каждый третий день;стул каждый пятый день;стул каждый седьмой день или реже;', 'Запоры', 'radio', 'String', 'Беспокойства', NULL);
INSERT INTO public.questions (id, text, options, description, input_type, answer_type, category, depends_on_the_question_id) VALUES (22, 'Как часто вас беспокоит вздутие живота', 'не беспокоит или очень редко;редко и недлительно;частые и длительные эпизоды, которые можно регулировать подбором одежды;постоянное вздутие, влияющее на повседневную активность', 'Вздутие живота', 'radio', 'String', 'Беспокойства', NULL);
INSERT INTO public.questions (id, text, options, description, input_type, answer_type, category, depends_on_the_question_id) VALUES (21, 'Как часто вас беспокоит урчание в животе', 'не беспокоит или очень редко;редко и недлительно;частые и длительные эпизоды без существенного влияния на повседневную деятельность;постоянное урчание с серьезным влиянием на повседневную активность', 'Урчание в животе', 'radio', 'String', 'Беспокойства', NULL);
INSERT INTO public.questions (id, text, options, description, input_type, answer_type, category, depends_on_the_question_id) VALUES (40, 'Холестерин (ХС)', '0;100;0.1', 'Холестерин', 'number', 'Float', 'Анализ крови', NULL);
INSERT INTO public.questions (id, text, options, description, input_type, answer_type, category, depends_on_the_question_id) VALUES (41, 'Триглицериды', '0;100;0.1', 'Холестерин', 'number', 'Float', 'Анализ крови', NULL);
INSERT INTO public.questions (id, text, options, description, input_type, answer_type, category, depends_on_the_question_id) VALUES (42, 'ХС липопротеинов высокой плотности', '0;100;0.1', 'ХС липопротеинов высокой плотности', 'number', 'Float', 'Анализ крови', NULL);
INSERT INTO public.questions (id, text, options, description, input_type, answer_type, category, depends_on_the_question_id) VALUES (43, 'Глюкоза', '0;100;0.1', 'Глюкоза', 'number', 'Float', 'Анализ крови', NULL);
INSERT INTO public.questions (id, text, options, description, input_type, answer_type, category, depends_on_the_question_id) VALUES (45, 'Занимаетесь физкультурой', 'да;иногда;нет', 'Физкультура', 'radio', 'String', 'Общие вопросы', NULL);
INSERT INTO public.questions (id, text, options, description, input_type, answer_type, category, depends_on_the_question_id) VALUES (46, 'Сколько часов в неделю занимаетесь физкультурой', '0;60', 'Часов физкультуры в неделю', 'number', 'Integer', 'Общие вопросы', NULL);
INSERT INTO public.questions (id, text, options, description, input_type, answer_type, category, depends_on_the_question_id) VALUES (47, 'Окружность талии (см)', '30;200', 'Окружность талии (см)', 'number', 'Integer', 'Общие вопросы', NULL);
INSERT INTO public.questions (id, text, options, description, input_type, answer_type, category, depends_on_the_question_id) VALUES (48, 'Окружность бедер (см)', '10;100', 'Окружность бедер (см)', 'number', 'Integer', 'Общие вопросы', NULL);
INSERT INTO public.questions (id, text, options, description, input_type, answer_type, category, depends_on_the_question_id) VALUES (49, 'Окружность грудной клетки (см) в дыхательной паузе', '30;200', 'Окружность грудной клетки (см) в дыхательной паузе', 'number', 'Integer', 'Общие вопросы', NULL);
INSERT INTO public.questions (id, text, options, description, input_type, answer_type, category, depends_on_the_question_id) VALUES (50, 'ХС липопротеинов низкой плотности', '0;100;0.1', 'ХС липопротеинов низкой плотности', 'number', 'Float', 'Анализ крови', NULL);
INSERT INTO public.questions (id, text, options, description, input_type, answer_type, category, depends_on_the_question_id) VALUES (1, 'Сколько вам лет', '14;100', 'Возраст', 'number', 'Integer', 'Общие вопросы', NULL);
INSERT INTO public.questions (id, text, options, description, input_type, answer_type, category, depends_on_the_question_id) VALUES (51, 'Пол', 'м;ж', 'Пол', 'radio', 'String', 'Общие вопросы', NULL);
INSERT INTO public.questions (id, text, options, description, input_type, answer_type, category, depends_on_the_question_id) VALUES (52, 'Образование', 'начальное и неполное среднее;среднее и среднее специальное; высшее', 'Образование', 'radio', 'String', 'Общие вопросы', NULL);
INSERT INTO public.questions (id, text, options, description, input_type, answer_type, category, depends_on_the_question_id) VALUES (44, 'Ишемическая болезнь сердца', 'есть определенная;возможна;нет', 'Ишемическая болезнь сердца', 'radio', 'String', 'Заболевания', NULL);
INSERT INTO public.questions (id, text, options, description, input_type, answer_type, category, depends_on_the_question_id) VALUES (13, 'Сколько обычно выпиваете в течение одной выпивки, если пьете пиво (объем в мл)', '0;10000', 'Выпиваемое пиво в мл', 'number', 'Integer', 'Вредные привычки', NULL);
INSERT INTO public.questions (id, text, options, description, input_type, answer_type, category, depends_on_the_question_id) VALUES (12, 'Как часто вы употребляли алкоголь', 'не употреблял;несколько раз в год;1-2 раза в месяц;несколько раз в недлю;ежедневно ', 'Алкоголь в прошлом', 'radio', 'String', 'Вредные привычки', NULL);
INSERT INTO public.questions (id, text, options, description, input_type, answer_type, category, depends_on_the_question_id) VALUES (9, 'Часто ли вы путешествуете', 'не выезжаем за пределы РФ;1-2 раза в год;более 2 раз в год', 'Путешествия', 'radio', 'String', 'Общие вопросы', NULL);
INSERT INTO public.questions (id, text, options, description, input_type, answer_type, category, depends_on_the_question_id) VALUES (8, 'Экономическое положение', 'работающий;учащийся;домохозяйка;безработный;пенсионер/инвалид', 'Экономическое положение', 'radio', 'String', 'Общие вопросы', NULL);
INSERT INTO public.questions (id, text, options, description, input_type, answer_type, category, depends_on_the_question_id) VALUES (3, 'Сколько вы весите', '40;200', 'Вес', 'number', 'Integer', 'Общие вопросы', NULL);
INSERT INTO public.questions (id, text, options, description, input_type, answer_type, category, depends_on_the_question_id) VALUES (2, 'Ваш рост', '100;250', 'Рост', 'number', 'Integer', 'Общие вопросы', NULL);
INSERT INTO public.questions (id, text, options, description, input_type, answer_type, category, depends_on_the_question_id) VALUES (7, 'Семейное положение', 'холост/не замужем;женат/замужем;живу с партнером вне брака;разведен(а);вдовец/вдова', 'Семейное положение', 'radio', 'String', 'Общие вопросы', NULL);
INSERT INTO public.questions (id, text, options, description, input_type, answer_type, category, depends_on_the_question_id) VALUES (6, 'Характер труда', 'сидячая работа;стоячая работа;физический труд;тяжелый физический труд', 'Характер труда', 'radio', 'String', 'Общие вопросы', NULL);
INSERT INTO public.questions (id, text, options, description, input_type, answer_type, category, depends_on_the_question_id) VALUES (19, 'Стаж курения', '0;200', 'Стаж курения', 'number', 'Integer', 'Вредные привычки', NULL);
INSERT INTO public.questions (id, text, options, description, input_type, answer_type, category, depends_on_the_question_id) VALUES (18, 'С какого возраста курите регулярно', '4;200', 'Возраст начала курения', 'number', 'Integer', 'Вредные привычки', NULL);
INSERT INTO public.questions (id, text, options, description, input_type, answer_type, category, depends_on_the_question_id) VALUES (17, 'Курите в настоящее время', 'никогда не курил;бросил (более 3-х мес. назад);эпизодически (1 сигарета в последние 3 мес.);ежедневно (не меньше 1 сигареты в неделю);ежедневно (не меньше 1 сигареты в день)', 'Курение в настоящем', 'radio', 'String', 'Вредные привычки', NULL);
INSERT INTO public.questions (id, text, options, description, input_type, answer_type, category, depends_on_the_question_id) VALUES (16, 'Вы когда-нибудь пробовали курить, делали хотя бы 1 или 2 затяжки', 'да;нет', 'Опыт курения', 'radio', 'Boolean', 'Вредные привычки', NULL);
INSERT INTO public.questions (id, text, options, description, input_type, answer_type, category, depends_on_the_question_id) VALUES (11, 'Как часто вы употребляете алкоголь', 'не употребляю;несколько раз в год;1-2 раза в месяц;несколько раз в недлю;ежедневно ', 'Алкоголь', 'radio', 'String', 'Вредные привычки', NULL);
INSERT INTO public.questions (id, text, options, description, input_type, answer_type, category, depends_on_the_question_id) VALUES (10, 'Сколько чашек кофе в день вы выпиваете', '0;20', 'Чашек кофе в день', 'number', 'Integer', 'Вредные привычки', NULL);
INSERT INTO public.questions (id, text, options, description, input_type, answer_type, category, depends_on_the_question_id) VALUES (15, 'Сколько обычно выпиваете в течение одной выпивки, если пьете крепкие спиртные напитки (объем в мл)', '0;10000', 'Выпиваеммые крепки спиртные напитки в мл', 'number', 'Integer', 'Вредные привычки', NULL);
INSERT INTO public.questions (id, text, options, description, input_type, answer_type, category, depends_on_the_question_id) VALUES (14, 'Сколько обычно выпиваете в течение одной выпивки, если пьете вино (объем в мл)', '0;10000', 'Выпиваемое вино в мл', 'number', 'Integer', 'Вредные привычки', NULL);
INSERT INTO public.questions (id, text, options, description, input_type, answer_type, category, depends_on_the_question_id) VALUES (32, 'Наличие осложнений', 'острая токсическая дилатация толстой кишки (мегаколон);кишечное кровотечение, постгеморрагическая анемия;кишечная непроходимость;перфорация;перитонит;рубцовые стриктуры;малигнизация;парапроктит;ДВС-синдром;тромбофлебит вен таза и нижних конечностей;ни один из вышеперечисленных', 'Осложнения', 'checkbox', 'String', 'Беспокойства', NULL);
INSERT INTO public.questions (id, text, options, description, input_type, answer_type, category, depends_on_the_question_id) VALUES (27, 'Необходимость дефекации', 'нормальный контроль;редкие эпизоды потребности срочной дефекации;частые эпизоды, требующие срочной дефекации;неконтролируемая дефекация', 'Необходимость дефекации', 'radio', 'String', 'Беспокойства', NULL);
INSERT INTO public.questions (id, text, options, description, input_type, answer_type, category, depends_on_the_question_id) VALUES (26, 'Бывает ли у вас плотный, твердый стул', 'нормальной консистенции;плотный;твердый, фрагментированный;твердый и фрагментированный, иногда сочетающийся с диареей', 'Плотный стул', 'radio', 'String', 'Беспокойства', NULL);
INSERT INTO public.questions (id, text, options, description, input_type, answer_type, category, depends_on_the_question_id) VALUES (25, 'Какая консистенция стула бывает чаще', 'нормальной консистенции;кашицеобразный;жидкий;водянистый', 'Консистенция стула', 'radio', 'String', 'Беспокойства', NULL);
INSERT INTO public.questions (id, text, options, description, input_type, answer_type, category, depends_on_the_question_id) VALUES (24, 'Беспокоит ли вас жидкий, кашицеобразный стул', 'нет (стул раз в день);до 4  раз в день;4-6  раз в день;более 6 раз в день', 'Жидкий стул', 'radio', 'String', 'Беспокойства', NULL);
INSERT INTO public.questions (id, text, options, description, input_type, answer_type, category, depends_on_the_question_id) VALUES (31, 'Беспокоят ли вас тенезмы', 'нет;до 2-3 раз в день;до 5-6 раз в день;более 6 раз в день', 'Тенезмы', 'radio', 'String', 'Беспокойства', NULL);
INSERT INTO public.questions (id, text, options, description, input_type, answer_type, category, depends_on_the_question_id) VALUES (30, 'Суточный объем стула', '250-300мл;400-600мл;700-1000мл', 'Суточный объем стула', 'radio', 'String', 'Беспокойства', NULL);
INSERT INTO public.questions (id, text, options, description, input_type, answer_type, category, depends_on_the_question_id) VALUES (29, 'Замечали ли вы у себя патологические примеси в стуле', 'нет;слизь;кровь;кровь и слизь', 'Патологические примеси в стуле', 'radio', 'String', 'Беспокойства', NULL);
INSERT INTO public.questions (id, text, options, description, input_type, answer_type, category, depends_on_the_question_id) VALUES (28, 'Испытываете ли вы чувство неполного опорожнения кишечника после дефекации', 'ощущение полного опорожнения;некоторое затруднение при дефекации, редко чувство неполного опорожнения;явное затруднение при дефекации, часто чувство неполного опорожнения;серьезное затруднение при дефекации, постоянное чувство неполного опорожнения', 'Чувство неполного опорожнения кишечника после дефекации', 'radio', 'String', 'Беспокойства', NULL);
INSERT INTO public.questions (id, text, options, description, input_type, answer_type, category, depends_on_the_question_id) VALUES (20, 'Как часто вы испытываете боль в животе', 'не испытываю или очень редко;редкая боль, незначительно влияющая на социальную активность (качество жизни);длительная боль, требующая вмешательства и влияющая на повседневную жизнь;тяжелая боль, наносящая вред всем видам деятельности', 'Боли в животе', 'radio', 'String', 'Беспокойства', NULL);
INSERT INTO public.questions (id, text, options, description, input_type, answer_type, category, depends_on_the_question_id) VALUES (35, 'В выходные дни сколько часов вы обычно спите', '1;24', 'Время сна в выходные дни', 'number', 'Integer', 'Распорядок дня', NULL);
INSERT INTO public.questions (id, text, options, description, input_type, answer_type, category, depends_on_the_question_id) VALUES (34, 'В рабочие дни сколько часов вы обычно спите', '1;24', 'Время сна в рабочие дни', 'number', 'Integer', 'Распорядок дня', NULL);
INSERT INTO public.questions (id, text, options, description, input_type, answer_type, category, depends_on_the_question_id) VALUES (39, 'Температура тела (ºС)', '<37,5;≤37,8;>37,8', 'Температура тела', 'radio', 'Float', 'Распорядок дня', NULL);
INSERT INTO public.questions (id, text, options, description, input_type, answer_type, category, depends_on_the_question_id) VALUES (38, 'Артериальное давление ДАД', '30;300', 'Артериальное давление ДАД', 'number', 'Integer', 'Распорядок дня', NULL);
INSERT INTO public.questions (id, text, options, description, input_type, answer_type, category, depends_on_the_question_id) VALUES (37, 'Артериальное давление САД', '30;300', 'Артериальное давление САД', 'number', 'Integer', 'Распорядок дня', NULL);
INSERT INTO public.questions (id, text, options, description, input_type, answer_type, category, depends_on_the_question_id) VALUES (36, 'Частота сердечных сокращений (уд/мин) ', '30;300', 'Частота сердечных сокращений', 'number', 'Integer', 'Распорядок дня', NULL);
INSERT INTO public.questions (id, text, options, description, input_type, answer_type, category, depends_on_the_question_id) VALUES (33, 'Есть ли у вас внекишечные проявления', 'афтозный / язвенный стоматит, глоссит, гингивит, эзофагит;узловатая эритема, гангренозная пиодермия, язвы нижних конечностей, анальный зуд, дерматит, абсцессы, флегмона;ирит, эписклерит, кератит, конъюнктивит, блефарит, увеит;полиартрит, анкилозирующий спондилит, артралгии;первичный склерозирующий холангит, холангиокарцинома, перихолангит, жировой гепатоз, хронический гепатит;пиелонефрит, нефролитиаз;аутоиммунная гемолитическая анемия, железои В12-дефицитная анемия; ни один из вышеперечисленных', 'Внекишечные проявления', 'checkbox', 'String', 'Беспокойства', NULL);


--
-- Data for Name: answers; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1247, '14', 172, 1, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1248, '100', 172, 2, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1249, '40', 172, 3, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1250, 'сидячая работа', 172, 6, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1251, 'холост/не замужем', 172, 7, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1252, NULL, 172, 8, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1253, NULL, 172, 9, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1254, '', 172, 10, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1255, NULL, 172, 11, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1256, NULL, 172, 12, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1257, '', 172, 13, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1258, '', 172, 14, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1259, '', 172, 15, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1260, NULL, 172, 16, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1261, NULL, 172, 17, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1262, '', 172, 18, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1263, '', 172, 19, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1264, NULL, 172, 20, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1265, NULL, 172, 21, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1266, NULL, 172, 22, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1267, NULL, 172, 23, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1268, NULL, 172, 24, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1269, NULL, 172, 25, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1270, NULL, 172, 26, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1271, NULL, 172, 27, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1272, NULL, 172, 28, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1273, NULL, 172, 29, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1274, NULL, 172, 30, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1275, NULL, 172, 31, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1276, NULL, 172, 32, 'null;null;null;null;null;null;null;null;null;null;null');
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1277, NULL, 172, 33, 'null;null;null;null;null;null;null;null');
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1278, '', 172, 34, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1279, '', 172, 35, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1280, '', 172, 36, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1281, '', 172, 37, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1282, '', 172, 38, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1283, '>37,8', 172, 39, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2165, '0', 222, 40, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2166, '0', 222, 41, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2167, '0', 222, 42, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2168, '0', 222, 50, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2169, '0', 222, 43, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2170, '14', 222, 1, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2171, 'ж', 222, 51, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2172, 'есть определенная', 222, 44, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2173, 'начальное и неполное среднее', 222, 52, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2174, 'сидячая работа', 222, 6, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2175, 'холост/не замужем', 222, 7, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2176, 'работающий', 222, 8, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2177, 'иногда', 222, 45, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2178, '0', 222, 46, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2179, 'никогда не курил', 222, 17, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2180, '100', 222, 2, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2181, '40', 222, 3, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2182, '30', 222, 47, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2183, '10', 222, 48, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2184, '30', 222, 49, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2185, '30', 222, 36, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2186, '30', 222, 37, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2187, '30', 222, 38, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2590, '', 250, 1, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2591, '', 250, 2, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2592, '', 250, 3, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2593, NULL, 250, 6, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2594, NULL, 250, 7, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2595, NULL, 250, 8, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2596, NULL, 250, 9, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2597, '1', 250, 10, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2598, 'не употребляю', 250, 11, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2599, 'не употреблял', 250, 12, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2600, '1', 250, 13, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2601, '2', 250, 14, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2602, '3', 250, 15, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2603, 'да', 250, 16, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2604, 'никогда не курил', 250, 17, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2605, '4', 250, 18, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2606, '1', 250, 19, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2607, NULL, 250, 20, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2608, NULL, 250, 21, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2609, NULL, 250, 22, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2610, NULL, 250, 23, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2611, NULL, 250, 24, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2612, NULL, 250, 25, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2613, NULL, 250, 26, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2614, NULL, 250, 27, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2615, NULL, 250, 28, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2616, NULL, 250, 29, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2617, NULL, 250, 30, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2618, NULL, 250, 31, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2619, NULL, 250, 32, 'null;null;null;null;null;null;null;null;null;null;null');
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2620, NULL, 250, 33, 'null;null;null;null;null;null;null;null');
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2621, '1', 250, 34, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2622, '2', 250, 35, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2623, '32', 250, 36, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2624, '', 250, 37, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2625, '', 250, 38, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2626, NULL, 250, 39, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2627, '0.1', 251, 40, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2628, '0.2', 251, 41, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2629, '0.3', 251, 42, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2630, '0.5', 251, 50, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2631, '0.7', 251, 43, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2632, '15', 251, 1, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2633, 'м', 251, 51, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2634, NULL, 251, 44, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2635, 'начальное и неполное среднее', 251, 52, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2636, NULL, 251, 6, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2637, NULL, 251, 7, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2638, NULL, 251, 8, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2639, NULL, 251, 45, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2640, '', 251, 46, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2641, 'никогда не курил', 251, 17, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2642, '', 251, 2, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2643, '', 251, 3, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2644, '', 251, 47, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2645, '', 251, 48, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2646, '', 251, 49, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2647, '30', 251, 36, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2648, '32', 251, 37, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2649, '34', 251, 38, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2650, '0', 252, 40, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2651, '0.1', 252, 41, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2652, '0.1', 252, 42, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2653, '0.1', 252, 50, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2654, '0', 252, 43, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2655, '14', 252, 1, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2656, 'ж', 252, 51, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2657, 'есть определенная', 252, 44, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2658, 'начальное и неполное среднее', 252, 52, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1284, '14', 174, 1, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1285, '100', 174, 2, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1286, '40', 174, 3, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1294, '', 174, 13, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1295, '', 174, 14, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1296, '', 174, 15, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1299, '', 174, 18, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1300, '', 174, 19, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1301, NULL, 174, 20, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1302, NULL, 174, 21, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1303, NULL, 174, 22, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1304, NULL, 174, 23, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1305, NULL, 174, 24, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1306, NULL, 174, 25, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1307, NULL, 174, 26, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1308, NULL, 174, 27, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1309, NULL, 174, 28, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1310, NULL, 174, 29, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1311, NULL, 174, 30, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1312, NULL, 174, 31, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1313, NULL, 174, 32, 'null;null;null;null;null;null;null;null;null;null;null');
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1314, NULL, 174, 33, 'null;null;null;null;null;null;null;null');
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1315, '', 174, 34, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1316, '', 174, 35, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1317, '', 174, 36, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1318, '', 174, 37, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1319, '', 174, 38, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1320, NULL, 174, 39, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1287, 'сидячая работа', 174, 6, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1288, 'холост/не замужем', 174, 7, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1289, 'работающий', 174, 8, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1290, 'не выезжаем за пределы РФ', 174, 9, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1291, '0', 174, 10, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1292, 'не употребляю', 174, 11, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1293, 'не употреблял', 174, 12, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2659, 'сидячая работа', 252, 6, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2660, 'холост/не замужем', 252, 7, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2661, 'работающий', 252, 8, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2662, 'да', 252, 45, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2663, '0', 252, 46, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2664, 'никогда не курил', 252, 17, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2665, '100', 252, 2, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2666, '40', 252, 3, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2667, '30', 252, 47, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2668, '10', 252, 48, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2669, '30', 252, 49, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2670, '30', 252, 36, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2671, '30', 252, 37, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2672, '30', 252, 38, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2739, NULL, 254, 32, 'null;null;null;null;null;null;null;null;null;null;null');
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2740, NULL, 254, 33, 'null;null;null;null;null;null;null;null');
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2741, '', 254, 34, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2742, '', 254, 35, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2743, '', 254, 36, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2744, '', 254, 37, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2745, '', 254, 38, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2746, NULL, 254, 39, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2747, '', 255, 1, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2748, '', 255, 2, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2749, '', 255, 3, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2750, NULL, 255, 6, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2751, NULL, 255, 7, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2752, NULL, 255, 8, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2753, NULL, 255, 9, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2754, '2', 255, 10, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2755, 'не употребляю', 255, 11, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2756, 'не употреблял', 255, 12, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2757, '1', 255, 13, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2758, '', 255, 14, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2759, '', 255, 15, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2760, NULL, 255, 16, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2761, 'никогда не курил', 255, 17, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2762, '', 255, 18, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2763, '', 255, 19, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2764, NULL, 255, 20, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2765, NULL, 255, 21, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2766, NULL, 255, 22, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2767, NULL, 255, 23, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2768, NULL, 255, 24, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2769, NULL, 255, 25, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2770, NULL, 255, 26, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2771, NULL, 255, 27, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2772, NULL, 255, 28, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2773, NULL, 255, 29, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2774, NULL, 255, 30, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2775, NULL, 255, 31, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2776, NULL, 255, 32, 'null;null;null;null;null;null;null;null;null;null;null');
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2777, NULL, 255, 33, 'null;null;null;null;null;null;null;null');
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2778, '', 255, 34, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2779, '', 255, 35, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2780, '', 255, 36, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2781, '', 255, 37, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2782, '', 255, 38, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2783, NULL, 255, 39, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1321, '14', 176, 1, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1322, '100', 176, 2, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1323, '', 176, 3, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1324, NULL, 176, 6, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1325, NULL, 176, 7, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1326, NULL, 176, 8, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1327, NULL, 176, 9, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1328, '', 176, 10, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1329, NULL, 176, 11, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1330, NULL, 176, 12, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1331, '', 176, 13, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1332, '', 176, 14, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1333, '', 176, 15, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1334, NULL, 176, 16, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1335, NULL, 176, 17, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1336, '', 176, 18, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1337, '', 176, 19, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1338, NULL, 176, 20, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1339, NULL, 176, 21, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1340, NULL, 176, 22, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1341, NULL, 176, 23, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1342, NULL, 176, 24, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1343, NULL, 176, 25, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1344, NULL, 176, 26, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1062, '14', 162, 1, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1063, '100', 162, 2, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1345, NULL, 176, 27, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1064, '40', 162, 3, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1065, 'сидячая работа', 162, 6, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1066, 'разведен(а)', 162, 7, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1346, NULL, 176, 28, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1347, NULL, 176, 29, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1348, NULL, 176, 30, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1349, NULL, 176, 31, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1350, NULL, 176, 32, 'null;null;null;null;null;null;null;null;null;null;null');
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1351, NULL, 176, 33, 'null;null;null;null;null;null;null;null');
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1352, '', 176, 34, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1353, '', 176, 35, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1354, '', 176, 36, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1355, '', 176, 37, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1356, '', 176, 38, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1357, NULL, 176, 39, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2553, '', 247, 1, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2554, '', 247, 2, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2555, '', 247, 3, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2556, NULL, 247, 6, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2557, NULL, 247, 7, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2558, NULL, 247, 8, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2559, NULL, 247, 9, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2560, '0', 247, 10, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2561, 'не употребляю', 247, 11, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2562, 'не употреблял', 247, 12, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2563, '1', 247, 13, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2564, '2', 247, 14, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2565, '3', 247, 15, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2566, NULL, 247, 16, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2567, NULL, 247, 17, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2568, '', 247, 18, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2569, '', 247, 19, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2570, NULL, 247, 20, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2571, NULL, 247, 21, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2572, NULL, 247, 22, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2573, NULL, 247, 23, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2574, NULL, 247, 24, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2575, NULL, 247, 25, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2576, NULL, 247, 26, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1025, '14', 160, 1, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1026, '100', 160, 2, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1027, '40', 160, 3, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1028, 'сидячая работа', 160, 6, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1029, 'разведен(а)', 160, 7, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1030, 'домохозяйка', 160, 8, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1031, 'не выезжаем за пределы РФ', 160, 9, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1032, '1', 160, 10, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1033, 'не употребляю', 160, 11, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1034, 'не употреблял', 160, 12, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1035, '0', 160, 13, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1036, '0', 160, 14, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1037, '0', 160, 15, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1038, 'нет', 160, 16, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1039, 'бросил (более 3-х мес. назад)', 160, 17, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1040, '4', 160, 18, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1041, '0', 160, 19, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1042, 'не испытываю или очень редко', 160, 20, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1043, 'постоянное урчание с серьезным влиянием на повседневную активность', 160, 21, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1044, 'редко и недлительно', 160, 22, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1045, 'нет (стул раз в день)', 160, 23, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1046, 'более 6 раз в день', 160, 24, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1047, 'нормальной консистенции', 160, 25, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1048, 'нормальной консистенции', 160, 26, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1049, 'редкие эпизоды потребности срочной дефекации', 160, 27, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1050, 'ощущение полного опорожнения', 160, 28, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1051, 'нет', 160, 29, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1052, '700-1000мл', 160, 30, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1053, 'нет', 160, 31, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1054, NULL, 160, 32, 'острая токсическая дилатация толстой кишки (мегаколон);кишечное кровотечение, постгеморрагическая анемия;кишечная непроходимость;null;null;null;малигнизация;null;null;null;null');
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1067, 'домохозяйка', 162, 8, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1068, 'не выезжаем за пределы РФ', 162, 9, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1069, '1', 162, 10, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1070, 'не употребляю', 162, 11, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1071, 'не употреблял', 162, 12, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1072, '0', 162, 13, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1073, '0', 162, 14, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1074, '0', 162, 15, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1075, 'нет', 162, 16, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1076, 'бросил (более 3-х мес. назад)', 162, 17, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1077, '4', 162, 18, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1078, '0', 162, 19, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1079, 'не испытываю или очень редко', 162, 20, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2577, NULL, 247, 27, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2578, NULL, 247, 28, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2579, NULL, 247, 29, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2580, NULL, 247, 30, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2581, NULL, 247, 31, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2582, NULL, 247, 32, 'null;null;null;null;null;null;null;null;null;null;null');
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2583, NULL, 247, 33, 'null;null;null;null;null;null;null;null');
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1055, NULL, 160, 33, 'афтозный / язвенный стоматит, глоссит, гингивит, эзофагит;null;ирит, эписклерит, кератит, конъюнктивит, блефарит, увеит;null;null;пиелонефрит, нефролитиаз;null; ни один из вышеперечисленных');
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1056, '1', 160, 34, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1057, '1', 160, 35, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1058, '30', 160, 36, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1059, '30', 160, 37, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1060, '30', 160, 38, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1061, '>37,8', 160, 39, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2584, '', 247, 34, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2585, '', 247, 35, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2586, '', 247, 36, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2587, '', 247, 37, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2588, '', 247, 38, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2589, NULL, 247, 39, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2673, '14', 253, 1, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2674, '101', 253, 2, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2675, '40', 253, 3, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2676, 'физический труд', 253, 6, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2677, 'живу с партнером вне брака', 253, 7, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2678, 'домохозяйка', 253, 8, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2679, 'более 2 раз в год', 253, 9, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2680, '1', 253, 10, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2681, 'не употребляю', 253, 11, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2682, 'несколько раз в год', 253, 12, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2683, '0', 253, 13, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2684, '1', 253, 14, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2685, '2', 253, 15, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2686, 'да', 253, 16, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2687, 'бросил (более 3-х мес. назад)', 253, 17, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2688, '5', 253, 18, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2689, '2', 253, 19, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2716, NULL, 254, 9, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2690, 'длительная боль, требующая вмешательства и влияющая на повседневную жизнь', 253, 20, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2691, 'не беспокоит или очень редко', 253, 21, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2692, 'частые и длительные эпизоды, которые можно регулировать подбором одежды', 253, 22, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2693, 'стул каждый пятый день', 253, 23, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2694, 'более 6 раз в день', 253, 24, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2695, 'нормальной консистенции', 253, 25, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2696, 'нормальной консистенции', 253, 26, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2697, 'неконтролируемая дефекация', 253, 27, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2698, 'серьезное затруднение при дефекации, постоянное чувство неполного опорожнения', 253, 28, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2699, 'кровь и слизь', 253, 29, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2700, '700-1000мл', 253, 30, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2701, 'до 2-3 раз в день', 253, 31, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2702, NULL, 253, 32, 'null;кишечное кровотечение, постгеморрагическая анемия;null;перфорация;null;рубцовые стриктуры;малигнизация;null;null;null;ни один из вышеперечисленных');
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2703, NULL, 253, 33, 'null;узловатая эритема, гангренозная пиодермия, язвы нижних конечностей, анальный зуд, дерматит, абсцессы, флегмона;null;полиартрит, анкилозирующий спондилит, артралгии;null;пиелонефрит, нефролитиаз;null;null');
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2704, '2', 253, 34, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2705, '4', 253, 35, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2706, '137', 253, 36, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2707, '80', 253, 37, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2708, '35', 253, 38, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2709, '>37,8', 253, 39, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2858, '0', 258, 40, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2859, '0', 258, 41, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2865, 'есть определенная', 258, 44, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2872, 'никогда не курил', 258, 17, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2860, '0', 258, 42, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2861, '0.1', 258, 50, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2862, '0.1', 258, 43, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2863, '14', 258, 1, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2864, 'ж', 258, 51, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2866, 'начальное и неполное среднее', 258, 52, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2867, 'сидячая работа', 258, 6, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2868, 'холост/не замужем', 258, 7, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2869, 'работающий', 258, 8, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2870, 'иногда', 258, 45, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2871, '0', 258, 46, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2873, '100', 258, 2, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2874, '40', 258, 3, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2875, '30', 258, 47, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2876, '10', 258, 48, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2877, '30', 258, 49, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2878, '30', 258, 36, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2879, '30', 258, 37, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2880, '30', 258, 38, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1080, 'постоянное урчание с серьезным влиянием на повседневную активность', 162, 21, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1081, 'редко и недлительно', 162, 22, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1082, 'нет (стул раз в день)', 162, 23, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1083, 'более 6 раз в день', 162, 24, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1084, 'нормальной консистенции', 162, 25, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1085, 'нормальной консистенции', 162, 26, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1086, 'редкие эпизоды потребности срочной дефекации', 162, 27, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1087, 'ощущение полного опорожнения', 162, 28, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1088, 'нет', 162, 29, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1089, '700-1000мл', 162, 30, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1090, 'нет', 162, 31, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1091, NULL, 162, 32, 'острая токсическая дилатация толстой кишки (мегаколон);кишечное кровотечение, постгеморрагическая анемия;кишечная непроходимость;null;null;null;малигнизация;null;null;null;ни один из вышеперечисленных');
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1092, NULL, 162, 33, 'афтозный / язвенный стоматит, глоссит, гингивит, эзофагит;узловатая эритема, гангренозная пиодермия, язвы нижних конечностей, анальный зуд, дерматит, абсцессы, флегмона;ирит, эписклерит, кератит, конъюнктивит, блефарит, увеит;null;null;пиелонефрит, нефролитиаз;null; ни один из вышеперечисленных');
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1093, '1', 162, 34, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1094, '1', 162, 35, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1095, '30', 162, 36, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1096, '30', 162, 37, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1097, '30', 162, 38, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1098, '>37,8', 162, 39, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1102, 'сидячая работа', 164, 6, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1105, 'не выезжаем за пределы РФ', 164, 9, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1109, '0', 164, 13, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1110, '0', 164, 14, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1111, '0', 164, 15, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1112, 'нет', 164, 16, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1113, 'бросил (более 3-х мес. назад)', 164, 17, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1115, '0', 164, 19, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1116, 'не испытываю или очень редко', 164, 20, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1119, 'нет (стул раз в день)', 164, 23, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1121, 'нормальной консистенции', 164, 25, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1122, 'нормальной консистенции', 164, 26, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1124, 'ощущение полного опорожнения', 164, 28, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1125, 'нет', 164, 29, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1127, 'нет', 164, 31, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1099, '18', 164, 1, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2710, '', 254, 1, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2711, '', 254, 2, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2712, '', 254, 3, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2713, NULL, 254, 6, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2714, NULL, 254, 7, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2715, NULL, 254, 8, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2717, '', 254, 10, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2718, 'не употребляю', 254, 11, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2719, 'не употреблял', 254, 12, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2720, '', 254, 13, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2721, '', 254, 14, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2722, '', 254, 15, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2723, NULL, 254, 16, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2724, NULL, 254, 17, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2725, '', 254, 18, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2726, '', 254, 19, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2727, NULL, 254, 20, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2728, NULL, 254, 21, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2729, NULL, 254, 22, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2730, NULL, 254, 23, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2731, NULL, 254, 24, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2732, NULL, 254, 25, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2733, NULL, 254, 26, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2734, NULL, 254, 27, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2735, NULL, 254, 28, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2736, NULL, 254, 29, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2737, NULL, 254, 30, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2738, NULL, 254, 31, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1297, 'нет', 174, 16, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1298, 'никогда не курил', 174, 17, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1100, '180', 164, 2, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1101, '80', 164, 3, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1103, 'холост/не замужем', 164, 7, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1104, 'учащийся', 164, 8, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1107, 'не употребляю', 164, 11, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1108, 'не употреблял', 164, 12, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1114, '19', 164, 18, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1117, 'не беспокоит или очень редко', 164, 21, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1118, 'не беспокоит или очень редко', 164, 22, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1126, '400-600мл', 164, 30, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1106, '2', 164, 10, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1120, 'нет (стул раз в день)', 164, 24, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1123, 'нормальный контроль', 164, 27, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1128, NULL, 164, 32, 'null;null;null;null;null;null;null;null;null;null;ни один из вышеперечисленных');
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1129, NULL, 164, 33, 'null;null;null;null;null;null;null; ни один из вышеперечисленных');
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1130, '8', 164, 34, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1131, '10', 164, 35, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1132, '60', 164, 36, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1133, '120', 164, 37, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1134, '80', 164, 38, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (1135, '<37,5', 164, 39, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2940, '0', 266, 10, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2941, 'не употребляю', 266, 11, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2942, 'не употреблял', 266, 12, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2943, '0', 266, 13, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2944, '0', 266, 14, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2945, '0', 266, 15, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2946, 'да', 266, 16, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2947, 'никогда не курил', 266, 17, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2948, '4', 266, 18, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2949, '1', 266, 19, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2964, '1', 266, 34, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2965, '1', 266, 35, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2966, '30', 266, 36, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2967, '31', 266, 37, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2968, '30', 266, 38, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2933, '14', 266, 1, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2934, '100', 266, 2, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2935, '40', 266, 3, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2936, 'сидячая работа', 266, 6, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2937, 'холост/не замужем', 266, 7, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2938, 'работающий', 266, 8, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2939, 'не выезжаем за пределы РФ', 266, 9, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2950, 'не испытываю или очень редко', 266, 20, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2951, 'не беспокоит или очень редко', 266, 21, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2952, 'не беспокоит или очень редко', 266, 22, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2953, 'нет (стул раз в день)', 266, 23, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2954, 'нет (стул раз в день)', 266, 24, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2955, 'нормальной консистенции', 266, 25, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2956, 'нормальной консистенции', 266, 26, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2957, 'нормальный контроль', 266, 27, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2958, 'ощущение полного опорожнения', 266, 28, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2959, 'нет', 266, 29, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2960, '250-300мл', 266, 30, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2961, 'нет', 266, 31, NULL);
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2962, NULL, 266, 32, 'null;null;null;null;null;null;null;null;null;null;ни один из вышеперечисленных');
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2963, NULL, 266, 33, 'null;null;null;null;null;null;null; ни один из вышеперечисленных');
INSERT INTO public.answers (id, text, questionnaire_id, question_id, text_list) VALUES (2969, '≤37,8', 266, 39, NULL);


--
-- Data for Name: diagnosis_score_questionnaire; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.diagnosis_score_questionnaire (id, diagnosis_id, score, questionnaire_id) VALUES (1, 1, 70.000, 255);
INSERT INTO public.diagnosis_score_questionnaire (id, diagnosis_id, score, questionnaire_id) VALUES (40, 1, 20.500, 266);
INSERT INTO public.diagnosis_score_questionnaire (id, diagnosis_id, score, questionnaire_id) VALUES (41, 2, 15.000, 266);


--
-- Data for Name: doctors; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.doctors (id, user_id, description) VALUES (1, 28, 'Лучший доктор в России. 50 лет практики, 10000 установленных диагнозов, тысячи довольных пациентов. Более 100 наград за вклад в диагностирование рака мирового уровня. Ученая степень доктора.');
INSERT INTO public.doctors (id, user_id, description) VALUES (2, 2, 'Лучший доктор в России. 50 лет практики, 10000 установленных диагнозов, тысячи довольных пациентов. Более 100 наград за вклад в диагностирование рака мирового уровня. Ученая степень доктора.');
INSERT INTO public.doctors (id, user_id, description) VALUES (5, 55, 'Лучший доктор в России. 50 лет практики, 10000 установленных диагнозов, тысячи довольных пациентов. Более 100 наград за вклад в диагностирование рака мирового уровня. Ученая степень доктора.');


--
-- Data for Name: patients; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.patients (id, diagnosis_id, user_id) VALUES (1, 5, 2);
INSERT INTO public.patients (id, diagnosis_id, user_id) VALUES (22, 1, 3);
INSERT INTO public.patients (id, diagnosis_id, user_id) VALUES (24, NULL, 54);
INSERT INTO public.patients (id, diagnosis_id, user_id) VALUES (23, NULL, 53);
INSERT INTO public.patients (id, diagnosis_id, user_id) VALUES (26, NULL, 57);
INSERT INTO public.patients (id, diagnosis_id, user_id) VALUES (25, 1, 56);


--
-- Data for Name: doctors_patients; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.doctors_patients (doctor_id, patient_id) VALUES (1, 1);
INSERT INTO public.doctors_patients (doctor_id, patient_id) VALUES (1, 25);
INSERT INTO public.doctors_patients (doctor_id, patient_id) VALUES (2, 22);




--
-- Data for Name: question_weights; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.question_weights (id, question_id, questionnaire_type_id, lower_bound, upper_bound, weight_for_low, weight_for_up, array_of_weights, bound_type, diagnosis_id) VALUES (22, 38, 1, 60.000, 90.000, 1.000, 2.000, NULL, 'out', 2);
INSERT INTO public.question_weights (id, question_id, questionnaire_type_id, lower_bound, upper_bound, weight_for_low, weight_for_up, array_of_weights, bound_type, diagnosis_id) VALUES (9, 34, 1, 4.000, 8.000, 2.000, 1.000, NULL, 'out', 2);
INSERT INTO public.question_weights (id, question_id, questionnaire_type_id, lower_bound, upper_bound, weight_for_low, weight_for_up, array_of_weights, bound_type, diagnosis_id) VALUES (8, 15, 1, 100.000, 1000.000, 0.000, 1.000, NULL, 'out', 2);
INSERT INTO public.question_weights (id, question_id, questionnaire_type_id, lower_bound, upper_bound, weight_for_low, weight_for_up, array_of_weights, bound_type, diagnosis_id) VALUES (39, 33, 1, NULL, NULL, NULL, NULL, '2;2;3;3;2;4;0;2', NULL, 1);
INSERT INTO public.question_weights (id, question_id, questionnaire_type_id, lower_bound, upper_bound, weight_for_low, weight_for_up, array_of_weights, bound_type, diagnosis_id) VALUES (36, 20, 1, NULL, NULL, NULL, NULL, '0;1;2;4', NULL, 1);
INSERT INTO public.question_weights (id, question_id, questionnaire_type_id, lower_bound, upper_bound, weight_for_low, weight_for_up, array_of_weights, bound_type, diagnosis_id) VALUES (37, 27, 1, NULL, NULL, NULL, NULL, '0;1;2;4', NULL, 1);
INSERT INTO public.question_weights (id, question_id, questionnaire_type_id, lower_bound, upper_bound, weight_for_low, weight_for_up, array_of_weights, bound_type, diagnosis_id) VALUES (34, 6, 1, NULL, NULL, NULL, NULL, '2;1;1;2', NULL, 1);
INSERT INTO public.question_weights (id, question_id, questionnaire_type_id, lower_bound, upper_bound, weight_for_low, weight_for_up, array_of_weights, bound_type, diagnosis_id) VALUES (35, 12, 1, NULL, NULL, NULL, NULL, '0;0.5;1;2;4', NULL, 1);
INSERT INTO public.question_weights (id, question_id, questionnaire_type_id, lower_bound, upper_bound, weight_for_low, weight_for_up, array_of_weights, bound_type, diagnosis_id) VALUES (33, 21, 1, NULL, NULL, NULL, NULL, '0;1;2;4', NULL, 1);
INSERT INTO public.question_weights (id, question_id, questionnaire_type_id, lower_bound, upper_bound, weight_for_low, weight_for_up, array_of_weights, bound_type, diagnosis_id) VALUES (32, 36, 1, 45.000, 70.000, 2.000, 3.000, NULL, 'out', 2);
INSERT INTO public.question_weights (id, question_id, questionnaire_type_id, lower_bound, upper_bound, weight_for_low, weight_for_up, array_of_weights, bound_type, diagnosis_id) VALUES (41, 15, 1, 100.000, 1000.000, 0.000, 1.000, NULL, 'out', 1);
INSERT INTO public.question_weights (id, question_id, questionnaire_type_id, lower_bound, upper_bound, weight_for_low, weight_for_up, array_of_weights, bound_type, diagnosis_id) VALUES (15, 10, 1, 2.000, 6.000, 0.500, NULL, NULL, 'in', 1);
INSERT INTO public.question_weights (id, question_id, questionnaire_type_id, lower_bound, upper_bound, weight_for_low, weight_for_up, array_of_weights, bound_type, diagnosis_id) VALUES (40, 10, 1, 6.000, 21.000, 2.000, NULL, NULL, 'in', 2);
INSERT INTO public.question_weights (id, question_id, questionnaire_type_id, lower_bound, upper_bound, weight_for_low, weight_for_up, array_of_weights, bound_type, diagnosis_id) VALUES (38, 35, 1, 7.000, 12.000, 1.000, 0.500, NULL, 'out', 2);
INSERT INTO public.question_weights (id, question_id, questionnaire_type_id, lower_bound, upper_bound, weight_for_low, weight_for_up, array_of_weights, bound_type, diagnosis_id) VALUES (6, 26, 1, NULL, NULL, NULL, NULL, '0;0;0.5;1', NULL, 1);
INSERT INTO public.question_weights (id, question_id, questionnaire_type_id, lower_bound, upper_bound, weight_for_low, weight_for_up, array_of_weights, bound_type, diagnosis_id) VALUES (7, 11, 1, NULL, NULL, NULL, NULL, '0;0.5;1;2;4
', NULL, 1);
INSERT INTO public.question_weights (id, question_id, questionnaire_type_id, lower_bound, upper_bound, weight_for_low, weight_for_up, array_of_weights, bound_type, diagnosis_id) VALUES (5, 1, 1, 50.000, 50.000, 0.500, 0.500, NULL, 'out', 1);
INSERT INTO public.question_weights (id, question_id, questionnaire_type_id, lower_bound, upper_bound, weight_for_low, weight_for_up, array_of_weights, bound_type, diagnosis_id) VALUES (29, 7, 1, NULL, NULL, NULL, NULL, '0;1;1;0', NULL, 1);
INSERT INTO public.question_weights (id, question_id, questionnaire_type_id, lower_bound, upper_bound, weight_for_low, weight_for_up, array_of_weights, bound_type, diagnosis_id) VALUES (14, 25, 1, NULL, NULL, NULL, NULL, '0;1;2;3', NULL, 1);
INSERT INTO public.question_weights (id, question_id, questionnaire_type_id, lower_bound, upper_bound, weight_for_low, weight_for_up, array_of_weights, bound_type, diagnosis_id) VALUES (3, 3, 1, 50.000, 90.000, 0.500, 0.500, NULL, 'out', 2);
INSERT INTO public.question_weights (id, question_id, questionnaire_type_id, lower_bound, upper_bound, weight_for_low, weight_for_up, array_of_weights, bound_type, diagnosis_id) VALUES (12, 17, 1, NULL, NULL, NULL, NULL, '0;1;1;4;6', NULL, 1);
INSERT INTO public.question_weights (id, question_id, questionnaire_type_id, lower_bound, upper_bound, weight_for_low, weight_for_up, array_of_weights, bound_type, diagnosis_id) VALUES (13, 31, 1, NULL, NULL, NULL, NULL, '0;1;2;4', NULL, 1);
INSERT INTO public.question_weights (id, question_id, questionnaire_type_id, lower_bound, upper_bound, weight_for_low, weight_for_up, array_of_weights, bound_type, diagnosis_id) VALUES (11, 32, 1, NULL, NULL, NULL, NULL, '2;2;2;2;2;2;2;2;2;2;0', NULL, 1);
INSERT INTO public.question_weights (id, question_id, questionnaire_type_id, lower_bound, upper_bound, weight_for_low, weight_for_up, array_of_weights, bound_type, diagnosis_id) VALUES (10, 19, 1, 5.000, 5.000, 2.000, 4.000, NULL, 'out', 2);
INSERT INTO public.question_weights (id, question_id, questionnaire_type_id, lower_bound, upper_bound, weight_for_low, weight_for_up, array_of_weights, bound_type, diagnosis_id) VALUES (23, 23, 1, NULL, NULL, NULL, NULL, '0;1;2;4;', NULL, 1);
INSERT INTO public.question_weights (id, question_id, questionnaire_type_id, lower_bound, upper_bound, weight_for_low, weight_for_up, array_of_weights, bound_type, diagnosis_id) VALUES (20, 24, 1, NULL, NULL, NULL, NULL, '0;1;2;4', NULL, 1);
INSERT INTO public.question_weights (id, question_id, questionnaire_type_id, lower_bound, upper_bound, weight_for_low, weight_for_up, array_of_weights, bound_type, diagnosis_id) VALUES (21, 9, 1, NULL, NULL, NULL, NULL, '10;1;0', NULL, 1);
INSERT INTO public.question_weights (id, question_id, questionnaire_type_id, lower_bound, upper_bound, weight_for_low, weight_for_up, array_of_weights, bound_type, diagnosis_id) VALUES (18, 16, 1, NULL, NULL, NULL, NULL, '1;0', NULL, 1);
INSERT INTO public.question_weights (id, question_id, questionnaire_type_id, lower_bound, upper_bound, weight_for_low, weight_for_up, array_of_weights, bound_type, diagnosis_id) VALUES (19, 30, 1, NULL, NULL, NULL, NULL, '2;4;6', NULL, 1);
INSERT INTO public.question_weights (id, question_id, questionnaire_type_id, lower_bound, upper_bound, weight_for_low, weight_for_up, array_of_weights, bound_type, diagnosis_id) VALUES (16, 39, 1, NULL, NULL, NULL, NULL, '0;1;2', NULL, 1);
INSERT INTO public.question_weights (id, question_id, questionnaire_type_id, lower_bound, upper_bound, weight_for_low, weight_for_up, array_of_weights, bound_type, diagnosis_id) VALUES (30, 28, 1, NULL, NULL, NULL, NULL, '0;1;2;4', NULL, 1);
INSERT INTO public.question_weights (id, question_id, questionnaire_type_id, lower_bound, upper_bound, weight_for_low, weight_for_up, array_of_weights, bound_type, diagnosis_id) VALUES (28, 22, 1, NULL, NULL, NULL, NULL, '0;1;2;4', NULL, 1);
INSERT INTO public.question_weights (id, question_id, questionnaire_type_id, lower_bound, upper_bound, weight_for_low, weight_for_up, array_of_weights, bound_type, diagnosis_id) VALUES (17, 18, 1, 18.000, 18.000, 4.000, 2.000, NULL, 'out', 2);
INSERT INTO public.question_weights (id, question_id, questionnaire_type_id, lower_bound, upper_bound, weight_for_low, weight_for_up, array_of_weights, bound_type, diagnosis_id) VALUES (26, 8, 1, NULL, NULL, NULL, NULL, '2;3;1;1;4', NULL, 1);
INSERT INTO public.question_weights (id, question_id, questionnaire_type_id, lower_bound, upper_bound, weight_for_low, weight_for_up, array_of_weights, bound_type, diagnosis_id) VALUES (31, 13, 1, 300.000, 300.000, 0.500, 2.000, NULL, 'out', 2);
INSERT INTO public.question_weights (id, question_id, questionnaire_type_id, lower_bound, upper_bound, weight_for_low, weight_for_up, array_of_weights, bound_type, diagnosis_id) VALUES (24, 29, 1, NULL, NULL, NULL, NULL, '0;1;2;4', NULL, 1);
INSERT INTO public.question_weights (id, question_id, questionnaire_type_id, lower_bound, upper_bound, weight_for_low, weight_for_up, array_of_weights, bound_type, diagnosis_id) VALUES (2, 2, 1, 140.000, 200.000, 1.000, 1.500, NULL, 'out', 2);
INSERT INTO public.question_weights (id, question_id, questionnaire_type_id, lower_bound, upper_bound, weight_for_low, weight_for_up, array_of_weights, bound_type, diagnosis_id) VALUES (27, 37, 1, 100.000, 130.000, 1.000, 2.000, NULL, 'out', 2);
INSERT INTO public.question_weights (id, question_id, questionnaire_type_id, lower_bound, upper_bound, weight_for_low, weight_for_up, array_of_weights, bound_type, diagnosis_id) VALUES (25, 14, 1, 200.000, 200.000, 0.000, 1.000, NULL, 'out', 2);


--
-- Data for Name: questionnaires_questions; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.questionnaires_questions (type_id, question_id, question_order) VALUES (1, 1, 1);
INSERT INTO public.questionnaires_questions (type_id, question_id, question_order) VALUES (1, 3, 3);
INSERT INTO public.questionnaires_questions (type_id, question_id, question_order) VALUES (1, 2, 2);
INSERT INTO public.questionnaires_questions (type_id, question_id, question_order) VALUES (1, 26, 24);
INSERT INTO public.questionnaires_questions (type_id, question_id, question_order) VALUES (1, 11, 9);
INSERT INTO public.questionnaires_questions (type_id, question_id, question_order) VALUES (1, 15, 13);
INSERT INTO public.questionnaires_questions (type_id, question_id, question_order) VALUES (1, 34, 32);
INSERT INTO public.questionnaires_questions (type_id, question_id, question_order) VALUES (1, 19, 17);
INSERT INTO public.questionnaires_questions (type_id, question_id, question_order) VALUES (1, 32, 30);
INSERT INTO public.questionnaires_questions (type_id, question_id, question_order) VALUES (1, 17, 15);
INSERT INTO public.questionnaires_questions (type_id, question_id, question_order) VALUES (1, 31, 29);
INSERT INTO public.questionnaires_questions (type_id, question_id, question_order) VALUES (1, 25, 23);
INSERT INTO public.questionnaires_questions (type_id, question_id, question_order) VALUES (1, 10, 8);
INSERT INTO public.questionnaires_questions (type_id, question_id, question_order) VALUES (1, 39, 37);
INSERT INTO public.questionnaires_questions (type_id, question_id, question_order) VALUES (1, 18, 16);
INSERT INTO public.questionnaires_questions (type_id, question_id, question_order) VALUES (1, 16, 14);
INSERT INTO public.questionnaires_questions (type_id, question_id, question_order) VALUES (1, 30, 28);
INSERT INTO public.questionnaires_questions (type_id, question_id, question_order) VALUES (1, 24, 22);
INSERT INTO public.questionnaires_questions (type_id, question_id, question_order) VALUES (1, 9, 7);
INSERT INTO public.questionnaires_questions (type_id, question_id, question_order) VALUES (1, 38, 36);
INSERT INTO public.questionnaires_questions (type_id, question_id, question_order) VALUES (1, 23, 21);
INSERT INTO public.questionnaires_questions (type_id, question_id, question_order) VALUES (1, 29, 27);
INSERT INTO public.questionnaires_questions (type_id, question_id, question_order) VALUES (1, 14, 12);
INSERT INTO public.questionnaires_questions (type_id, question_id, question_order) VALUES (1, 8, 6);
INSERT INTO public.questionnaires_questions (type_id, question_id, question_order) VALUES (1, 37, 35);
INSERT INTO public.questionnaires_questions (type_id, question_id, question_order) VALUES (1, 22, 20);
INSERT INTO public.questionnaires_questions (type_id, question_id, question_order) VALUES (1, 7, 5);
INSERT INTO public.questionnaires_questions (type_id, question_id, question_order) VALUES (1, 28, 26);
INSERT INTO public.questionnaires_questions (type_id, question_id, question_order) VALUES (1, 13, 11);
INSERT INTO public.questionnaires_questions (type_id, question_id, question_order) VALUES (1, 36, 34);
INSERT INTO public.questionnaires_questions (type_id, question_id, question_order) VALUES (1, 21, 19);
INSERT INTO public.questionnaires_questions (type_id, question_id, question_order) VALUES (1, 6, 4);
INSERT INTO public.questionnaires_questions (type_id, question_id, question_order) VALUES (1, 12, 10);
INSERT INTO public.questionnaires_questions (type_id, question_id, question_order) VALUES (1, 20, 18);
INSERT INTO public.questionnaires_questions (type_id, question_id, question_order) VALUES (1, 27, 25);
INSERT INTO public.questionnaires_questions (type_id, question_id, question_order) VALUES (1, 35, 33);
INSERT INTO public.questionnaires_questions (type_id, question_id, question_order) VALUES (1, 33, 31);
INSERT INTO public.questionnaires_questions (type_id, question_id, question_order) VALUES (2, 42, 3);
INSERT INTO public.questionnaires_questions (type_id, question_id, question_order) VALUES (2, 41, 2);
INSERT INTO public.questionnaires_questions (type_id, question_id, question_order) VALUES (2, 40, 1);
INSERT INTO public.questionnaires_questions (type_id, question_id, question_order) VALUES (2, 50, 4);
INSERT INTO public.questionnaires_questions (type_id, question_id, question_order) VALUES (2, 43, 5);
INSERT INTO public.questionnaires_questions (type_id, question_id, question_order) VALUES (2, 1, 6);
INSERT INTO public.questionnaires_questions (type_id, question_id, question_order) VALUES (2, 51, 7);
INSERT INTO public.questionnaires_questions (type_id, question_id, question_order) VALUES (2, 44, 8);
INSERT INTO public.questionnaires_questions (type_id, question_id, question_order) VALUES (2, 52, 9);
INSERT INTO public.questionnaires_questions (type_id, question_id, question_order) VALUES (2, 6, 10);
INSERT INTO public.questionnaires_questions (type_id, question_id, question_order) VALUES (2, 7, 11);
INSERT INTO public.questionnaires_questions (type_id, question_id, question_order) VALUES (2, 8, 12);
INSERT INTO public.questionnaires_questions (type_id, question_id, question_order) VALUES (2, 45, 13);
INSERT INTO public.questionnaires_questions (type_id, question_id, question_order) VALUES (2, 46, 14);
INSERT INTO public.questionnaires_questions (type_id, question_id, question_order) VALUES (2, 17, 15);
INSERT INTO public.questionnaires_questions (type_id, question_id, question_order) VALUES (2, 2, 17);
INSERT INTO public.questionnaires_questions (type_id, question_id, question_order) VALUES (2, 3, 18);
INSERT INTO public.questionnaires_questions (type_id, question_id, question_order) VALUES (2, 47, 19);
INSERT INTO public.questionnaires_questions (type_id, question_id, question_order) VALUES (2, 48, 20);
INSERT INTO public.questionnaires_questions (type_id, question_id, question_order) VALUES (2, 49, 21);
INSERT INTO public.questionnaires_questions (type_id, question_id, question_order) VALUES (2, 36, 22);
INSERT INTO public.questionnaires_questions (type_id, question_id, question_order) VALUES (2, 37, 23);
INSERT INTO public.questionnaires_questions (type_id, question_id, question_order) VALUES (2, 38, 24);


--
-- Data for Name: roles; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.roles (id, rolename) VALUES (1, 'ROLE_USER');
INSERT INTO public.roles (id, rolename) VALUES (2, 'ROLE_PATIENT');
INSERT INTO public.roles (id, rolename) VALUES (3, 'ROLE_ADMIN');
INSERT INTO public.roles (id, rolename) VALUES (4, 'ROLE_DOCTOR');
INSERT INTO public.roles (id, rolename) VALUES (5, 'ROLE_ANALYTIC');


--
-- Data for Name: spring_session; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- Data for Name: spring_session_attributes; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- Data for Name: users_roles; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.users_roles (user_id, role_id) VALUES (3, 1);
INSERT INTO public.users_roles (user_id, role_id) VALUES (3, 2);
INSERT INTO public.users_roles (user_id, role_id) VALUES (53, 2);
INSERT INTO public.users_roles (user_id, role_id) VALUES (53, 1);
INSERT INTO public.users_roles (user_id, role_id) VALUES (54, 2);
INSERT INTO public.users_roles (user_id, role_id) VALUES (54, 1);
INSERT INTO public.users_roles (user_id, role_id) VALUES (55, 1);
INSERT INTO public.users_roles (user_id, role_id) VALUES (55, 4);
INSERT INTO public.users_roles (user_id, role_id) VALUES (2, 2);
INSERT INTO public.users_roles (user_id, role_id) VALUES (2, 5);
INSERT INTO public.users_roles (user_id, role_id) VALUES (2, 1);
INSERT INTO public.users_roles (user_id, role_id) VALUES (2, 3);
INSERT INTO public.users_roles (user_id, role_id) VALUES (2, 4);
INSERT INTO public.users_roles (user_id, role_id) VALUES (56, 2);
INSERT INTO public.users_roles (user_id, role_id) VALUES (56, 1);
INSERT INTO public.users_roles (user_id, role_id) VALUES (57, 2);
INSERT INTO public.users_roles (user_id, role_id) VALUES (57, 1);
INSERT INTO public.users_roles (user_id, role_id) VALUES (28, 1);
INSERT INTO public.users_roles (user_id, role_id) VALUES (28, 4);


--
-- Name: activation_code_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.activation_code_id_seq', 66, true);


--
-- Name: answers_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.answers_id_seq', 2969, true);


--
-- Name: diagnoses_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.diagnoses_id_seq', 6, true);


--
-- Name: diagnosis_score_questionnaire_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.diagnosis_score_questionnaire_id_seq', 1, true);


--
-- Name: doctors_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.doctors_id_seq', 5, true);


--
-- Name: hibernate_sequence; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.hibernate_sequence', 41, true);


--
-- Name: patients_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.patients_id_seq', 26, true);


--
-- Name: question_weight_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.question_weight_id_seq', 41, true);


--
-- Name: questionnaire_types_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.questionnaire_types_id_seq', 2, true);


--
-- Name: questionnaires_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.questionnaires_id_seq', 266, true);


--
-- Name: questionnaires_questions_question_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.questionnaires_questions_question_id_seq', 1, false);


--
-- Name: questionnaires_questions_type_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.questionnaires_questions_type_id_seq', 1, true);


--
-- Name: questions_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.questions_id_seq', 16, true);


--
-- Name: roles_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.roles_id_seq', 2, true);


--
-- Name: users_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.users_id_seq', 63, true);


--
-- PostgreSQL database dump complete
--

