package ru.hogwarts.school.service;

import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import java.util.HashMap;

import static org.apache.catalina.security.SecurityUtil.remove;

public class FacultyService {
    private final HashMap<Long, Faculty> faculties = new HashMap<>();
    public long id = 0;

    public Faculty addFaculty(Faculty faculty) {
        faculty.setId(++id);
        faculties.put(id, faculty);
        return faculty;
    }

    private Faculty findFacultyById(long id) {
        return faculties.get(id);
    }

    public Faculty editFaculty(Faculty faculty) {
        if (!faculties.containsKey(id)) {
            return null;
        }
        faculties.put(faculty.getId(), faculty);
        return faculty;
    }

    public Faculty removeFaculty(Faculty faculty) {
        return faculties.remove(id);
    }
}
