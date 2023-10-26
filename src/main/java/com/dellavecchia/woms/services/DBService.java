package com.dellavecchia.woms.services;

import com.dellavecchia.woms.domain.Client;
import com.dellavecchia.woms.domain.WO;
import com.dellavecchia.woms.domain.Technician;
import com.dellavecchia.woms.domain.enums.Priority;
import com.dellavecchia.woms.domain.enums.Status;
import com.dellavecchia.woms.repositories.ClientRepository;
import com.dellavecchia.woms.repositories.WORepository;
import com.dellavecchia.woms.repositories.TechnicianRepository;
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
    private WORepository woRepository;

    public void dbInstance(){

        Technician t1 = new Technician(null, "Gabriel Guimarães", "100.531.916-25", "+5535991481551");
        Technician t2 = new Technician(null, "Marcio Mascarenas", "412.205.966-63", "+5519986545454");
        Technician t3 = new Technician(null, "Maria da Silva", "920.981.390-12", "+5599987654321");
        Technician t4 = new Technician(null, "João Santos", "874.525.550-40", "+5599777755555");
        Technician t5 = new Technician(null, "Carlos Oliveira", "826.977.390-50", "+5599555544444");
        Technician t6 = new Technician(null, "Luiza Almeida", "946.143.940-78", "+5599444433333");
        Client c1 = new Client(null, "Maria da Silva de Oliveira", "012.524.980-20", "(35) 99120-3731");
        Client c2 = new Client(null, "Pedro Nunes", "951.099.610-69", "(35) 99120-3731");
        Client c3 = new Client(null, "Luiz Almeida", "829.484.440-39", "(35) 99120-3731");
        WO wo1 = new WO(null, "change mother board", Priority.HIGH, Status.ONGOING, t1, c1);
        WO wo2 = new WO(null, "Fix wather cooler", Priority.MEDIUM, Status.CLOSED, t2, c2);
        WO wo3 = new WO(null, "Format system", Priority.LOW, Status.OPEN, t3, c2);
        WO wo4 = new WO(null, "Increase memory", Priority.HIGH, Status.ONGOING, t3, c2);


        t1.getList().add(wo1);
        t2.getList().add(wo2);
        t3.getList().add(wo3);
        c1.getList().add(wo1);
        c2.getList().add(wo2);
        c2.getList().add(wo3);
        c2.getList().add(wo4);

        technicianRepository.saveAll(Arrays.asList(t1, t2, t3, t4, t5, t6));
        clientRepository.saveAll(Arrays.asList(c1, c2, c3));
        woRepository.saveAll(Arrays.asList(wo1, wo2, wo3, wo4));
    }
}
