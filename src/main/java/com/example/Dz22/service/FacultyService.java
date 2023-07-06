package com.example.Dz22.service;

import com.example.Dz22.model.Faculty;

public interface FacultyService {
    Faculty addFaculty(Faculty faculty);
    Faculty findFaculty(long id);
    Faculty editFaculty(Faculty faculty);
    void deleteFaculty(long id);
}
