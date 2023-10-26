package com.dellavecchia.woms.dtos;

import com.dellavecchia.woms.domain.Client;
import com.dellavecchia.woms.domain.WO;
import jakarta.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.br.CPF;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ClientDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private Integer id;

    @NotEmpty(message = "The NAME field is required")
    private String name;

    @CPF
    @NotEmpty(message = "The CPF field is required")
    private String cpf;
    @NotEmpty(message = "The PHONE field is required")
    private String phone;

    private List<WO> list = new ArrayList<>();
    public ClientDTO() {
        super();
    }

    public ClientDTO(Client obj) {
        this.id = obj.getId();
        this.name = obj.getName();
        this.cpf = obj.getCpf();
        this.phone = obj.getPhone();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<WO> getList() {
        return list;
    }

    public void setList(List<WO> list) {
        this.list = list;
    }
}
