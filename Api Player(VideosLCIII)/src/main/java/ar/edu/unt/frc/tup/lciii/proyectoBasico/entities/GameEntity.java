package ar.edu.unt.frc.tup.lciii.proyectoBasico.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/* El término "LOB" es una abreviatura de "Large Object" (objeto grande)
 y se utiliza para describir datos binarios que son demasiado grandes para
 ser almacenados directamente en una columna de base de datos regular.*/

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "games")
public class GameEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String code;
    @Column
    private String name;
    @Lob
    @Column
    private String description;
    @Lob
    @Column
    private String rules;
}
