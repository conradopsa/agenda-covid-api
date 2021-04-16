package io.conrado.api.starwars.models;

import lombok.Data;

@Data
public class Patient {    
    private Integer idPatient;
    private String name;
    private String profilePhoto;
    private String age;
    private String gender;
    private String profession;
}