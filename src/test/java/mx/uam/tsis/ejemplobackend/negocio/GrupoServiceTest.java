package mx.uam.tsis.ejemplobackend.negocio;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyInt;

import mx.uam.tsis.ejemplobackend.datos.AlumnoRepository;
import mx.uam.tsis.ejemplobackend.datos.GrupoRepository;
import mx.uam.tsis.ejemplobackend.negocio.modelo.Alumno;
import mx.uam.tsis.ejemplobackend.negocio.modelo.Grupo;



@ExtendWith(MockitoExtension.class) // Uso de Mockito
public class GrupoServiceTest {

	@Mock
	private GrupoRepository grupoRepositoryMock;
	
	@Mock
	private AlumnoService alumnoServiceMock;
	
	@InjectMocks
	private GrupoService grupoService;
	
	//Prueba añadir un grupo
	@Test
	public void testSuccesfulCreate() {
		Grupo grupo = new Grupo();
		grupo.setId(1);
		grupo.setClave("TST01");
		when(grupoRepositoryMock.save(grupo)).thenReturn(grupo);
		grupo = grupoService.create(grupo);
		
		assertNotNull(grupo);
	}
	
	@Test
	public void testUnsuccesfulCreate() {
		Grupo grupo = new Grupo();
		grupo.setId(1);
		grupo.setClave("TST01");
		
		when(grupoRepositoryMock.save(grupo)).thenReturn(null);
		grupo = grupoService.create(grupo);
		
		assertNull(grupo);
	}
	
	
	//Prueba recuperar
	@Test
	public void testSuccesfulretriveAll() {
		Grupo grupo1 = new Grupo();
		grupo1.setId(1);
		grupo1.setClave("TST01");
		
		Grupo grupo2 = new Grupo();
		grupo2.setId(2);
		grupo2.setClave("Ck01");
		
		List <Grupo> list = new ArrayList<>();
		list.add(grupo1);
		list.add(grupo2);
		Collection <Grupo> collection = list;
		Iterable <Grupo> grupoIterable = collection;
		
		when(grupoRepositoryMock.findAll()).thenReturn(grupoIterable);
		grupoIterable = grupoService.retriveAll();
		
		assertNotNull(grupoIterable);
	}
	
	
	
	@Test
	public void testUnsuccesfulretriveAll() {
		Grupo grupo1 = new Grupo();
		grupo1.setId(1);
		grupo1.setClave("TST01");
		
		Grupo grupo2 = new Grupo();
		grupo2.setId(2);
		grupo2.setClave("Ck01");
		
		List <Grupo> list = new ArrayList<>();
		list.add(grupo1);
		list.add(grupo2);
		Collection <Grupo> collection = list;
		Iterable <Grupo> grupoIterable = collection;
		
		when(grupoRepositoryMock.findAll()).thenReturn(null);
		grupoIterable = grupoService.retriveAll();
		
		assertNull(grupoIterable);
		
		
	}
	
	
	
	//Prueba añadir un alumno a un grupo exitosamente 
	@Test
	public void testSuccesfulAddStudentToGroup (){
		
		Grupo grupo = new Grupo();
		grupo.setId(1);
		grupo.setClave("TST01");

		Alumno alumno = new Alumno();
		alumno.setCarrera("Computación");
		alumno.setMatricula(12345678);
		alumno.setNombre("Pruebin");
		
		// Stubbing para el alumnoService
		when(alumnoServiceMock.retrive(12345678)).thenReturn(alumno);
		
		// Stubbing para grupoRepository
		when(grupoRepositoryMock.findById(grupo.getId())).thenReturn(Optional.of(grupo));
		
		
		boolean result = grupoService.addStudentToGroup(1, 12345678);
		
		assertEquals(true,result);
		
		assertEquals(grupo.getAlumnos().get(0),alumno);
		
	}
	
	
	@Test
	public void testUnsuccesfulAddStudentToGroup (){
		
		Alumno alumno = new Alumno();
		alumno.setCarrera("Computación");
		alumno.setMatricula(12345678);
		alumno.setNombre("Pruebin");
		
		// Stubbing para el alumnoService
		when(alumnoServiceMock.retrive(12345678)).thenReturn(alumno);
		
		// Stubbing para grupoRepository
		when(grupoRepositoryMock.findById(anyInt())).thenReturn(Optional.ofNullable(null));
		
		
		boolean result = grupoService.addStudentToGroup(1, 12345678);
		
		assertEquals(false,result);
		
		
	}


}
