package ar.edu.unt.frc.tup.lciii.proyectoBasico.dtos.common;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/*Esta clase la creamos para devolver errores más amigables al usuario*/

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorApi {

    private String timestamp;

    private Integer status;

    private String error;

    private String message;
}