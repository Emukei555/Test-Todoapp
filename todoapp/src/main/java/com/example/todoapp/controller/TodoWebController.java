package com.example.todoapp.controller;

import com.example.todoapp.entity.Todo;
import com.example.todoapp.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/todos")
public class TodoWebController {

    @Autowired
    private TodoService todoService;

    @GetMapping
    public String listTodos(Model model) {
        model.addAttribute("todos", todoService.getAllTodos());
        return "index";
    }

    @PostMapping
    public String createTodo(@ModelAttribute Todo todo) {
        todoService.createTodo(todo);
        return "redirect:/todos";
    }

    @GetMapping("/edit/{id}")
    public String editTodo(@PathVariable Long id, Model model) {
        model.addAttribute("todo", todoService.getTodoById(id).orElseThrow());
        return "edit";
    }

    @PostMapping("/update/{id}")
    public String updateTodo(@PathVariable Long id, @ModelAttribute Todo todo) {
        todoService.updateTodo(id, todo);
        return "redirect:/todos";
    }

    @GetMapping("/delete/{id}")
    public String deleteTodo(@PathVariable Long id) {
        todoService.deleteTodo(id);
        return "redirect:/todos";
    }
}