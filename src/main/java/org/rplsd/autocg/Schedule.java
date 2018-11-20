package org.rplsd.autocg;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class Schedule {
    private List<Classroom> classrooms;
    private List<Lecturer> lecturers;
    private List<Courses> courses;

    public Schedule(ArrayList<Classroom> classrooms, 
        ArrayList<Lecturer> lecturers, 
        ArrayList<Courses> courses) {
        this.classrooms = classrooms;
        this.lecturers = lecturers;
        this.courses = courses;
    }
}
