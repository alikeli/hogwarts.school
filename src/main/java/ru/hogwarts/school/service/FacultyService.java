package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.exception.FacultyNotFoundException;
import ru.hogwarts.school.exception.StudentNotFoundException;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.stream.Collectors;

import static org.apache.catalina.security.SecurityUtil.remove;

@Service
public class FacultyService {
    private final HashMap<Long, Faculty> faculties = new HashMap<>();
    public long id = 0;

    public Faculty addFaculty(Faculty faculty) {
        faculty.setId(++id);
        faculties.put(faculty.getId(), faculty);
        return faculty;
    }

    public Faculty findFacultyById(long id) {
        if (!faculties.containsKey(id)) {
            throw new StudentNotFoundException(id);
        }
        return faculties.get(id);
    }

    public Faculty editFaculty(Long id, Faculty faculty) {
        if (!faculties.containsKey(id)) {
            throw new FacultyNotFoundException(id);
        }
        Faculty oldFaculty = faculties.get(id);
        oldFaculty.setColor(oldFaculty.getColor());
        oldFaculty.setName(oldFaculty.getName());
        faculties.replace(id, oldFaculty);
        return oldFaculty;
    }

    public Faculty removeFaculty(Long id) {
        if (!faculties.containsKey(id)) {
            throw new StudentNotFoundException(id);
        }
        return faculties.remove(id);
    }

    public Collection<Faculty> getFacultyByColor(String color) {
        return faculties.values().stream()
                .filter(student -> student.getColor() == color)
                .collect(Collectors.toList());

    }
}
