package com.hospital.hospitalatencion.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospital.hospitalatencion.client.ExternalServiceClient;
import com.hospital.hospitalatencion.dto.MedicoDto;
import com.hospital.hospitalatencion.dto.PacienteDto;
import com.hospital.hospitalatencion.model.Atencion;
import com.hospital.hospitalatencion.repository.AtencionRepository;

@Service
public class AtencionServices {

    @Autowired
    private AtencionRepository atencionRepository;

    private final ExternalServiceClient cliente;

    public AtencionServices(ExternalServiceClient cliente) {
            this.cliente = cliente;
    }

    public List<Atencion> buscarTodos(){
        return atencionRepository.findAll();
    }

    public Atencion buscarAtencionPorId(Long id){
        return atencionRepository.findById(id).get();
    }

    public Atencion guardarAtencion(Atencion unaAtencion){
        PacienteDto paciente = cliente.obtenerPaciente(unaAtencion.getIdPaciente());

        if (paciente == null){
            throw new RuntimeException("Paciente no existe :(");
        }

        MedicoDto medico = cliente.obtenerMedico(unaAtencion.getIdMedico());
        if (medico == null){
            throw new RuntimeException("Medico no existe :(");
        }

        return atencionRepository.save(unaAtencion);
    }

    public void eliminarAtencion(Long id){
        atencionRepository.deleteById(id);
    }

}
