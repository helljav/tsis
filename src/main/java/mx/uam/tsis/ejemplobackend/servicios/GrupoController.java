package mx.uam.tsis.ejemplobackend.servicios;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import mx.uam.tsis.ejemplobackend.negocio.GrupoService;
import mx.uam.tsis.ejemplobackend.negocio.modelo.Grupo;

@RestController
@Slf4j
public class GrupoController {
	@Autowired
	private GrupoService grupoService;
	
	
	
	
	
	@ApiOperation(
				value = "Crear grupo",
				notes = "Permite crear un nuevo grupo"
			)
	@PostMapping(path = "/grupos", consumes = "application/json", produces = "application/json")
	public ResponseEntity <?> create (@RequestBody @Valid Grupo nuevoGrupo ){
		log.info("Recibi llamada a create con " + nuevoGrupo );
		Grupo grupo = grupoService.create(nuevoGrupo);
		if(grupo != null) {
			return ResponseEntity.status(HttpStatus.CREATED).body(grupo);
		}
		else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se puede crear el grupo");
		}
		
	}
	
	
	
	
	
	@ApiOperation(
			value = "Obtener grupos",
			notes = "Permite Obtener todos los grupos almacenados hasta el momento"
		)
	@GetMapping(path = "/grupós", produces = " application/json" )
	public ResponseEntity <?> retriveAll(){
		Iterable <Grupo> result = grupoService.retriveAll();
		return ResponseEntity.status(HttpStatus.OK).body(result); 
		
	}
	
	
	
	
	
	

	/**
	 * 
	 * POST /grupos/{id}/alumnos?matricula=1234
	 * 
	 * PROBAR ESTE!!!
	 * 
	 * @return
	 */
	@PostMapping(path = "/grupos/{id}/alumnos", produces = "application/json")
	public ResponseEntity <?> addStudentToGroup(
			@PathVariable("id") Integer id,
			@RequestParam("matricula") Integer matricula) {
		
		boolean result = grupoService.addStudentToGroup(id, matricula);
		
		if(result) {
			return ResponseEntity.status(HttpStatus.OK).build(); 
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); 
		}
		
	
	}

	
	
	
	
	
	

}
