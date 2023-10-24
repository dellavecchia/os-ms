package com.dellavecchia.osms.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Priority {
    
    LOW(0, "LOW"),
    MEDIUM(1, "MEDIUM"),
    HIGH(2,"HIGH");
    
    private Integer cod;
    private String description;
    public static Priority toEnum(Integer cod){
        if (cod == null){
            return null;
        }
        for (Priority x : Priority.values()){
            if (cod.equals(x.getCod())){
                return x;
            }
        }

        throw new IllegalArgumentException("Invalid priority!" + cod);
    }
}

