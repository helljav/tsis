package mx.uam.tsis.ejemplobackend.negocio;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mx.uam.tsis.ejemplobackend.datos.AlumnoRepository;
import mx.uam.tsis.ejemplobackend.negocio.modelo.Alumno;
/**
 * Clase que se encarga de la logica de negocio para la entidad del alumno
 * @author HP
 *
 */

@Service
@Slf4j//Te permite hacer los log con la dependencia de lombok
public class AlumnoService {
	@Autowired //Con esto va a realizar la inyeccion de dependencias 
	private AlumnoRepository alumnoRepository;
	
	/**
	 * Metodo que permite crear nuevos alumnos 
	 * @param nuevoAlumno
	 * @return alumno si es exitoso, null de lo contrario 
	 */
	public Alumno create(Alumno nuevoAlumno) {
		//Regla de negocio no se puede crear mas de un alumno con la misma matricula
		Optional <Alumno> alumno = alumnoRepository.findById(nuevoAlumno.getMatricula());
		log.info(alumno.toString());
		//Si el alumno no se encuentra en la base de datos lo guarda
		if(alumno.isEmpty()==true) {
			return alumnoRepository.save(nuevoAlumno);
			
		}else {
			return null;
		}
		
	}
	
	
	
	
	/**
	 * Metodo que te devuelve todos los alumnos
	 * @return Regresa todos los Alumnos actualmente guardaddos (Get All)
	 * 
	 */
	public Iterable <Alumno> retriveAll(){
		return alumnoRepository.findAll();
	}
	
	
	/**
	 * Metodo que te devuelve un alumno vase a su matricula (ID)
	 * 
	 * @param matricula
	 * @return Regresa a un alumno base a su matricula (Get)
	 */
	
	public Optional <Alumno> retrive(Integer matricula) {
		return alumnoRepository.findById(matricula);
	}
	
	/**
	 * Metodo que te permite actualizar la informacion de los alumnos
	 * @param matricula Id del alumno
	 * @param update parametro de tipo alumno que contiene los datos a actualizar
	 * @return Si es que por alguna razon falla al actualizar mandara un false, de lo contraio un true
	 */
	public boolean update(Integer matricula, Alumno update) {
		if(matricula != update.getMatricula()) {
			if(alumnoRepository .save(update)==null) {
				
				return false;			
			}
			return true;
			
		}
		return false;
		
		
	}
	
	
	/**
	 * Metodo que te permite eliminar a un alumno base a su ID (matricula)
	 * @param matricula ID del alumno a eliminar
	 * @return True si fue exitoso, false si no pudo hacerlo
	 */
	public void delete(Integer matricula) {
		alumnoRepository.deleteById(matricula);
		
	}
	
}
