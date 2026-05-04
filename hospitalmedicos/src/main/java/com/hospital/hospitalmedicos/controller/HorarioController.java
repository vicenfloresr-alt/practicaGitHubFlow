package com.hospital.hospitalmedicos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hospital.hospitalmedicos.model.Horario;
import com.hospital.hospitalmedicos.services.HorarioServices;

@RestController
@RequestMapping("/api/v1/horarios")
public class HorarioController {

    @Autowired
    private HorarioServices horarioService;

    @PostMapping
    public Horario crear(@RequestBody Horario horario) {
        return horarioService.guardar(horario);
    }

    @GetMapping
    public List<Horario> listar() {
        return horarioService.listar();
    }

}
