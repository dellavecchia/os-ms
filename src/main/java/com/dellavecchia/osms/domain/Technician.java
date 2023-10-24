package com.dellavecchia.osms.domain;

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
    private List<OS> list = new ArrayList<>();
    public Technician() {
        super();
    }

    public Technician(Integer id, String name, String cpf, String phone) {
        super(id, name, cpf, phone);
    }

    public List<OS> getList() {
        return list;
    }

    public void setList(List<OS> list) {
        this.list = list;
    }
}
