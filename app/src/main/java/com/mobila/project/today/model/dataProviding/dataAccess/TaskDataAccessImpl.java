package com.mobila.project.today.model.dataProviding.dataAccess;

import com.mobila.project.today.model.Identifiable;
import com.mobila.project.today.model.dataProviding.DataKeyNotFoundException;

import java.util.Date;

import android.content.ContentValues;

import com.mobila.project.today.model.dataProviding.dataAccess.databank.TaskTable;

class TaskDataAccessImpl extends ParentDataAccessImpl implements TaskDataAccess {
    private static TaskDataAccess instance;

    public static final String TAG = TaskDataAccessImpl.class.getName();

    private TaskDataAccessImpl() {
    }

    static TaskDataAccess getInstance() {
        if (instance == null)
            instance = new TaskDataAccessImpl();
        return instance;
    }

    @Override
    public void setDeadline(Identifiable task, Date date) throws DataKeyNotFoundException {
        ContentValues values = new ContentValues();
        values.put(TaskTable.COLUMN_DEADLINE, date.getTime());
        this.updateTaskInDB(task, values);
    }

    @Override
    public void setContent(Identifiable task, String content) throws DataKeyNotFoundException {
        ContentValues values = new ContentValues();
        values.put(TaskTable.COLUMN_CONTENT, content);
        this.updateTaskInDB(task, values);
    }

    private void updateTaskInDB(Identifiable task, ContentValues values) {
        this.database.update(TaskTable.TABLE_NAME, values,
                TaskTable.COLUMN_ID + "=?", new String[]{task.getID()});
    }
}