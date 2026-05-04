package com.hospital.hospitalmedicos.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="medico")
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 

    @Column(nullable = false)
    private String nombreCompleto;

    @Column(unique=true, nullable = false)
    private String run_medico;

    @Column(nullable = true)
    private String especialidad;

    private Integer jefeTurno; 

    @OneToMany(mappedBy = "medico", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Horario> horarios;


}
