package com.dellavecchia.woms.resources;

import com.dellavecchia.woms.dtos.WOMS;
import com.dellavecchia.woms.services.WOService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
@CrossOrigin("*")
@RestController
@RequestMapping(value =  "/wo")
public class WOResource {


    @Autowired
    private WOService service;
    @GetMapping(value = "/{id}")
    public ResponseEntity<WOMS> findById(@PathVariable Integer id) {
        WOMS objDTO = new WOMS(service.findById(id));

        return ResponseEntity.ok().body(objDTO);

    }

    @GetMapping
    public ResponseEntity<List<WOMS>> findAll(){
        List<WOMS> listDTO = service.findAll().stream().map(WOMS::new).toList();
        return ResponseEntity.ok().body(listDTO);
    }

    @PostMapping
    public ResponseEntity<WOMS> create(@Valid @RequestBody WOMS obj){
        obj = new WOMS(service.create(obj));
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<WOMS> update(@PathVariable Integer id, @Valid @RequestBody WOMS objDTO){
        WOMS newDTO = new WOMS(service.update(id, objDTO));
        return ResponseEntity.ok().body(newDTO);
    }
}
