select *
from student;
select *
from student
where age > 10
  AND age < 20;
select name
from student;
select *
from student
where name like '%о%';
select *
from student
where age < id;
select *
from student
order by age;
select *
from student;

select faculty.name
from student,
     faculty
where student.faculty_id = faculty.id
  and student.name = 'Гермиона';

select student.name
from student as s,
     faculty as f
where s.faculty_id = f.id
  and f.name = 'Гриффиндор';