package com.example.gym_app.models;

import java.util.List;

public class User {
    private String id;
    private String firstName;
    private String lastName;
    private List<TrainingPlan> trainingPlans;

    public User() {}

    public User(String id, String firstName, String lastName, List<TrainingPlan> trainingPlans) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.trainingPlans = trainingPlans;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<TrainingPlan> getTrainingPlans() {
        return trainingPlans;
    }

    public void setTrainingPlans(List<TrainingPlan> trainingPlans) {
        this.trainingPlans = trainingPlans;
    }
}
