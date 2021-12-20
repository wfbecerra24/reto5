package com.sergio_arboleda.ciclo4.Entities;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Date;
import java.util.Map;

@Document(collection = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Orden {
    public static String PENDING = "Pendiente";
    public static String APROVED = "Aprobada";
    public static String REJECTED = "Rechazada";

    @Id
    private Integer id;
    private Date registerDay;
    private String status;
    private Usuario salesMan;
    //private Cliente client;

    //hashMap con llave-valor
    private Map<String, Gadget> products;
    //String representa el código del producto y la cantidad
    private Map<String, Integer> quantities;

}
