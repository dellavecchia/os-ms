package com.dellavecchia.osms.resources;

import com.dellavecchia.osms.dtos.OSDTO;
import com.dellavecchia.osms.services.OsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
@CrossOrigin("*")
@RestController
@RequestMapping(value =  "/os")
public class OSResource {


    @Autowired
    private OsService service;
    @GetMapping(value = "/{id}")
    public ResponseEntity<OSDTO> findById(@PathVariable Integer id) {
        OSDTO objDTO = new OSDTO(service.findById(id));

        return ResponseEntity.ok().body(objDTO);

    }

    @GetMapping
    public ResponseEntity<List<OSDTO>> findAll(){
        List<OSDTO> listDTO = service.findAll().stream().map(OSDTO::new).toList();
        return ResponseEntity.ok().body(listDTO);
    }

    @PostMapping
    public ResponseEntity<OSDTO> create(@Valid @RequestBody OSDTO obj){
        obj = new OSDTO(service.create(obj));
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<OSDTO> update(@PathVariable Integer id, @Valid @RequestBody OSDTO objDTO){
        OSDTO newDTO = new OSDTO(service.update(id, objDTO));
        return ResponseEntity.ok().body(newDTO);
    }
}
