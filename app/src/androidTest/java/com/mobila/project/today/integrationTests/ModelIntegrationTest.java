package com.mobila.project.today.integrationTests;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;

import com.mobila.project.today.model.Semester;
import com.mobila.project.today.model.Student;
import com.mobila.project.today.model.Task;
import com.mobila.project.today.model.dataProviding.dataAccess.OrganizerDataProvider;
import com.mobila.project.today.model.dataProviding.dataAccess.RootDataAccess;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

public class ModelIntegrationTest {
    private Context instrumentationContext;

    private RootDataAccess rootDataAccess;
    private Student student;

    private Semester semester1;
    private Semester semester2;
    private Semester semester3;
    private List<Semester> semesters;
    private List<Semester> resultSemesters;

    private Task task1;
    private Task task2;
    private Task task3;
    private List<Task> tasks;
    private List<Task> resultTasks;

    @Before
    public void setup() {
        this.instrumentationContext = InstrumentationRegistry.getInstrumentation().getContext();

        this.rootDataAccess = OrganizerDataProvider.getInstance().getRootDataAccess();
        this.student = new Student();

        this.semester1 = new Semester(1);
        this.semester2 = new Semester(2);
        this.semester3 = new Semester(3);
        this.semesters = new LinkedList<>();
        this.semesters.add(this.semester1);
        this.semesters.add(this.semester2);
        this.semesters.add(this.semester3);

        Date anyWhen = new Date(123456789);
        this.task1 = new Task("1_id", "1_content", anyWhen);
        this.task2 = new Task("2_id", "2_content", anyWhen);
        this.task3 = new Task("3_id", "3_content", anyWhen);
        this.tasks = new LinkedList<>();
        this.tasks.add(task1);
        this.tasks.add(task2);
        this.tasks.add(task3);

        this.openDatabaseConnection();
    }

    private void openDatabaseConnection() {
//      this can also be seen as test. if there are problems in setup() approve this
        OrganizerDataProvider.getInstance().openDbConnection(this.instrumentationContext);
    }

    @After
    public void tearDown() {
        OrganizerDataProvider.getInstance().closeDbConnection();
    }


    private void addSemestersToStudent() {
        this.student.addSemester(semester1);
        this.student.addSemester(semester2);
        this.student.addSemester(semester3);
    }

    @Test
    public void addAndGetSemestersViaStudent_Test() {
        this.resultSemesters = this.student.getAllSemesters();
        assertNotNull(this.resultSemesters);
        assertTrue(this.resultSemesters.isEmpty());

        this.addSemestersToStudent();
        this.resultSemesters = this.student.getAllSemesters();

        assertEquals(this.semesters.size(), this.resultSemesters.size());
        Semester expected;
        Semester actual;
        for (int i = 0; i < this.resultSemesters.size(); i++) {
            actual = this.resultSemesters.get(i);
            expected = this.semesters.get(i);
            assertEquals(expected, actual);
        }
    }

    @Test
    public void passNullToAddSemesterViaStudent_Test() {
        this.addSemestersToStudent();
        this.resultSemesters = this.student.getAllSemesters();
        int expectedSemestersSize = this.resultSemesters.size();

        this.student.addSemester(null);
        this.resultSemesters = this.student.getAllSemesters();

        assertEquals(expectedSemestersSize, this.resultSemesters.size());
    }

    @Test
    public void deleteSemestersViaStudent_Test() {
        this.addSemestersToStudent();

        this.resultSemesters = this.rootDataAccess.getAllSemesters();
        int expectedSemestersSize = this.resultSemesters.size();

        this.student.removeSemester(this.semester2);

        assertEquals(expectedSemestersSize, this.resultSemesters.size());
        assertTrue(this.resultSemesters.contains(semester1));
        assertTrue(this.resultSemesters.contains(semester3));
        assertFalse(this.resultSemesters.contains(semester2));
    }

    @Test
    public void passNullToDeleteSemesterViaStudent_Test(){
        this.resultSemesters = this.student.getAllSemesters();
        int expectedSemestersSize = this.resultSemesters.size();

        this.student.removeSemester(null);

        this.resultSemesters = this.student.getAllSemesters();
        assertEquals(expectedSemestersSize, this.resultSemesters.size());
    }

    @Test
    public void getEmptyTasksViaStudent_Test() {
        this.resultTasks = this.student.getAllTasks();

        assertNotNull(this.resultSemesters);
        assertTrue(this.resultTasks.isEmpty());
    }
}