package com.lk.domain;

import lombok.Data;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

@Data
public class User {
    private String id;
    private String password;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
