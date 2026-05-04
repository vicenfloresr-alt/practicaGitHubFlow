package com.hospital.hospitalmedicos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hospital.hospitalmedicos.model.Horario;

@Repository
public interface HorarioRepository extends JpaRepository<Horario, Long>{

}
