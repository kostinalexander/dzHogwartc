ALTER TABLE student
    ADD CONSTRAINT age_constraint CHECK ( age>16 );

ALTER TABLE student
    ALTER COLUMN name SET NOT NULl;

ALTER TABLE student
    ADD CONSTRAINT nameUniq UNIQUE (name);

ALTER TABLE faculty
    ADD CONSTRAINT name_color UNIQUE (name,color);

ALTER TABLE student
    ALTER COLUMN age SET DEFAULT (20);



