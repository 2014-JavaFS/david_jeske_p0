drop table registrations cascade;
drop table users cascade;
drop table courses cascade;

create table users(
		user_id serial primary key,
		first_name varchar(20),
		last_name varchar(30),
		email varchar(50) unique not null,
		password varchar(64) not null,
		is_faculty boolean default false
);

create table courses(
		course_code varchar(9) primary key,
		course_title varchar(25) not null,
		credit_hours smallint not null check (credit_hours>=0 and credit_hours<=9),
		capacity smallint not null check (capacity>0),
		enrolled smallint default 0,
		professor int references users(user_id) not null
);

--bridging table
create table registrations(
		registration_id serial primary key,
		course_code varchar(9) references courses(course_code) not null,
		student int references users(user_id) not null,
		registration_date date default now()
);
		
insert into users --Faculty
values  (default, 'John', 'Doe', 'JohnDoe@uni.edu', '123', true),
		(default, 'Jane', 'Doe', 'JaneDoe@uni.edu', '123', true),
		(default, 'June', 'Doe', 'JuneDoe@uni.edu', '123', true);

insert into courses 
values  ('MATH101-1', 'Intro Math', 2, 3, default, (select user_id from users where email like 'JohnDoe@uni.edu')),
		('MATH101-2', 'Intro Math', 2, 3, default, (select user_id from users where email like 'JohnDoe@uni.edu')),
		('HIST204-1', 'US History II', 3, 10, default, (select user_id from users where email like 'JaneDoe@uni.edu')),
		('POLS207-1', 'US Government', 3, 10, default, (select user_id from users where email like 'JaneDoe@uni.edu')),
		('PSYC107-1', 'Intro Psychology', 4, 10, default, (select user_id from users where email like 'JuneDoe@uni.edu')),
		('PSYC107-2', 'Intro Psychology', 4, 10, default, (select user_id from users where email like 'JuneDoe@uni.edu'));
		
insert into users --Students
values  (default, 'Jimmy', 'Smith', 'JimmySmith@uni.edu', '123', false),
		(default, 'Jenny', 'Smith', 'JennySmith@uni.edu', '123', false);
		
insert into registrations 
values	(default, 'MATH101-1', (select user_id from users where email like 'JimmySmith@uni.edu'), default),
		(default, 'MATH101-1', (select user_id from users where email like 'JennySmith@uni.edu'), default);