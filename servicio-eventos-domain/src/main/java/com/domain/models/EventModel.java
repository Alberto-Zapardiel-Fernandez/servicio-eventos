package com.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * EventModel
 * @author Alberto Zapardiel Fernández
*/

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventModel {
    private Long id;
    private String name;
    private String description;
    private String date;
    private String time;
    private String location;
    private String category;
    private Integer capacity;
}
