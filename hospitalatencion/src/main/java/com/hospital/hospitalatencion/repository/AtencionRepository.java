package com.hospital.hospitalatencion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hospital.hospitalatencion.model.Atencion;

@Repository
public interface AtencionRepository  extends JpaRepository<Atencion, Long>{

}
