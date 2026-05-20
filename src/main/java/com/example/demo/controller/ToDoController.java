package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Task;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ToDoController {

    private List<Task> tasks = new ArrayList<>();

    @GetMapping("/tasks")
    public List<Task> getTasks() {

        return tasks;
    }

    @PostMapping("/tasks")
    public Task createTask(@RequestBody Task task) {
        tasks.add(task);
        return task;
    }}
