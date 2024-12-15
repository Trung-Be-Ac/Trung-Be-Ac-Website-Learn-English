create database DBWebTA;
use DBWebTA;

create table `user`	(
user_id int primary key auto_increment,
user_name varchar(50) not null,
user_email varchar(50),
user_level int,
user_datejoined date,
user_role varchar(50),
user_type int,
user_picture text,
username varchar(50),
`password` varchar(50)
);
select * from `user`;
select * from `user` where user_id = ?;
update `user` set user_name=?, user_email=?, user_level=?,user_datejoined=?,user_role=?,user_type=?, user_picture= ? where user_id = ? ;
update `user` set `password` = ? where user_id = ?;
select * from `user` where user_name = ?;
delete from `user` where user_id = ?;
select * from `user` where username = ? and `password` = ?;
insert into `user` (user_name, user_email, user_level, user_datejoined, user_role, user_type, user_picture, username, `password`)
values 
('Nguyễn Văn A', 'NVA01@gmail.com', 5, '2024-01-01', 'admin', true, '.....jpg', 'acc1', 'pass1'),
('Trần Văn B', 'NVB02@gmail.com', 5, '2024-01-02', 'teacher', false, '.....jpg', 'acc2', 'pass2'),
('Đô Thành C', 'DTC@gmail.com', 4, '2024-01-03', 'teacher', true, '.....jpg', 'acc3', 'pass3'),
('Phạm Tạ D', 'PTD@gmail.com', 1, '2024-01-04', 'student', false, '.....jpg', 'acc4', 'pass4'),
('Phan Mạnh E', 'PME@gmail.com', 2, '2024-01-05', 'student', true, '.....jpg', 'acc5', 'pass5');

create table `process` (
process_id int primary key auto_increment,
user_id int,
course_id int,
process_status double,
process_result varchar(50),
foreign key (user_id) references `user`(user_id),
foreign key (course_id) references course(course_id)
);
select * from `process`;
insert into `process` (user_id, course_id, process_status, process_result)
values 
(1, 1, 60.0, 'Learning'),
(2, 2, 75.0, 'Improving'),
(3, 3, 85.0, 'Achieving'),
(4, 4, 92.0, 'Excelling'),
(5, 5, 98.0, 'Mastering');
-- -- --
create table course(
course_id int primary key auto_increment,
course_name varchar(50),
course_level int,
course_description text,
user_id int ,
foreign key (user_id) references `user`(user_id)
);
select * from course ;
select * from course where course_id = ?;
delete from course where course_id=?;
insert into course (course_name, course_level, course_description, user_id)
values 
('English Basics', 1, 'Ngữ pháp và từ vựng tiếng Anh cơ bản.', 2),
('Intermediate English', 3, 'Mở rộng kỹ năng tiếng Anh lên trình độ trung cấp.', 2),
('Advanced English', 4, 'Thành thạo cách sử dụng tiếng Anh nâng cao.', 3),
('Business English', 5, 'Tiếng Anh dành cho các tình huống giao tiếp chuyên nghiệp và học thuật.', 3),
('Travel English', 2, 'Tiếng Anh cơ bản phục vụ học tập và giao tiếp hàng ngày.', 3);
SELECT u.user_name
FROM course c
JOIN `user` u ON c.user_id = u.user_id
WHERE c.course_id = 2;
-- -- --
create table lesson(
lesson_id int primary key auto_increment,
course_id int,
lesson_description text,
lesson_name varchar(50),
foreign key (course_id) references course(course_id)
);
select * from lesson where lesson_id = ?;
update `user` set `password` = ? where user_id = ?;
delete from lesson where lesson_id=?;
insert into lesson (course_id, lesson_description, lesson_name)
values 
(1, 'Introduction to the English alphabet and basic pronunciation.', 'Lesson 1: Alphabet'),
(2, 'Using modal verbs to express ability, permission, and necessity.', 'Lesson 2: Modal Verbs'),
(3, 'Learning common idiomatic expressions in English for everyday use.', 'Lesson 3: Idioms'),
(4, 'Writing clear and professional emails for various purposes.', 'Lesson 4: Business Writing'),
(5, 'Common English phrases for communication in everyday situations.', 'Lesson 5: Travel Phrases');

-- -- --
create table test(
test_id int primary key auto_increment,
test_name varchar (100),
quiz_id int,
lesson_id int,
test_questiontype varchar(10),
test_timelimit time,
foreign key (lesson_id) references lesson(lesson_id),
foreign key (quiz_id) references quiz(quiz_id)
);
select * from test;
insert into test (test_name,quiz_id,lesson_id,test_questiontype,test_timelimit) values (?,?,?,?,?);
select * from test where test_id = ?;
update test set test_name = ?,quiz_id= ?,lesson_id= ?,test_questiontype= ?,test_timelimit = ? where test_id = ?;
delete from test where test_id=?;

-- -- --
create table quiz(
quiz_id int primary key auto_increment,
quiz_title varchar(50),
quiz_text text,
quiz_a varchar(100),
quiz_b varchar(100),
quiz_c varchar(100),
quiz_d varchar(100),
quiz_correctanswer varchar(100),
user_id int,
foreign key (user_id) references `user`(user_id)
);
select * from quiz;
delete from quiz where quiz_id=?;
update quiz set quiz_tilte=?,quiz_text=?, quiz_a=?, quiz_b=?, quiz_c=?, quiz_d=?, quiz_correctanswer=? where quiz_id = ? ;
insert into quiz (quiz_title, quiz_text, quiz_a, quiz_b, quiz_c, quiz_d, quiz_correctanswer, user_id)
values
(?,?,?,?,?,?,?,?),
('Quiz 1', 'What is the English word for "mèo"?', 'Dog', 'Cat', 'Bird', 'Fish', 'Cat', 2),
('Quiz 2', 'Choose the correct sentence: "She ___ to the store yesterday."', 'goes', 'went', 'going', 'gone', 'went', 3);

SELECT u.user_name
FROM quiz q
JOIN `user` u ON q.user_id = u.user_id
WHERE q.quiz_id = ?;