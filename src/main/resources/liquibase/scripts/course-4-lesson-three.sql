-- liquibase formatted sql

-- changeset alikritskikh:1
CREATE INDEX student_name_index ON student(name);

-- changeset alikritskikh:2
CREATE UNIQUE INDEX faculty_name_and_color_index ON faculty (name, color);