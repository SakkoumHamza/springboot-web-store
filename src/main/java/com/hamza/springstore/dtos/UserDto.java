package com.hamza.springstore.dtos;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class UserDto {
    private Long id;
    private String name;

    private String email;
    @JsonFormat(pattern = "yyyy-MM-dd  HH:mm")
    private LocalDateTime createdAt;
}
