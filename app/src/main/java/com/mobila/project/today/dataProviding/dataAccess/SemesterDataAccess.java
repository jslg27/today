package com.mobila.project.today.dataProviding.dataAccess;

import com.mobila.project.today.dataProviding.DataKeyNotFoundException;
import com.mobila.project.today.model.Course;
import com.mobila.project.today.model.Identifiable;
import com.mobila.project.today.model.Semester;

import java.util.List;

public interface SemesterDataAccess {

    void addCourse(Identifiable semester, Course course) throws DataKeyNotFoundException;

    List<Course> getCourses(Identifiable semester) throws DataKeyNotFoundException;
}