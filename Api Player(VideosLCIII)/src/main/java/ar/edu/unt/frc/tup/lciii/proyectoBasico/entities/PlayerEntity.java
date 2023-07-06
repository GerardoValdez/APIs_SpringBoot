package ar.edu.unt.frc.tup.lciii.proyectoBasico.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
/*Entidad de la base de datos*/
@Entity
@Table(name = "players")
public class PlayerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String userName;
    @Column
    private String password;
    @Column
    private String email;
    @Column
    private String avatar;
    @Column
    private LocalDateTime lastLogin;
    @Column
    private LocalDateTime createdAt;
    @Column
    private LocalDateTime updatedAt;
}
