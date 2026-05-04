package com.hospital.hospitalmedicos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hospital.hospitalmedicos.model.Medico;

@Repository
public interface MedicoRepositories extends JpaRepository<Medico, Long>{

}
