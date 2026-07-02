package cl.techstore.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuditoriaDTO {

    private String accion;
    private Long productoId;
    private String nombre;
    private String usuario;
    private String fecha;
}