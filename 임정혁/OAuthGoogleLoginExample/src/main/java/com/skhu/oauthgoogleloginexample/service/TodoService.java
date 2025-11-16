package com.skhu.oauthgoogleloginexample.service;

import com.skhu.oauthgoogleloginexample.domain.Priority;
import com.skhu.oauthgoogleloginexample.domain.Role;
import com.skhu.oauthgoogleloginexample.domain.Todo;
import com.skhu.oauthgoogleloginexample.domain.User;
import com.skhu.oauthgoogleloginexample.dto.TodoRequestDto;
import com.skhu.oauthgoogleloginexample.dto.TodoResponseDto;
import com.skhu.oauthgoogleloginexample.repository.TodoRepository;
import com.skhu.oauthgoogleloginexample.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TodoService {

    private final TodoRepository todoRepository;
    private final UserRepository userRepository;

    @Transactional
    public TodoResponseDto createTodo(TodoRequestDto requestDto, Principal principal) {
        User user = getUserFromPrincipal(principal);

        Todo todo = Todo.builder()
                .title(requestDto.getTitle())
                .description(requestDto.getDescription())
                .completed(requestDto.getCompleted() != null ? requestDto.getCompleted() : false)
                .priority(requestDto.getPriority() != null ? requestDto.getPriority() : Priority.MEDIUM)
                .dueDate(requestDto.getDueDate())
                .user(user)
                .build();

        Todo savedTodo = todoRepository.save(todo);
        return TodoResponseDto.from(savedTodo);
    }

    public List<TodoResponseDto> getAllTodos(Principal principal) {
        User user = getUserFromPrincipal(principal);

        if (user.getRole() == Role.ADMIN) {
            return todoRepository.findAll().stream()
                    .map(TodoResponseDto::from)
                    .collect(Collectors.toList());
        }

        return todoRepository.findByUser(user).stream()
                .map(TodoResponseDto::from)
                .collect(Collectors.toList());
    }

    public TodoResponseDto getTodo(Long id, Principal principal) {
        User user = getUserFromPrincipal(principal);
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("할일을 찾을 수 없습니다."));

        if (user.getRole() != Role.ADMIN && !todo.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("접근 권한이 없습니다.");
        }

        return TodoResponseDto.from(todo);
    }

    @Transactional
    public TodoResponseDto updateTodo(Long id, TodoRequestDto requestDto, Principal principal) {
        User user = getUserFromPrincipal(principal);
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("할일을 찾을 수 없습니다."));

        if (user.getRole() != Role.ADMIN && !todo.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("접근 권한이 없습니다.");
        }

        todo.update(requestDto.getTitle(), requestDto.getDescription(), requestDto.getCompleted(), requestDto.getPriority(), requestDto.getDueDate());
        return TodoResponseDto.from(todo);
    }

    @Transactional
    public void deleteTodo(Long id, Principal principal) {
        User user = getUserFromPrincipal(principal);
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("할일을 찾을 수 없습니다."));

        if (user.getRole() != Role.ADMIN && !todo.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("접근 권한이 없습니다.");
        }

        todoRepository.delete(todo);
    }

    private User getUserFromPrincipal(Principal principal) {
        Long userId = Long.parseLong(principal.getName());
        return userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("유저를 찾을 수 없습니다."));
    }
}

