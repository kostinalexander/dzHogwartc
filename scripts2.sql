CREATE TABLE Car (id int2 PRIMARY KEY ,brand varchar, model int2 );

CREATE TABLE Person (name varchar PRIMARY KEY , age int2, identityCard BOOLEAN, car_id int2 REFERENCES Car(id));