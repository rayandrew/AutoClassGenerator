package org.rplsd.autocg;

import java.util.HashMap;
import java.util.List;

public class Course {
    private String courseName;
    private HashMap<String, Integer> facilities;
    private int duration;
    private List<String> lecturers;

    public Course(String courseName, HashMap<String, Integer> facilities, int duration, List<String> lecturers) {
        this.courseName = courseName;
        this.facilities = facilities;
        this.duration = duration;
        this.lecturers = lecturers;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public HashMap<String, Integer> getFacilities() {
        return facilities;
    }

    public void setFacilities(HashMap<String, Integer> facilities) {
        this.facilities = facilities;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public List<String> getLecturers() {
        return lecturers;
    }

    public void setLecturers(List<String> lecturers) {
        this.lecturers = lecturers;
    }
}