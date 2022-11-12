package ru.hogwarts.school.component;

import org.springframework.stereotype.Component;
import ru.hogwarts.school.controller.entity.Faculty;
import ru.hogwarts.school.controller.entity.Student;
import ru.hogwarts.school.record.FacultyRecord;
import ru.hogwarts.school.record.StudentRecord;
@Component
public class RecordMapper {
    public StudentRecord toRecord(Student student) {
        StudentRecord studentRecord = new StudentRecord();
        studentRecord.setId(student.getId());
        studentRecord.setName(student.getName());
        studentRecord.setAge(student.getAge());
        return studentRecord;

    }

    public FacultyRecord toRecord(Faculty faculty) {
        FacultyRecord facultyRecord = new FacultyRecord();
        facultyRecord.setId(faculty.getId());
        facultyRecord.setName(faculty.getName());
        facultyRecord.setColor(faculty.getColor());
        return facultyRecord;

    }

    public Student toEntity(StudentRecord studentRecord) {
        Student student = new Student();
        student.setId(studentRecord.getId());
        student.setName(studentRecord.getName());
        student.setAge(studentRecord.getAge());
        return student;

    }

    public Faculty toEntity(FacultyRecord facultyRecord) {
        Faculty faculty = new Faculty();
        faculty.setId(faculty.getId());
        faculty.setName(faculty.getName());
        faculty.setColor(faculty.getColor());
        return faculty;

    }




}
