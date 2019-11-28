package com.mobila.project.today.dataProviding.dataAccess;

import com.mobila.project.today.dataProviding.DataKeyNotFoundException;
import com.mobila.project.today.model.Course;
import com.mobila.project.today.model.Identifiable;

import java.util.Date;

public interface TaskDataAccess {
    Course getCourse(Identifiable section) throws DataKeyNotFoundException;

    Date getDate(Identifiable task);

    void setDate(Identifiable task, Date date);
}