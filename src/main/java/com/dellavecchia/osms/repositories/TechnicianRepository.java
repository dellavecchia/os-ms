package com.dellavecchia.osms.repositories;

import com.dellavecchia.osms.domain.Technician;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface TechnicianRepository extends JpaRepository<Technician, Integer> {


}
