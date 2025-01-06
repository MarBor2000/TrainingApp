package com.example.gym_app.models;

import java.util.List;

public class TrainingPlan {
    private String goal;
    private int daysPerWeek;
    private List<Exercise> exercises;

    public TrainingPlan() {}

    public TrainingPlan(String goal, int daysPerWeek, List<Exercise> exercises) {
        this.goal = goal;
        this.daysPerWeek = daysPerWeek;
        this.exercises = exercises;
    }

    public String getGoal() {
        return goal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    public int getDaysPerWeek() {
        return daysPerWeek;
    }

    public void setDaysPerWeek(int daysPerWeek) {
        this.daysPerWeek = daysPerWeek;
    }

    public List<Exercise> getExercises() {
        return exercises;
    }

    public void setExercises(List<Exercise> exercises) {
        this.exercises = exercises;
    }
}
