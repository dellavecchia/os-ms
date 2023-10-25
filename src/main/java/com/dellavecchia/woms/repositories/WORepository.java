package com.dellavecchia.woms.repositories;


import com.dellavecchia.woms.domain.WO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WORepository extends JpaRepository<WO, Integer> {
}
