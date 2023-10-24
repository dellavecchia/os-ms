package com.dellavecchia.osms.services;

import com.dellavecchia.osms.domain.OS;
import com.dellavecchia.osms.domain.enums.Priority;
import com.dellavecchia.osms.domain.enums.Status;
import com.dellavecchia.osms.dtos.OSDTO;
import com.dellavecchia.osms.repositories.OSRepository;
import com.dellavecchia.osms.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OsService {

    @Autowired
    private OSRepository repository;
    @Autowired
    private TechnicianService technicianService;
    @Autowired
    private ClientService clientService;

    public OS findById(Integer id) {
        Optional<OS> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Object not found! Id: " + id + " , Type: " + OS.class.getName()));
    }


    public List<OS> findAll() {
        return repository.findAll();

    }

    public OS create(OSDTO obj) {
        OS newObj = new OS();
        return fromDTO(obj, newObj);
    }

//    private OS fromDTO(OSDTO obj){
//        OS newObj = new OS();
//        return getOs(obj, newObj);
//

//    }

    private OS fromDTO(OSDTO obj, OS newObj) {
        newObj.setComments(obj.getComments());
        newObj.setPriority(Priority.toEnum(obj.getPriority()));
        newObj.setStatus(Status.toEnum(obj.getStatus()));
        newObj.setTechnician(technicianService.findById(obj.getTechnician()));
        newObj.setClient(clientService.findById(obj.getClient()));

        if (newObj.getStatus().getCod().equals(2)) {
            newObj.setClosingDate(LocalDateTime.now());
        }
        return repository.save(newObj);
    }

    public OS update(Integer id, OSDTO objDTO) {
        OS oldOBJ = findById(id);
        return fromDTO(objDTO, oldOBJ);
    }
}
