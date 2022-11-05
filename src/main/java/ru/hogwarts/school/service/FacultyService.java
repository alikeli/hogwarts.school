package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repositories.FacultyRepository;
import java.util.List;

import static org.apache.catalina.security.SecurityUtil.remove;

@Service
public class FacultyService {
    private final FacultyRepository facultyRepository;

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public Faculty addFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    public Faculty findFacultyById(long id) {
        return facultyRepository.findById(id).get();
    }

    public Faculty editFaculty(Long id, Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    public void removeFaculty(Long id) {
        facultyRepository.deleteById(id);
    }


    public List<Faculty> getFacultyByNameIgnoreCase(String name) {
        return facultyRepository.getFacultyByNameIgnoreCase(name);
    }

    public List<Faculty> getFacultyByColorIgnoreCase(String color) {
        return facultyRepository.getFacultyByColorIgnoreCase(color);
    }
}
