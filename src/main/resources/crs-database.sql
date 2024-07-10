drop table registrations;
drop table courses;
drop table users;
drop type user_enum;

create table courses(
		course_id serial primary key,
		subject varchar(4) not null,
		course_number smallint not null check (course_number>100 and course_number<999),
		section_number smallint not null check (section_number>0 and section_number<99),
		unique (subject, course_number, section_number),
		course_title varchar(20) not null,
		credit_hours smallint not null check (credit_hours>=0 and credit_hours<=9),
		capacity smallint not null check (capacity>0),
		professor int
);

create type user_enum as enum ('STUDENT', 'FACULTY');

create table users(
		user_id serial primary key,
		user_type user_enum not null default 'STUDENT',
		first_name varchar(20),
		last_name varchar(30),
		email varchar(50) unique not null,
		password varchar(64) not null default 'password'
		--DEFAULT IS AN INSANE SECURITY RISK. ONLY THERE FOR SIMPLICITY DURING TESTING
);

alter table courses
add constraint fk_professor_user_id foreign key (professor)
references users(user_id);

--bridging table
create table registrations(
		course int,
		student int
);

alter table registrations
add constraint fk_course_id foreign key (course)
references courses(course_id);

alter table registrations
add constraint fk_student_user_id foreign key (student)
references users(user_id);


insert into
		courses(subject, course_number,	section_number,	course_title, credit_hours, capacity)
values ('MATH', 101, 1,'Intro Math', 3, 50),
		('MATH', 101, 2,'Intro Math', 3, 50),
		('HIST', 204, 1,'US History II', 3, 35),
		('POLS', 207, 1,'US Government', 3, 75),
		('PSYC', 107, 1,'Intro Psychology', 4, 25),
		('PSYC', 107, 2,'Intro Psychology', 4, 25);
		
insert into
		users
values (default, 'FACULTY', 'John', 'Smith', 'JSmith01@uni.edu', default),
		(default, default, 'Jimmy', 'Dean', 'JDean01@uni.edu', default),
		(default, default, 'James', 'Cameron', 'JCameron01@uni.edu', default),
		(default, default, 'Joan', 'Ofark', 'JOfark01@uni.edu', default),
		(default, 'FACULTY', 'Jane', 'Smith', 'JSmith02@uni.edu', default);