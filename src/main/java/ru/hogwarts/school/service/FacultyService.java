package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import java.util.HashMap;

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
        return faculties.get(id);
    }

    public Faculty editFaculty(Long id, Faculty faculty) {
        if (!faculties.containsKey(id)) {
            return null;
        }
        faculties.put(id, faculty);
        return faculty;
    }

    public Faculty removeFaculty(Long id) {
        return faculties.remove(id);
    }
}
