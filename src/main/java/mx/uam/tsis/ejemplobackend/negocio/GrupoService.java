package mx.uam.tsis.ejemplobackend.negocio;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import mx.uam.tsis.ejemplobackend.datos.GrupoRepository;
import mx.uam.tsis.ejemplobackend.negocio.modelo.Alumno;
import mx.uam.tsis.ejemplobackend.negocio.modelo.Grupo;

@Service
@Slf4j
public class GrupoService {
	@Autowired
	private GrupoRepository grupoRepository;
	
	//Esta inyeccion de dependencia se hace para que Grupo service se comunique con alumno service entre capa
	@Autowired
	private AlumnoService alumnoService;
	
	public Grupo create(Grupo nuevo) {
		return grupoRepository.save(nuevo);
	}
	
	
	public Iterable<Grupo> retriveAll(){
		return grupoRepository.findAll();
	}
	
	public boolean addStudentToGroup(Integer groupId, Integer matricula) {
log.info("Agregando alumno con matricula "+matricula+" al grupo "+groupId);
		
		// 1.- Recuperamos primero al alumno
		Alumno alumno = alumnoService.retrive(matricula);
		
		// 2.- Recuperamos el grupo
		Optional <Grupo> grupoOpt = grupoRepository.findById(groupId);
		
		// 3.- Verificamos que el alumno y el grupo existan
		if(!grupoOpt.isPresent() || alumno == null) {
			
			log.info("No se encontró alumno o grupo");
			
			return false;
		}
		
		// 4.- Agrego el alumno al grupo
		Grupo grupo = grupoOpt.get();
		grupo.addAlumno(alumno);
		
		// 5.- Persistir el cambio
		grupoRepository.save(grupo);
		
		return true;
	}
			

}
