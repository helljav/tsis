package mx.uam.tsis.ejemplobackend.negocio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import mx.uam.tsis.ejemplobackend.datos.AlumnoRepository;
import mx.uam.tsis.ejemplobackend.negocio.modelo.Alumno;

@Service
@Slf4j
public class AlumnoService {
	@Autowired //Con esto va a realizar la inyeccion de dependencias 
	private AlumnoRepository alumnoRepository;
	
	/**
	 * 
	 * @param nuevoAlumno
	 * @return alumno si es exitoso, null de lo contrario 
	 */
	public Alumno create(Alumno nuevoAlumno) {
		//Regla de negocio no se puede crear mas de un alumno con la misma matricula
		Alumno alumno = alumnoRepository.findByMatricula(nuevoAlumno.getMatricula());
		
		//Si el alumno no se encuentra en la base de datos lo guarda
		if(alumno == null) {
			return alumnoRepository.save(nuevoAlumno);
			
		}else {
			return null;
		}
		
	}
	
	
	
	
	/**
	 * 
	 * @return Regresa todos los Alumnos actualmente guardaddos (Get All)
	 * 
	 */
	public List <Alumno> retriveAll(){
		return alumnoRepository.findAll();
	}
	
	
	/**
	 * Regresa a un alumno base a su matricula (Get)
	 * @param matricula
	 * @return
	 */
	
	public Alumno retrive(Integer matricula) {
		return alumnoRepository.findByMatricula(matricula);
	}
	
	/**
	 * 
	 * @param matricula
	 * @param update
	 * @return Si es que por alguna razon falla al actualizar mandara un false, de lo contraio un true
	 */
	public boolean update(Integer matricula, Alumno update) {
		if(matricula != update.getMatricula()) {
			if(alumnoRepository.update(matricula, update)==false) {
				
				return false;			
			}
			return true;
			
		}
		return false;
		
		
	}
	
	
	
	public boolean delete(Integer matricula) {
		if(alumnoRepository.delete(matricula)==false) {
			return false;			
		}
		else {
			return true;
		}
		
	}
	
}
