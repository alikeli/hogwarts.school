package ru.hogwarts.school.service;

import ru.hogwarts.school.model.Student;

import java.util.HashMap;

public class StudentService {
    private final HashMap<Long, Student> students = new HashMap<>();
    public long id = 0;

    public Student addStudent(Student student) {
        student.setId(++id);
        students.put(id, student);
        return student;
    }

    public Student findStudent(Long id) {
        return students.get(id);
    }

    public Student editStudent(Student student) {
        if (!students.containsKey(id)) {
            return null;
        }
        students.put(student.getId(), student);
        return student;
    }

    public Student removeStudent(Long id) {
        return students.remove(id);
    }
}
