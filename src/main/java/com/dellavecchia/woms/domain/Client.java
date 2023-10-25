package com.dellavecchia.woms.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Client extends Person {

    @OneToMany(mappedBy = "technician")
    private List<WO> list = new ArrayList<>();
    public Client() {
        super();
    }
    public Client(Integer id, String name, String cpf, String phone) {
        super(id, name, cpf, phone);
    }



    public List<WO> getList() {
        return list;
    }

    public void setList(List<WO> list) {
        this.list = list;
    }
}
