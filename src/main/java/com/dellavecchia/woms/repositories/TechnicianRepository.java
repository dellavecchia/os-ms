package com.dellavecchia.woms.repositories;

import com.dellavecchia.woms.domain.Technician;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface TechnicianRepository extends JpaRepository<Technician, Integer> {


}
