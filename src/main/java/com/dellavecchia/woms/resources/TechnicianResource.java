package com.dellavecchia.woms.resources;

import com.dellavecchia.woms.domain.Technician;
import com.dellavecchia.woms.dtos.TechnicianDTO;
import com.dellavecchia.woms.services.TechnicianService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;
@CrossOrigin("*")
@RestController
@RequestMapping(value = "/technicians")
public class TechnicianResource {

    @Autowired
    private TechnicianService service;
    @GetMapping(value = "/{id}")
    public ResponseEntity<TechnicianDTO> findById(@PathVariable Integer id){
        Technician obj = service.findById(id);
        TechnicianDTO objDTO = new TechnicianDTO(obj);
        return ResponseEntity.ok().body(objDTO);

    }
    @GetMapping
    public ResponseEntity<List<TechnicianDTO>> findAll(){
        List<TechnicianDTO> listDTO = service.findAll().stream().map(obj -> new TechnicianDTO(obj))
                .collect(Collectors.toList());


//        List<Technician> list = service.findAll();
//        List<TechnicianDTO> listDTO = new ArrayList<>();
//        for (Technician obj: list) {
//            listDTO.add(new TechnicianDTO(obj));
//        }
//        list.forEach(obj -> listDTO.add(new TechnicianDTO(obj)));

        return ResponseEntity.ok().body(listDTO);
    }
    @PostMapping
    public ResponseEntity<TechnicianDTO> create(@Valid @RequestBody TechnicianDTO objDTO){
        Technician newObj = service.create(objDTO);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(newObj.getId()).toUri();
        return ResponseEntity.created(uri).build();

    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<TechnicianDTO> update(@PathVariable Integer id, @Valid @RequestBody TechnicianDTO objDTO){
        TechnicianDTO newObj = new TechnicianDTO(service.update(id, objDTO));

        return ResponseEntity.ok().body(newObj);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
