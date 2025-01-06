package com.example.gym_app.models;

public class Exercise {
    private String name;
    private int sets;
    private int reps;
    private String tempo;
    private String rest;

    public Exercise() {}

    public Exercise(String name, int sets, int reps, String tempo, String rest) {
        this.name = name;
        this.sets = sets;
        this.reps = reps;
        this.tempo = tempo;
        this.rest = rest;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSets() {
        return sets;
    }

    public void setSets(int sets) {
        this.sets = sets;
    }

    public int getReps() {
        return reps;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }

    public String getTempo() {
        return tempo;
    }

    public void setTempo(String tempo) {
        this.tempo = tempo;
    }

    public String getRest() {
        return rest;
    }

    public void setRest(String rest) {
        this.rest = rest;
    }
}
