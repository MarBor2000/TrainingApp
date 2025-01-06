package com.example.gym_app.controllers;

import com.example.gym_app.models.TrainingPlan;
import com.example.gym_app.models.User;
import com.example.gym_app.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping
    public ResponseEntity<Void> addUser(@RequestBody User user) {
        userService.addUser(user);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable String id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{userId}/plans")
    public ResponseEntity<Void> addTrainingPlan(
            @PathVariable String userId,
            @RequestBody TrainingPlan trainingPlan) {
        userService.addTrainingPlan(userId, trainingPlan);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{userId}/plans")
    public List<TrainingPlan> getTrainingPlans(@PathVariable String userId) {
        return userService.getTrainingPlans(userId);
    }

}