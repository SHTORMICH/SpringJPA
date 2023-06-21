package com.kabaldin.todo.controller;

import com.kabaldin.todo.entity.TodoItem;
import com.kabaldin.todo.TodoItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/todo")
public class TodoItemController {
    private final TodoItemRepository todoItemRepository;

    @Autowired
    public TodoItemController(TodoItemRepository todoItemRepository) {
        this.todoItemRepository = todoItemRepository;
    }

    @GetMapping
    public List<TodoItem> getAllTodoItems() {
        return todoItemRepository.findAll();
    }

    @PostMapping
    public TodoItem createTodoItem(@RequestBody TodoItem todoItem) {
        return todoItemRepository.save(todoItem);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TodoItem> getTodoItemById(@PathVariable("id") Long id) {
        Optional<TodoItem> todoItemOptional = todoItemRepository.findById(id);
        if (todoItemOptional.isPresent()) {
            TodoItem todoItem = todoItemOptional.get();
            return ResponseEntity.ok(todoItem);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<TodoItem> updateTodoItem(@PathVariable("id") Long id, @RequestBody TodoItem updatedTodoItem) {
        Optional<TodoItem> todoItemOptional = todoItemRepository.findById(id);
        if (todoItemOptional.isPresent()) {
            TodoItem todoItem = todoItemOptional.get();
            todoItem.setTitle(updatedTodoItem.getTitle());
            todoItem.setDescription(updatedTodoItem.getDescription());
            todoItemRepository.save(todoItem);
            return ResponseEntity.ok(todoItem);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTodoItem(@PathVariable("id") Long id) {
        todoItemRepository.deleteById(id);
    }
}
