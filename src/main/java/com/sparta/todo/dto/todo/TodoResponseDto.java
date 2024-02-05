package com.sparta.todo.dto.todo;


import com.sparta.todo.entity.Todo;
import lombok.Getter;

import java.time.LocalDateTime;


@Getter
public class TodoResponseDto {
    private Long todoId;
    private String title;
    private String contents;
    private String username;
    private LocalDateTime createdAt;

    public TodoResponseDto(Todo todo, String username){
        this.todoId = todo.getTodoId();
        this.title = todo.getTitle();
        this.contents = todo.getContents();
        this.username = username;
        this.createdAt = todo.getCreatedAt();
    }


}
