package com.skhu.oauthgoogleloginexample.controller;

import com.skhu.oauthgoogleloginexample.dto.TodoRequestDto;
import com.skhu.oauthgoogleloginexample.dto.TodoResponseDto;
import com.skhu.oauthgoogleloginexample.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/todos")
public class TodoController {

    private final TodoService todoService;

    @PostMapping
    public ResponseEntity<TodoResponseDto> createTodo(@RequestBody TodoRequestDto requestDto, Principal principal) {
        TodoResponseDto responseDto = todoService.createTodo(requestDto, principal);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @GetMapping
    public ResponseEntity<List<TodoResponseDto>> getAllTodos(Principal principal) {
        List<TodoResponseDto> todos = todoService.getAllTodos(principal);
        return ResponseEntity.ok(todos);
    }

    @GetMapping("/{todoId}")
    public ResponseEntity<TodoResponseDto> getTodo(@PathVariable Long todoId, Principal principal) {
        TodoResponseDto todo = todoService.getTodo(todoId, principal);
        return ResponseEntity.ok(todo);
    }

    @PutMapping("/{todoId}")
    public ResponseEntity<TodoResponseDto> updateTodo(@PathVariable Long todoId, @RequestBody TodoRequestDto requestDto, Principal principal) {
        TodoResponseDto todo = todoService.updateTodo(todoId, requestDto, principal);
        return ResponseEntity.ok(todo);
    }

    @DeleteMapping("/{todoId}")
    public ResponseEntity<Void> deleteTodo(@PathVariable Long todoId, Principal principal) {
        todoService.deleteTodo(todoId, principal);
        return ResponseEntity.noContent().build();
    }
}

