package com.dellavecchia.osms.services;

import com.dellavecchia.osms.domain.Person;
import com.dellavecchia.osms.domain.Technician;
import com.dellavecchia.osms.dtos.TechnicianDTO;
import com.dellavecchia.osms.repositories.PersonRepository;
import com.dellavecchia.osms.repositories.TechnicianRepository;
import com.dellavecchia.osms.services.exceptions.DataIntegrityViolationException;
import com.dellavecchia.osms.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TechnicianService {

    @Autowired
    private TechnicianRepository technicianRepository;
    @Autowired
    private PersonRepository personRepository;
    public Technician findById(Integer id){
        Optional<Technician> obj = technicianRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Object not found! Id: " + id + ", type: " + Technician.class.getName())
        );

    }

    public List<Technician> findAll() {
        return technicianRepository.findAll();
    }


    public Technician create(TechnicianDTO objDTO){
        if (findByCPF(objDTO) != null){
            throw new DataIntegrityViolationException("This CPF already exists in the database!");
        }
        return technicianRepository.save(new Technician(null,
                objDTO.getName(), objDTO.getCpf(), objDTO.getPhone()));
    }


    public Technician update(Integer id, TechnicianDTO objDTO) {
        Technician oldObj = findById(id);
        if (findByCPF(objDTO) != null && findByCPF(objDTO).getId() != id){
            throw new DataIntegrityViolationException("This CPF already exists in the database!");
        }
        oldObj.setName(objDTO.getName());
        oldObj.setCpf(objDTO.getCpf());
        oldObj.setPhone(objDTO.getPhone());

        return technicianRepository.save(oldObj);


    }
    public void delete(Integer id) {
       Technician obj = findById(id);
       if (obj.getList().size() > 0) {
           throw new DataIntegrityViolationException("Existing service orders are bind with this technician, cannot be deleted");
       }
        technicianRepository.deleteById(id);


    }

    private Person findByCPF(TechnicianDTO objDTO) {
        Person obj = personRepository.findByCPF(objDTO.getCpf());
        if (obj != null) {
            return obj;
        }
        return null;
    }


}
