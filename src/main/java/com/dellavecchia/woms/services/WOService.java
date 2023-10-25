package com.dellavecchia.woms.services;

import com.dellavecchia.woms.domain.WO;
import com.dellavecchia.woms.domain.enums.Priority;
import com.dellavecchia.woms.domain.enums.Status;
import com.dellavecchia.woms.dtos.WOMS;
import com.dellavecchia.woms.repositories.WORepository;
import com.dellavecchia.woms.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class WOService {

    @Autowired
    private WORepository repository;
    @Autowired
    private TechnicianService technicianService;
    @Autowired
    private ClientService clientService;

    public WO findById(Integer id) {
        Optional<WO> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Object not found! Id: " + id + " , Type: " + WO.class.getName()));
    }


    public List<WO> findAll() {
        return repository.findAll();

    }

    public WO create(WOMS obj) {
        WO newObj = new WO();
        return fromDTO(obj, newObj);
    }

//    private WO fromDTO(WOMS obj){
//        WO newObj = new WO();
//        return getOs(obj, newObj);
//

//    }

    private WO fromDTO(WOMS obj, WO newObj) {
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

    public WO update(Integer id, WOMS objDTO) {
        WO oldOBJ = findById(id);
        return fromDTO(objDTO, oldOBJ);
    }
}
