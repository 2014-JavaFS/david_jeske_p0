drop table registrations cascade;
drop table users cascade;
drop table courses cascade;

create table users(
		user_id serial primary key,
		first_name varchar(20),
		last_name varchar(20),
		email varchar(30) unique not null,
		password varchar(32) not null,
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
		course int references courses(course_id) on update cascade on delete cascade not null,
		student int references users(user_id) on update cascade on delete cascade not null,
		unique(course, student),
		registration_date date default now()
);
		
insert into users --Faculty
values  (default, 'John', 'Doe', 'JohnDoe@uni.edu', '123', true),
		(default, 'Jane', 'Doe', 'JaneDoe@uni.edu', '123', true),
		(default, 'June', 'Doe', 'JuneDoe@uni.edu', '123', true);

insert into courses 
values  (default, 'MATH101-1', 'Intro Math', 3, default, (select user_id from users where email like 'JohnDoe@uni.edu')),
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
		
	
-- PROCEDURES
create or replace procedure new_registration(
		in p_course int,
		in p_student int
)
	language plpgsql
	as $$
	begin 
		insert into registrations values (default, p_course, p_student, default);
		update courses set enrolled = enrolled + 1 where course_id = p_course;
	end;
$$;

create or replace procedure cancel_registration(
		in p_course int,
		in p_student int
)
	language plpgsql
	as $$
	begin 
		update courses set enrolled = enrolled - 1 where course_id = p_course;
		delete from registrations r where r.course = p_course and r.student = p_student;
	end;
$$;	

create or replace procedure update_course(
		in p_course_id int,
		in p_course_code varchar(9),
		in p_course_title varchar(25),
		in p_capacity smallint,
		in p_enrolled smallint,
		in p_professor int
	)-- p_ for parameter
	language plpgsql
	as $$
	begin 
		update courses
		set course_id = p_course_id, 
			course_code = p_course_code, 
			course_title = p_course_title,
			capacity = p_capacity, 
			enrolled = p_enrolled, 
			professor = p_professor 
		where course_id = p_course_id;
	end;
$$;

-- CALLS	
call new_registration(1, 4); 
call new_registration(1, 5); 
call new_registration(1, 6); 		
		
select c.* 
from courses c 
inner join registrations r on c.course_id = r.course
where r.student = 4;











	