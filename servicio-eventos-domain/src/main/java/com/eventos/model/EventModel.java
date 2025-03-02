package com.eventos.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EventModel {

    private Integer id;

    private String name;

    private String description;

    private LocalDate date;

    private LocalTime time;

    private String location;

    private String category;

    private Integer capacity;
}
