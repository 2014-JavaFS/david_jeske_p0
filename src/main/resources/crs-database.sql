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
		course_id serial primary key,
		course_code varchar(9) unique,
		course_title varchar(25) not null,
		capacity smallint not null check (capacity>0),
		enrolled smallint default 0,
		professor int references users(user_id) not null
);

--bridging table
create table registrations(
		registration_id serial primary key,
		course int references courses(course_id) not null,
		student int references users(user_id) not null,
		registration_date date default now()
);
		
insert into users --Faculty
values  (default, 'John', 'Doe', 'JohnDoe@uni.edu', '123', true),
		(default, 'Jane', 'Doe', 'JaneDoe@uni.edu', '123', true),
		(default, 'June', 'Doe', 'JuneDoe@uni.edu', '123', true);

insert into courses 
values  (default, 'MATH101-1', 'Intro Math', 3, 2, (select user_id from users where email like 'JohnDoe@uni.edu')),
		(default, 'MATH101-2', 'Intro Math', 3, default, (select user_id from users where email like 'JohnDoe@uni.edu')),
		(default, 'HIST204-1', 'US History II', 10, default, (select user_id from users where email like 'JaneDoe@uni.edu')),
		(default, 'POLS207-1', 'US Government', 10, default, (select user_id from users where email like 'JaneDoe@uni.edu')),
		(default, 'PSYC107-1', 'Intro Psychology', 10, default, (select user_id from users where email like 'JuneDoe@uni.edu')),
		(default, 'PSYC107-2', 'Intro Psychology', 10, default, (select user_id from users where email like 'JuneDoe@uni.edu'));
		
insert into users --Students
values  (default, 'Sam', 'Smith', 'SamSmith@uni.edu', '123', false),
		(default, 'Sammy', 'Smith', 'SammySmith@uni.edu', '123', false),
		(default, 'Frank', 'Smith', 'FrankSmith@uni.edu', '123', false),
		(default, 'Frankie', 'Smith', 'FrankieSmith@uni.edu', '123', false),
		(default, 'Theo', 'Smith', 'TheoSmith@uni.edu', '123', false),
		(default, 'Thea', 'Smith', 'TheaSmith@uni.edu', '123', false);
		
insert into registrations 
values	(default, (select course_id from courses where course_code like 'MATH101-1'), (select user_id from users where email like 'SamSmith@uni.edu'), default),	
		(default, (select course_id from courses where course_code like 'MATH101-1'), (select user_id from users where email like 'SammySmith@uni.edu'), default),
		(default, (select course_id from courses where course_code like 'MATH101-1'), (select user_id from users where email like 'FrankSmith@uni.edu'), default);
		
	
	
	