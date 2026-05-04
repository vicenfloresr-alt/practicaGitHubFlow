package com.hospital.hospitalmedicos.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospital.hospitalmedicos.model.Medico;
import com.hospital.hospitalmedicos.repositories.MedicoRepositories;

@Service
public class MedicoServices {

    @Autowired
    private MedicoRepositories medicoRepositories;

    public List<Medico> buscarTodos(){
        return medicoRepositories.findAll();            //similar a select * from tabla
    }

    public Medico buscarPorId(Long id){
        return medicoRepositories.findById(id).get();   //similar a select * from tabla where condicion

    }

    public Medico crearMedico(Medico unMedico){         //similar a insert into tabla (atributos)
        return medicoRepositories.save(unMedico);
    }

    public void eliminarMedico(Long id){
        medicoRepositories.deleteById(id);              //similar a delete from tabla
    }


}
