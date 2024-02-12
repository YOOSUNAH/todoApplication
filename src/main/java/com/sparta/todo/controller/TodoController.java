package com.sparta.todo.controller;

import com.sparta.todo.dto.todo.TodoRequestDto;
import com.sparta.todo.dto.todo.TodoListResponseDto;
import com.sparta.todo.dto.todo.TodoResponseDto;
import com.sparta.todo.service.TodoService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import java.util.concurrent.RejectedExecutionException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/todos")
public class TodoController {

    private final TodoService todoService;

    // 일정 추가
    @PostMapping("")
    @Operation(summary = "일정 추가", description = "제목, 내용을 입력해주세요")
    public ResponseEntity<TodoResponseDto> saveTodo(HttpServletRequest request, @RequestBody TodoRequestDto todoRequestDto) {
        TodoResponseDto todoResponseDto = todoService.saveTodo(request, todoRequestDto);
        return ResponseEntity.status(201).body(todoResponseDto);
    }

    // 일정 목록 조회
    @GetMapping("")
    @Operation(summary = "일정 목록 조회", description = "전체 일정 목록을 조회해줍니다")
    public ResponseEntity<List<TodoListResponseDto>> getTodos() {
        List<TodoListResponseDto> responseDtoList = todoService.getTodos();
        return ResponseEntity.status(201).body(responseDtoList);
    }

    // 선택 일정 조회
    @GetMapping("/{todoId}")
    @Operation(summary = "선택 일정 조회",  description = "조회하고자 하는 일정의 아이디를 입력해주세요")
    public ResponseEntity<TodoResponseDto> getTodoById(@PathVariable Long todoId) {
        try {
            TodoResponseDto todoResponseDto = todoService.getTodoById(todoId);
            return ResponseEntity.status(201).body(todoResponseDto);
        } catch (RejectedExecutionException | IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new TodoResponseDto(e.getMessage(), HttpStatus.BAD_REQUEST.value()));
        }

    }

    // 선택 일정 수정
    @PutMapping("/{todoId}")
    @Operation(summary = "선택 일정 수정", description = "수정하고자 하는 일정의 아이디를 입력해주세요")
    public ResponseEntity<TodoResponseDto> updateTodo(HttpServletRequest request, @PathVariable Long todoId, @RequestBody TodoRequestDto todoRequestDto, Boolean isCompleted) {
        try {
            TodoResponseDto todoResponseDto = todoService.updateTodo(request, todoId, todoRequestDto, isCompleted);
            return ResponseEntity.ok().body(todoResponseDto);
        } catch (RejectedExecutionException | IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new TodoResponseDto(e.getMessage(), HttpStatus.BAD_REQUEST.value()));
        }
    }

    // 선택 일정 삭제
    @DeleteMapping("/{todoId}")
    @Operation(summary = "선택 일정 삭제", description = "삭제하고자 하는 일정의 아이디를 입력해주세요")
    public ResponseEntity<Long> deleteTodo(@PathVariable Long todoId) {
        Long response =  todoService.deleteTodo(todoId);
        return ResponseEntity.ok().body(response);
    }
}