package mx.uam.tsis.ejemplobackend.servicios;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import mx.uam.tsis.ejemplobackend.negocio.AlumnoService;
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
	@Autowired //Con esto se concreta la inyeccion de dependecnias por parte de Spring
	private AlumnoService alumnoService;
	//private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(AlumnoController.class);
	
	
	
	/**
	 * PostMapping Le dice al metodo que va a recibir un JSON y mapea la ruta base a un verbo 
	 * @param nuevoAlumno Toma el JSON y en automatico lo combierte a una variable Alumno
	 * @return Devuelve un estatus si se pudo hacer o no 
	 */
	
	@PostMapping(path = "/alumnos", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity <?> create(@RequestBody @Valid Alumno nuevoAlumno) {
		
		// No se deben agregar dos alumnos con la misma matricula
		
		log.info("Recibí llamada a create con "+nuevoAlumno);
		Alumno alumno = alumnoService.create(nuevoAlumno);
		if(alumno !=null) {
			return ResponseEntity.status(HttpStatus.CREATED).body(alumno);
		}
		else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se pudo crear el alumno, chequelo bien o cumuniquese con el admin");
		}
		
		
	}
	
	
	/**
	 * Lo mismo que arriba pero ahora con el verbo GET, regresa a todos los alumnos
	 * @return Regresa un estado 
	 */
	@GetMapping(path = "/alumnos", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity <?> retrieveAll() {
		return ResponseEntity.status(HttpStatus.OK).body(alumnoService.retriveAll());
		
	}
	
	
	
	
	
	/**
	 * El mismo verbo GET de arriba pero esta vez solo regresara al alumno con esa matricula
	 * @param matricula Obtiene el valor "matricula" del JSON
	 * @return
	 */
	@GetMapping(path = "/alumnos/{matricula}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity <?> retrieve(@PathVariable("matricula") Integer matricula) {
		log.info("Buscando al alumno con matricula "+matricula);
		
		Alumno alumno = alumnoService.retrive(matricula);		
		if(alumno != null) {
			return ResponseEntity.status(HttpStatus.OK).body(alumno);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

		
		
	}
	
	/**
	 * Se optiene la id del alumno que se va a modificar del archivo JSON
	 * @param updateAlumno
	 * @return
	 */
	@PutMapping(path = "/alumnos/{matricula}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity <?> update(@PathVariable("matricula") Integer matricula, @RequestBody  Alumno updateAlumno){
		
		Alumno alumno = alumnoService.retrive(matricula);
		
		if(alumno!=null) {
			//log.info("Si existe un alumno "+updateAlumno);
			if(alumnoService.update(matricula, updateAlumno)) {
				alumno = alumnoService.retrive(matricula); 
				return ResponseEntity.status(HttpStatus.OK).body(alumno);
			}
			return ResponseEntity.status(HttpStatus.CONFLICT).body("El ID del alumno debe de coincidir");
			
		}
		else {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("No se encontro el alumno");
		}
		
	}
	
	
	@DeleteMapping(path = "/alumnos/{matricula}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity <?> delete(@PathVariable("matricula") Integer matricula) {
		Alumno alumno = alumnoService.retrive(matricula);
		if(alumno!=null) {
			alumnoService.delete(matricula);
			log.info("El alumno fue eliminado con matricula: "+ matricula);
			return ResponseEntity.status(HttpStatus.OK).body(alumnoService.retrive(matricula));
		}
		else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontro el alumno a eliminar");
		}
				
	}
 
}
