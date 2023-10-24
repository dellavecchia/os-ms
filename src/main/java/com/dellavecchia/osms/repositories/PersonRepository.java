package com.dellavecchia.osms.repositories;

import com.dellavecchia.osms.domain.Person;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {

    @Query(value = "SELECT obj FROM Person obj WHERE obj.cpf =:cpf")
    Person findByCPF(@Param("cpf") String cpf);
}
