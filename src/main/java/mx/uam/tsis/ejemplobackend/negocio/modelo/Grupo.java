package mx.uam.tsis.ejemplobackend.negocio.modelo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

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
public class Grupo {
	@NotBlank
	private String clave;
	
	@Id
	@GeneratedValue // Autogenera un ID unico
	private Integer Id;
	
	
	
	//Podriamos decir que esta es una identiada foranea para ña base de datos ya que podemos tener multiples alumnos en un grupo (cardinalidad multiple)
	@Builder.Default
	@OneToMany(targetEntity = Alumno.class, fetch = FetchType.LAZY, cascade = CascadeType.MERGE) //Decimos que es de uno a muchos, con el Lazy le decimos que recorra su lista de alumnos y nos devuelba solo ese alumno y no todos de forma anticipada
	@JoinColumn(name = "grupoId") // No crea tabla intermedia	
	private List <Alumno> alumnos = new ArrayList<>();
	
	//Debemos de hacer un metodo para agregar alumnos a la lista de alumnbos
	public boolean addAlumno(Alumno alumno) {
		return alumnos.add(alumno);
	}
}

