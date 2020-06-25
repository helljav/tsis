package mx.uam.tsis.ejemplobackend.datos;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;
import mx.uam.tsis.ejemplobackend.negocio.modelo.Alumno;
/**
 * Se encarga de almacenar y recuperar alumnos
 * @author HP
 *
 */
@Component
public class AlumnoRepository {
	//La "base de datos"
	private Map <Integer, Alumno> alumnoRepository = new HashMap<>();
	
	
	/**
	 * Guarda en la 80
	 * @param nuevoAlumno variable tipo Alumno que se guardara
	 * @return
	 */
	public Alumno save(Alumno nuevoAlumno) {
		alumnoRepository.put(nuevoAlumno.getMatricula(), nuevoAlumno);
		return nuevoAlumno;
	}
	
	
	
	public Alumno findByMatricula(Integer matricula) {
		return alumnoRepository.get(matricula);
	}
	
	
	
	
	public List <Alumno> findAll(){
		return new ArrayList <> (alumnoRepository.values());
	}
	
	
	public boolean update(Integer matricula, Alumno update) {
		if(alumnoRepository.replace(matricula, update)==null) {
			return false;
		}
		else {
			return true;
		}	
	}
	
	
	
	public boolean delete(Integer matricula) {
		if(alumnoRepository.remove(matricula)==null) {
			return false;
		}
		else {
			return true;
		}
		
	}

}
