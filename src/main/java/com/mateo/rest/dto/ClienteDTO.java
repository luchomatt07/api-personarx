package com.mateo.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ClienteDTO {

    private String nombres;
    private String fechanacimiento;
    private String estado;
    private String apellidos;
    private String dni;
}
