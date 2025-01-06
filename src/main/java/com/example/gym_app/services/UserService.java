package com.example.gym_app.services;

import com.example.gym_app.models.Exercise;
import com.example.gym_app.models.TrainingPlan;
import com.example.gym_app.models.User;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

@Service
public class UserService {

    private final MongoCollection<Document> userCollection;

    public UserService(MongoDatabase mongoDatabase) {
        this.userCollection = mongoDatabase.getCollection("users");
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();

        for (Document userDoc : userCollection.find()) {
            List<Document> planDocs = userDoc.getList("trainingPlans", Document.class);
            List<TrainingPlan> trainingPlans = new ArrayList<>();

            if (planDocs != null) {
                for (Document planDoc : planDocs) {
                    List<Document> exerciseDocs = planDoc.getList("exercises", Document.class);
                    List<Exercise> exercises = new ArrayList<>();

                    if (exerciseDocs != null) {
                        for (Document exerciseDoc : exerciseDocs) {
                            exercises.add(new Exercise(
                                    exerciseDoc.getString("name"),
                                    exerciseDoc.getInteger("sets"),
                                    exerciseDoc.getInteger("reps"),
                                    exerciseDoc.getString("tempo"),
                                    exerciseDoc.getString("rest")
                            ));
                        }
                    }

                    trainingPlans.add(new TrainingPlan(
                            planDoc.getString("goal"),
                            planDoc.getInteger("daysPerWeek"),
                            exercises
                    ));
                }
            }

            users.add(new User(
                    userDoc.getObjectId("_id").toString(),
                    userDoc.getString("firstName"),
                    userDoc.getString("lastName"),
                    trainingPlans
            ));
        }

        return users;
    }

    public void addUser(User user) {
        Document doc = new Document()
                .append("firstName", user.getFirstName())
                .append("lastName", user.getLastName());
        userCollection.insertOne(doc);
    }

    public void deleteUser(String id) {
        userCollection.deleteOne(eq("_id", id));
    }

    public void addTrainingPlan(String userId, TrainingPlan trainingPlan) {
        Document query = new Document("_id", userId);
        Document update = new Document("$push", new Document("trainingPlans", trainingPlan));
        userCollection.updateOne(query, update);
    }


    public List<TrainingPlan> getTrainingPlans(String userId) {
        Document userDoc = userCollection.find(eq("_id", userId)).first();
        if (userDoc == null) return new ArrayList<>();

        List<Document> plans = userDoc.getList("trainingPlans", Document.class);
        List<TrainingPlan> trainingPlans = new ArrayList<>();
        for (Document planDoc : plans) {
            TrainingPlan plan = new TrainingPlan(
                    planDoc.getString("goal"),
                    planDoc.getInteger("daysPerWeek"),
                    planDoc.getList("exercises", Document.class)
                            .stream()
                            .map(exDoc -> new Exercise(
                                    exDoc.getString("name"),
                                    exDoc.getInteger("sets"),
                                    exDoc.getInteger("reps"),
                                    exDoc.getString("tempo"),
                                    exDoc.getString("rest")
                            ))
                            .toList()
            );
            trainingPlans.add(plan);
        }
        return trainingPlans;
    }

}

