package com.hospital.hospitalmedicos.controller;

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

import com.hospital.hospitalmedicos.model.Medico;
import com.hospital.hospitalmedicos.services.MedicoServices;

import java.util.List;

@RestController
@RequestMapping("api/v1/medicos")
public class MedicoController {

    @Autowired
    private MedicoServices medicoServices;

    @GetMapping
    public ResponseEntity<List<Medico>> mostrarMedicos(){
       List<Medico> medicos = medicoServices.buscarTodos();
        if (medicos.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(medicos);
    } 

    @PostMapping
    public ResponseEntity<Medico> guardarMedico(@RequestBody Medico unMedico){
        Medico medicoNuevo = medicoServices.crearMedico(unMedico);
        return ResponseEntity.status(HttpStatus.CREATED).body(medicoNuevo);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Medico> buscarMedico(@PathVariable Long id){
        try{
            Medico unMedico = medicoServices.buscarPorId(id);
            return ResponseEntity.ok(unMedico);
        }
        catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Medico> modificarMedico(@PathVariable Long id, @RequestBody Medico unMedico){
        try{
            Medico med = medicoServices.buscarPorId(id);
            med.setId(id);
            med.setRun_medico(unMedico.getRun_medico());
            med.setNombreCompleto(unMedico.getNombreCompleto());
            med.setEspecialidad(unMedico.getEspecialidad());
            med.setJefeTurno(unMedico.getJefeTurno());
            medicoServices.crearMedico(unMedico);
            return ResponseEntity.ok(med);
        }catch (Exception e){   
            return ResponseEntity.notFound().build();
        }
    }

    
    @DeleteMapping("/{id}")
    public String eliminarMedico(@PathVariable Long id){
        try{
            medicoServices.eliminarMedico(id);
            return "El paciente ha sido eliminado :(";
        }
        catch (Exception e){
            return "El paciente no existe";
        }
    }






}
