select user(), database();

use mybatis_study;

select * from addresses;
select * from students;
select * from tutors;
select course_id , name , description , start_date , end_date , tutor_id  from courses;
select * from course_enrollment;
select * from user_pics up ;


-- studentMapper sql

select stud_id as studId, name, email, dob, phone
  from students
 where stud_id=1;
 
select stud_id, name, email, dob, phone, 
               substring(phone, 1, 3) as f, 
               substring(phone, 5, 3) as m, 
               substring(phone, 9, 4) as l
  from students where stud_id=1;
 
select stud_id as studId, name, email, dob, phone
  from students;

desc students;

insert into students(stud_id, name, email, phone, dob) 
values(3, '홍길동', 'test@test.co.kr', '010-1234-1234', '1990-02-28');

delete from students  where stud_id =3;


desc students;