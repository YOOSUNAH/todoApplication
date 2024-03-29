package com.sparta.todo.service;

import com.sparta.todo.dto.todo.TodoRequestDto;
import com.sparta.todo.dto.todo.TodoResponseDto;
import com.sparta.todo.dto.user.UserDto;
import com.sparta.todo.entity.Todo;
import com.sparta.todo.entity.User;
import com.sparta.todo.repository.TodoRepository;
import java.util.concurrent.RejectedExecutionException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;


    @Transactional
    public TodoResponseDto saveTodo(TodoRequestDto todoRequestDto, User user) {
        Todo todo = new Todo(todoRequestDto);
        todo.setUser(user);

        var saved = todoRepository.save(todo);
        return new TodoResponseDto(saved);
    }


    public TodoResponseDto getTodoById(Long todoId) {
        Todo todo = getTodo(todoId);
        return new TodoResponseDto(todo);
    }

    public Map<UserDto, List<TodoResponseDto>> getUserTodoMap() {
        Map<UserDto, List<TodoResponseDto>> userTodoMap = new HashMap<>();
        List<Todo> todoList = todoRepository.findAll(Sort.by(Direction.DESC, "createDate"));

        todoList.forEach(todo -> {
            var userDto = new UserDto(todo.getUser());
            var todoDto = new TodoResponseDto(todo);
            if (userTodoMap.containsKey(userDto)) {
                // 유저 할일 목록에 항목을 추가
                userTodoMap.get(userDto).add(todoDto);
            } else {
                // 유저 할일 목록을 새로 추가
                userTodoMap.put(userDto, new ArrayList<>(List.of(todoDto)));
            }
        });
        return userTodoMap;
    }

    @Transactional
    public TodoResponseDto updateTodo(Long todoId, TodoRequestDto todoRequestDto, User user) {
        Todo todo = getUserTodo(todoId, user);
        // 변경
        todo.setTitle(todoRequestDto.getTitle());
        todo.setContent(todoRequestDto.getContent());
        return new TodoResponseDto(todo);
    }

    @Transactional
    public TodoResponseDto completeTodo(Long todoId, User user) {
        Todo todo = getUserTodo(todoId, user);
        todo.complete();
        return new TodoResponseDto(todo);
    }
    @Transactional
    public void deleteTodo(Long todoId) {
        Todo todo = getTodo(todoId);
        todoRepository.delete(todo);
    }

    public Todo getTodo(Long todoId) {
        return todoRepository.findById(todoId)
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 할일 ID 입니다."));
    }
    public Todo getUserTodo(Long todoId, User user) {
        Todo todo = getTodo(todoId);
        if(!user.getUserId().equals(todo.getUser().getUserId())) {
            throw new RejectedExecutionException("작성자만 수정할 수 있습니다.");
        }
        return todo;
    }


    Todo createTodoFromRequest(TodoRequestDto todoRequestDto, User user) {
        Todo todo = new Todo();
        todo.setTitle(todoRequestDto.getTitle());
        todo.setContent(todoRequestDto.getContent());
        todo.setUser(user);
        return todo;
    }
}
