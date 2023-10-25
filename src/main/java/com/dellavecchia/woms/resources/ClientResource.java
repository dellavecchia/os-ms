package com.dellavecchia.woms.resources;

import com.dellavecchia.woms.domain.Client;
import com.dellavecchia.woms.dtos.ClientDTO;
import com.dellavecchia.woms.services.ClientService;
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
@RequestMapping(value = "/clients")
public class ClientResource {

    @Autowired
    private ClientService service;
    @GetMapping(value = "/{id}")
    public ResponseEntity<ClientDTO> findById(@PathVariable Integer id){
        Client obj = service.findById(id);
        ClientDTO objDTO = new ClientDTO(obj);
        return ResponseEntity.ok().body(objDTO);

    }
    @GetMapping
    public ResponseEntity<List<ClientDTO>> findAll(){
        List<ClientDTO> listDTO = service.findAll().stream().map(obj -> new ClientDTO(obj))
                .collect(Collectors.toList());


//        List<Client> list = service.findAll();
//        List<ClientDTO> listDTO = new ArrayList<>();
//        for (Client obj: list) {
//            listDTO.add(new ClientDTO(obj));
//        }
//        list.forEach(obj -> listDTO.add(new ClientDTO(obj)));

        return ResponseEntity.ok().body(listDTO);
    }
    @PostMapping
    public ResponseEntity<ClientDTO> create(@Valid @RequestBody ClientDTO objDTO){
        Client newObj = service.create(objDTO);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(newObj.getId()).toUri();
        return ResponseEntity.created(uri).build();

    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ClientDTO> update(@PathVariable Integer id, @Valid @RequestBody ClientDTO objDTO){
        ClientDTO newObj = new ClientDTO(service.update(id, objDTO));

        return ResponseEntity.ok().body(newObj);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
