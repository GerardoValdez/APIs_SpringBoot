package ar.edu.utn.frc.tup.lciii.h2api.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Usuario {
    @Id //indico que el atributo de abajo es la pk
    @GeneratedValue(strategy = GenerationType.IDENTITY) //pk autoincremental
    private Long id;

    @Column(nullable = false) //nombre no puede ser null
    private String nombre;

    @Column(nullable = false) //apellido no puede ser null
    private String apellido;

    @Column(length = 16) //hasta 16 caracteres
    private String telefono;

    @Column(nullable = false) //direccion no puede ser null
    private String direccion;

}
