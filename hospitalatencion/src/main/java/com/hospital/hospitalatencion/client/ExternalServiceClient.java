package com.hospital.hospitalatencion.client;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.hospital.hospitalatencion.dto.MedicoDto;
import com.hospital.hospitalatencion.dto.PacienteDto;

@Service
public class ExternalServiceClient {

    private final WebClient webClient;

    public ExternalServiceClient(WebClient webClient) {
        this.webClient=webClient;
    }

    public PacienteDto obtenerPaciente(Long idPaciente){
        try{
            return webClient.get()
                .uri("http://localhost:8082/api/v1/pacientes/" +idPaciente)
                .retrieve()
                .bodyToMono(PacienteDto.class)
                .block();
        }
        catch(Exception e){
            return null;
        }
    }

     public MedicoDto obtenerMedico(Long idMedico){
        try{
            return webClient.get()
                .uri("http://localhost:8083/api/v1/medicos/" +idMedico)
                .retrieve()
                .bodyToMono(MedicoDto.class)
                .block();
        }
        catch(Exception e){
            return null;
        }
    }






}
