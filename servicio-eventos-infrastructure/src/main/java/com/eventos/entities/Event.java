package com.eventos.entities;

import com.eventos.validation.ValidLocalDateFormat;
import com.eventos.validation.ValidLocalTimeFormat;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "eventos")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Event {

    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @NotBlank
    @Size(max = 255)
    @Column(name = "nombre")
    private String name;

    @Column(name = "descripcion")
    private String description;

    @Column(name = "fecha")
    @ValidLocalDateFormat
    private LocalDate date;

    @Column(name = "hora")
    @ValidLocalTimeFormat
    private LocalTime time;

    @Column(name = "ubicacion")
    private String location;

    @Column(name = "categoria")
    private String category;

    @NotNull
    @Min(0)
    @Column(name = "capacidad")
    private Integer capacity;
}
