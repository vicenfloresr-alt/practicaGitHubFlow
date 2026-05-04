package com.hospital.hospitalatencion.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hospital.hospitalatencion.model.Atencion;
import com.hospital.hospitalatencion.services.AtencionServices;

@RestController
@RequestMapping("api/v1/atencion")
public class AtencionController {

    @Autowired
    private AtencionServices atencionServices;


    @GetMapping
    public ResponseEntity<List<Atencion>> mostrar(){
        List<Atencion> atenciones = atencionServices.buscarTodos();
        if (atenciones.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(atenciones);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Atencion> buscarAtencion(@PathVariable Long id){
        try{
            Atencion unaAtencion = atencionServices.buscarAtencionPorId(id);
            return ResponseEntity.ok(unaAtencion);
        }
        catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<?> crearAtencion(@RequestBody Atencion unaAtencion){
        try{
            return ResponseEntity.ok(atencionServices.guardarAtencion(unaAtencion));        
        }
        catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        
    }

    @PutMapping("/{id}")
    public ResponseEntity<Atencion> modificarAtencion(@PathVariable Long id, @RequestBody Atencion unaAtencion){
        try{
            Atencion ate = atencionServices.buscarAtencionPorId(id);
            ate.setId(id);
            ate.setIdMedico(unaAtencion.getIdMedico());
            ate.setIdPaciente(unaAtencion.getIdPaciente());
            ate.setFechaHora(unaAtencion.getFechaHora());
            ate.setCosto(unaAtencion.getCosto());
            ate.setComentario(unaAtencion.getComentario());
            atencionServices.guardarAtencion(unaAtencion);
            return ResponseEntity.ok(ate);
        }catch (Exception e){   
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public String eliminarAtencion(@PathVariable Long id){
        try{
            atencionServices.eliminarAtencion(id);
            return "La atención ha sido eliminada :(";
        }
        catch (Exception e){
            return "La atencion no existe";
        }
    }




}
