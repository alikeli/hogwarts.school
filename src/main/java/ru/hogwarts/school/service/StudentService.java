package ru.hogwarts.school.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
@Service

public class StudentService {
    private final HashMap<Long, Student> students = new HashMap<>();
    public long id = 0;

    public Student addStudent(Student student) {
        student.setId(++id);
        students.put(student.getId(), student);
        return student;
    }

    public Student findStudent(Long id) {
        return students.get(id);
    }

    public Student editStudent(long id, Student student) {
        if (!students.containsKey(id)) {
            return null;
        }
        students.put(id, student);
        return student;
    }

    public Student removeStudent(Long id) {
        return students.remove(id);
    }

    public Collection<Student> getStudentByAge(Integer age) {
       ArrayList<Student> result = new ArrayList<>();
        for (Student student : students.values()) {
            if(student.getAge() == age) {
                result.add(student);
            }
        }
        return result;

    }
}
