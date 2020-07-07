package mx.uam.tsis.ejemplobackend.datos;

import org.springframework.data.repository.CrudRepository;
import mx.uam.tsis.ejemplobackend.negocio.modelo.Alumno;
/**
 * Se encarga de almacenar y recuperar alumnos
 * Ya con esto tendriamos todos los metodos para poder almacenar a la base de datos
 * @author HP
 *
 */
public interface AlumnoRepository extends CrudRepository <Alumno, Integer>{//Entidad, Tipo de la llave primaria
	

}
