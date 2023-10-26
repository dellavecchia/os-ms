package com.dellavecchia.woms.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Technician extends Person implements Serializable {
    private static final long serialVersionUID = 1L;
    @JsonIgnore
    @OneToMany(mappedBy = "technician")
    private List<WO> list = new ArrayList<>();
    public Technician() {
        super();
    }

    public Technician(Integer id, String name, String cpf, String phone) {
        super(id, name, cpf, phone);
    }

    public List<WO> getList() {
        return list;
    }

    public void setList(List<WO> list) {

        this.list = list;
    }
}
