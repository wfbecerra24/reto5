package com.sergio_arboleda.ciclo4.Entities;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/*
Anotaciones de lombok para los constructores, setters y getters

 */
@Document(collection = "usuarios")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {
    @Id

    private Integer id;
    private String identification;
    private String name;
    private String address;
    private String cellPhone;
    private String email;
    private String password;
    private String zone;
    private String type;
}
