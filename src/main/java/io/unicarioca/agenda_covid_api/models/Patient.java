package io.unicarioca.agenda_covid_api.models;

import lombok.Data;

@Data
public class Patient {    
    private Integer idPatient;
    private String name;
    private String profilePhoto;
    private Integer age;
    private String gender;
    private String profession;
}