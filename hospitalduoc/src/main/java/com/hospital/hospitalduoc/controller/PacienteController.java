package com.hospital.hospitalduoc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hospital.hospitalduoc.model.Paciente;
import com.hospital.hospitalduoc.services.PacienteService;

@RestController
@RequestMapping("api/v1/pacientes")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;


    //devuelve en consulta rest la información de la tabla Paciente
    @GetMapping
    public ResponseEntity<List<Paciente>> listar(){
        List<Paciente> pacientes = pacienteService.mostrarPacientes();
        if (pacientes.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(pacientes);

    }


    @PostMapping
    public ResponseEntity<Paciente> guardarPaciente(@RequestBody Paciente unPaciente){
        Paciente pacienteNuevo = pacienteService.crearPaciente(unPaciente);
        return ResponseEntity.status(HttpStatus.CREATED).body(pacienteNuevo);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paciente> buscarPaciente(@PathVariable Long id){
        try{
            Paciente paciente = pacienteService.buscarId(id);
            return ResponseEntity.ok(paciente);
        }
        catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Paciente> modificar(@PathVariable Long id, @RequestBody Paciente unPaciente){
        try{
            Paciente pac = pacienteService.buscarId(id);
            pac.setId(id);
            pac.setRun(unPaciente.getRun());
            pac.setNombre(unPaciente.getNombre());
            pac.setApellido(unPaciente.getApellido());
            pac.setFechaNacimiento(unPaciente.getFechaNacimiento());
            pac.setCorreo(unPaciente.getCorreo());
            pacienteService.crearPaciente(pac);
            return ResponseEntity.ok(pac);
        }catch (Exception e){   
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public String eliminarPaciente(@PathVariable Long id){
        try{
            pacienteService.eliminarPaciente(id);
            return "El paciente ha sido eliminado :(";
        }
        catch (Exception e){
            return "El paciente no existe";
        }
    }

}
