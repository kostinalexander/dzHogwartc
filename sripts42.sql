ALTER TABLE student
    ADD CONSTRAINT age_constraint CHECK ( age>16 );


ALTER TABLE student
    ALTER COLUMN name SET NOT NULL ;

ALTER TABLE student
    ADD CONSTRAINT nameUniq UNIQUE (name);

ALTER TABLE faculty
    ADD CONSTRAINT name_color UNIQUE (name,color);

ALTER TABLE student
    ALTER COLUMN age SET DEFAULT (20);


SELECT student.name , student.age , faculty.name FROM student
INNER JOIN faculty ON student.faculty_id = faculty.id;

SELECT student.name, student.age FROM studen
INNER JOIN avatar a on student.id = a.student_id;