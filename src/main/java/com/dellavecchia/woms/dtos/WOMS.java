package com.dellavecchia.woms.dtos;

import com.dellavecchia.woms.domain.WO;
import com.dellavecchia.woms.domain.enums.Priority;
import com.dellavecchia.woms.domain.enums.Status;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotEmpty;


import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

public class WOMS implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;


    private Integer id;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime openingDate;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime closingDate;

    @NotEmpty(message = "The field COMMENTS is required.")
    private String comments;
    private Integer priority;
    private Integer status;
    private Integer technician;
    private Integer client;

    public WOMS() {
        super();
    }

    public WOMS(WO obj) {
        this.id = obj.getId();
        this.openingDate = obj.getOpeningDate();
        this.closingDate = obj.getClosingDate();
        this.comments = obj.getComments();
        this.priority = obj.getPriority().getCod();
        this.status = obj.getStatus().getCod();
        this.technician = obj.getTechnician().getId();
        this.client = obj.getClient().getId();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getOpeningDate() {
        return openingDate;
    }

    public void setOpeningDate(LocalDateTime openingDate) {
        this.openingDate = openingDate;
    }

    public LocalDateTime getClosingDate() {
        return closingDate;
    }

    public void setClosingDate(LocalDateTime closingDate) {
        this.closingDate = closingDate;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Priority getPriority() {
        return Priority.toEnum(this.status);
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Status getStatus() {
        return Status.toEnum(this.status);
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getTechnician() {
        return technician;
    }

    public void setTechnician(Integer technician) {
        this.technician = technician;
    }

    public Integer getClient() {
        return client;
    }

    public void setClient(Integer client) {
        this.client = client;
    }
}
