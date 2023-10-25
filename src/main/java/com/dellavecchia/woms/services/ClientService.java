package com.dellavecchia.woms.services;

import com.dellavecchia.woms.domain.Person;
import com.dellavecchia.woms.domain.Client;
import com.dellavecchia.woms.dtos.ClientDTO;
import com.dellavecchia.woms.repositories.ClientRepository;
import com.dellavecchia.woms.repositories.PersonRepository;
import com.dellavecchia.woms.services.exceptions.DataIntegrityViolationException;
import com.dellavecchia.woms.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private PersonRepository personRepository;
    public Client findById(Integer id){
        Optional<Client> obj = clientRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Object not found! Id: " + id + ", type: " + Client.class.getName())
        );

    }

    public List<Client> findAll() {

        return clientRepository.findAll();
    }


    public Client create(ClientDTO objDTO){
        if (findByCPF(objDTO) != null){
            throw new DataIntegrityViolationException("This CPF already exists in the database!");
        }
        return clientRepository.save(new Client(null,
                objDTO.getName(), objDTO.getCpf(), objDTO.getPhone()));
    }


    public Client update(Integer id, ClientDTO objDTO) {
        Client oldObj = findById(id);
        if (findByCPF(objDTO) != null && findByCPF(objDTO).getId() != id){
            throw new DataIntegrityViolationException("This CPF already exists in the database!");
        }
        oldObj.setName(objDTO.getName());
        oldObj.setCpf(objDTO.getCpf());
        oldObj.setPhone(objDTO.getPhone());

        return clientRepository.save(oldObj);


    }
    public void delete(Integer id) {
       Client obj = findById(id);
       if (obj.getList().size() > 0) {
           throw new DataIntegrityViolationException("Existing service orders are bind with this Client, cannot be deleted");
       }
        clientRepository.deleteById(id);


    }

    private Person findByCPF(ClientDTO objDTO) {
        Person obj = personRepository.findByCPF(objDTO.getCpf());
        if (obj != null) {
            return obj;
        }
        return null;
    }


}
