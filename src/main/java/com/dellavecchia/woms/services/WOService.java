package com.dellavecchia.woms.services;

import com.dellavecchia.woms.domain.WO;
import com.dellavecchia.woms.domain.enums.Priority;
import com.dellavecchia.woms.domain.enums.Status;
import com.dellavecchia.woms.dtos.WOMS;
import com.dellavecchia.woms.repositories.WORepository;
import com.dellavecchia.woms.services.exceptions.ObjectNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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

    /*
     * FindById
     */
    public WO findById(Integer id) {
        Optional<WO> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Object not found! Id: " + id + " , Type: " + WO.class.getName()));
    }

    /*
     * FindAll
     */

    public List<WO> findAll() {
        return repository.findAll(Sort.by(Sort.Direction.ASC, "id"));

    }

    /*
     * Create
     */
    public WO create(@Valid WOMS obj) {
        WO newObj = new WO();
        return updateData(obj, newObj);
    }

    /*
     * update
     */
    public WO update(@Valid Integer id, WOMS objDTO) {
        WO oldOBJ = findById(id);
        WO newObj = updateData(objDTO, oldOBJ);
        return repository.save(newObj);
    }

    // Update data from the Work Order
    private WO updateData(@Valid WOMS obj, WO oldObj) {
        oldObj.setComments(obj.getComments());
        oldObj.setPriority(Priority.toEnum(obj.getPriority().getCod()));
        oldObj.setStatus(Status.toEnum(obj.getStatus().getCod()));
        oldObj.setTechnician(technicianService.findById(obj.getTechnician()));
        oldObj.setClient(clientService.findById(obj.getClient()));

        //Automatic set date when work order is closed
        if (oldObj.getStatus().equals(Status.CLOSED)) {
            oldObj.setClosingDate(LocalDateTime.now());

            //Remove closing date if a reopen my work order
        } else if (oldObj.getStatus().equals(Status.OPEN)) {
            oldObj.setClosingDate(null);
        }
        return repository.save(oldObj);


    }

}