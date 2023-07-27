--liquibase formatted sql

--changeset kostin:1
CREATE TABLE ignores(  --test
    id SERIAL,
    email TEXT
)

--changeset kostin:2
CREATE INDEX students_name_index ON student(name);

--changeset kostin:3
CREATE INDEX faculty_find ON faculty(name,color)