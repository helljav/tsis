package mx.uam.tsis.ejemplobackend.servicios;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import mx.uam.tsis.ejemplobackend.negocio.modelo.Alumno;

/**
 * Controlador para el API rest
 * 
 * @author humbertocervantes
 *
 */
@RestController
@Slf4j
public class AlumnoController {
	private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(AlumnoController.class);
	
	// La "base de datos"
	private Map <Integer, Alumno> alumnoRepository = new HashMap <>();
	
	/**
	 * PostMapping Le dice al metodo que va a recibir un JSON y mapea la ruta base a un verbo 
	 * @param nuevoAlumno Toma el JSON y en automatico lo combierte a una variable Alumno
	 * @return Devuelve un estatus si se pudo hacer o no 
	 */
	
	@PostMapping(path = "/alumnos", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity <?> create(@RequestBody Alumno nuevoAlumno) {
		
		// No se deben agregar dos alumnos con la misma matricula
		
		log.info("Recibí llamada a create con "+nuevoAlumno);
		
		alumnoRepository.put(nuevoAlumno.getMatricula(), nuevoAlumno);
		
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	
	/**
	 * Lo mismo que arriba pero ahora con el verbo GET, regresa a todos los alumnos
	 * @return Regresa un estado 
	 */
	@GetMapping(path = "/alumnos", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity <?> retrieveAll() {
		return ResponseEntity.status(HttpStatus.OK).body(alumnoRepository.values());
		
	}
	
	/**
	 * El mismo verbo GET de arriba pero esta vez solo regresara al alumno con esa matricula
	 * @param matricula Obtiene el valor "matricula" del JSON
	 * @return
	 */
	@GetMapping(path = "/alumnos/{matricula}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity <?> retrieve(@PathVariable("matricula") Integer matricula) {
		log.info("Buscando al alumno con matricula "+matricula);
		
		Alumno alumno = alumnoRepository.get(matricula);
		
		if(alumno != null) {
			return ResponseEntity.status(HttpStatus.OK).body(alumno);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		
		
	}
	/*
	public update() {
		
	}
	
	public delete() {
		
	}*/
 
}
