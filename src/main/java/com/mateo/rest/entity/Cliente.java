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
public class Cliente {

	@Id
    private String id;
    private String nombres;
    private String fechanacimiento;
    private String estado;
    private String apellidos;
    private String dni;
    
}
