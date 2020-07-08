package mx.uam.tsis.ejemplobackend.negocio.modelo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase que representa a la entidad Alumno
 * @author HP
 *
 */
@NoArgsConstructor
@AllArgsConstructor//Te genera los constructores gracias a la dependencia de Lombok
@Builder 
@Data // Permite la creacion de geters y seters con la dependencia lombok
@Entity //Perimite identificar a la BD como una identidad para JPA y H2
public class Alumno {
	
	@NotNull //Es una validacion que se hace gracias a la dependencia validation
	@ApiModelProperty(notes = "Matricula del alumno", required = true) //Con esto le decimos al modelo de swagger que es obligatoria la matricula
	@Id //PrimaryKey para la base de datos
	private Integer matricula;
	
	
	@NotBlank//Es una validacion que se hace gracias a la dependencia validation
	@ApiModelProperty(notes = "Nombre del alumno", required = true)
	private String nombre;
	
	
	@NotBlank//Es una validacion que se hace gracias a la dependencia validation
	@ApiModelProperty(notes = "Carrera del alumno", required = true)
	private String carrera;
	
}
