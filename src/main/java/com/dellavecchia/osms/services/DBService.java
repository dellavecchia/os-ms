package com.dellavecchia.osms.services;

import com.dellavecchia.osms.domain.Client;
import com.dellavecchia.osms.domain.OS;
import com.dellavecchia.osms.domain.Technician;
import com.dellavecchia.osms.domain.enums.Priority;
import com.dellavecchia.osms.domain.enums.Status;
import com.dellavecchia.osms.repositories.ClientRepository;
import com.dellavecchia.osms.repositories.OSRepository;
import com.dellavecchia.osms.repositories.TechnicianRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class DBService {
    @Autowired
    private TechnicianRepository technicianRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private OSRepository osRepository;

    public void dbInstance(){

        Technician t1 = new Technician(null, "Gabriel Guimarães", "100.531.916-25", "+5535991481551");
        Technician t2 = new Technician(null, "Marcio Mascarenas", "412.205.966-63", "+5519986545454");
        Technician t3 = new Technician(null, "Maria da Silva", "920.981.390-12", "+5599987654321");
        Technician t4 = new Technician(null, "João Santos", "874.525.550-40", "+5599777755555");
        Technician t5 = new Technician(null, "Carlos Oliveira", "826.977.390-50", "+5599555544444");
        Technician t6 = new Technician(null, "Luiza Almeida", "946.143.940-78", "+5599444433333");
        Client c1 = new Client(null, "Ivonir Della Vecchia Marques", "161.684.882-00", "(35) 99120-3731");
        OS os1 = new OS(null, "test create os", Priority.HIGH, Status.ONGOING, t1, c1);

        t1.getList().add(os1);
        c1.getList().add(os1);

        technicianRepository.saveAll(Arrays.asList(t1, t2, t3, t4, t5, t6));
        clientRepository.saveAll(Arrays.asList(c1));
        osRepository.saveAll(Arrays.asList(os1));
    }
}
