USE test-db-1;

DROP TABLE student_info;

CREATE TABLE student_info(
    student_id      INT         AUTO_INCREMENT PRIMARY KEY,
    first_name      VARCHAR(20) NOT NULL,
    last_name       VARCHAR(30) NOT NULL,
    grade_level     VARCHAR(10)
);

SELECT * FROM student_info;

INSERT INTO student_info
(student_id, first_name, last_name, grade_level)
VALUES
(123, "john", "smith", "freshman"),
(124, "jane", "doe", "senior");

SELECT * FROM student_info;

SHOW tables;

DESCRIBE student_info;
