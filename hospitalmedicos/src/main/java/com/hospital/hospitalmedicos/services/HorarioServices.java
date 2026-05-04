package com.hospital.hospitalmedicos.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospital.hospitalmedicos.model.Horario;
import com.hospital.hospitalmedicos.repositories.HorarioRepository;

@Service
public class HorarioServices {

    @Autowired
    private HorarioRepository horarioRepository;

     public Horario guardar(Horario horario) {
        return horarioRepository.save(horario);
    }

    public List<Horario> listar() {
        return horarioRepository.findAll();
    }

}
