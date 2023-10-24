package com.dellavecchia.osms.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor

public enum Status {

    OPEN(0, "OPEN"),
    ONGOING(1, "ONGOING"),
    CLOSED(2,"CLOSED");

    private final Integer cod;
    private final String description;
    public static Status toEnum(Integer cod){
        if (cod == null){
            return null;
        }
        for (Status x : Status.values()){
            if (cod.equals(x.getCod())){
                return x;
            }
        }

        throw new IllegalArgumentException("Invalid status!" + cod);
    }
}
