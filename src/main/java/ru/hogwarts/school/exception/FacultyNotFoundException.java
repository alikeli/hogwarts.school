package ru.hogwarts.school.exception;

import ru.hogwarts.school.model.Faculty;

public class FacultyNotFoundException extends RuntimeException {
    private final long id;
    public FacultyNotFoundException(long id) {
        this.id = id;

    }

    public long getId() {
        return id;
    }
}
