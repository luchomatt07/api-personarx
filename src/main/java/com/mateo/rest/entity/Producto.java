package com.mateo.rest.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document
@ToString
@Data
public class Producto {

    @Id
    private String id;
    private String nombre;
    private String estado;
    private String tipo;
}
