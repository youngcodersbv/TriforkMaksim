package com.example.footballleagues.exceptions;

import lombok.Data;

import java.time.LocalTime;

@Data
public class ExceptionResponse {
    private LocalTime timestamp;
    private String message;
    private String detail;

    public ExceptionResponse(LocalTime timestamp, String message, String detail) {
        this.timestamp = timestamp;
        this.message = message;
        this.detail = detail;
    }
}
