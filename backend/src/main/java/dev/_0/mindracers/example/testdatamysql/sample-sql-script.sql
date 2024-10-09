-- drop db to wipe testdb1
DROP DATABASE testdb1;

-- create testdb1 for testing
CREATE DATABASE testdb1;

-- use testdb1 as the main db to run sql commands against
USE testdb1;

-- drop student_info table to wipe all values
DROP TABLE student_info;

-- create student_info table
CREATE TABLE student_info(
    student_id      INT         AUTO_INCREMENT PRIMARY KEY,
    first_name      VARCHAR(20) NOT NULL,
    last_name       VARCHAR(30) NOT NULL,
    grade_level     VARCHAR(10)
);

/* example sql queries */

SELECT * FROM student_info;

INSERT INTO student_info
(student_id, first_name, last_name, grade_level)
VALUES
(123, "john", "smith", "freshman"),
(124, "jane", "doe", "senior");

SELECT * FROM student_info;

SHOW tables;

DESCRIBE student_info;
