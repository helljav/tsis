package mx.uam.tsis.ejemplobackend.negocio;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import mx.uam.tsis.ejemplobackend.datos.AlumnoRepository;
import mx.uam.tsis.ejemplobackend.negocio.modelo.Alumno;
import mx.uam.tsis.ejemplobackend.negocio.modelo.Grupo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)// Uso de Mockito
public class AlumnoServiceTest {
	@Mock //Generado por Mockito
	private AlumnoRepository alumnoRepositoryMock; //Con esto vamos a emular la comunicacion a la base de datos
	
	
	@InjectMocks //Los mockitos generados se los vamos a poner (Inyectar) a la clase de prueba
	private AlumnoService alumnoService; //Unidad que va a ser probada
	
	
	//EMPEZAMOS A HACER LOS CASOS DE PRUEBAS
	@Test
	public void testSuccesfulCreate() {
		Alumno alumno = new Alumno();
		alumno.setCarrera("Compútacion");
		alumno.setMatricula(2143008102);
		alumno.setNombre("Pruebin");
		
		//Simula lo que haria el alumnoRepository ral cuando le pasan una matricula del alumno
		when(alumnoRepositoryMock.findById(2143008102)).thenReturn(Optional.ofNullable(null));
		
		//Tenemos que simular que el alumnop haya sido guardadp
		when(alumnoRepositoryMock.save(alumno)).thenReturn(alumno);
		
		//Ponemos a prueba la unidad
		alumno = alumnoService.create(alumno);
		
		//Aqui compruebo el resultado
		assertNotNull(alumno); //Probar que la referencia del alumno no es nula
		
	}
	
	@Test
	public void testUnsuccesfulCreate() {
		Alumno alumno = new Alumno();
		alumno.setCarrera("Compútacion");
		alumno.setMatricula(2143008102);
		alumno.setNombre("Pruebin");
		
		//Simula lo que haria el alumnoRepository ral cuando le pasan una matricula del alumno
		when(alumnoRepositoryMock.findById(2143008102)).thenReturn(Optional.ofNullable(alumno));
		
		//Ponemos a prueba la unidad
		alumno = alumnoService.create(alumno);
		
		//Aqui compruebo el resultado
		assertNull(alumno); //Probar que la referencia del alumno no es nula		
		
	}
	
	
	
	
	
	@Test
	public void testSuccesfulretriveAll() {
		Alumno alumno = new Alumno();
		alumno.setCarrera("Compútacion");
		alumno.setMatricula(2143008102);
		alumno.setNombre("Pruebin");
		
		Alumno alumno2 = new Alumno();
		alumno.setCarrera("Bioquimica");
		alumno.setMatricula(21291);
		alumno.setNombre("Pruebin2");
		
		
		
		List <Alumno> list = new ArrayList<>();
		list.add(alumno);
		list.add(alumno2);
		Collection <Alumno> collection = list;
		Iterable <Alumno> alumnoIterable = collection;
		
		
		when(alumnoRepositoryMock.findAll()).thenReturn(alumnoIterable);
		alumnoIterable = alumnoService.retriveAll();
		
		assertNotNull(alumnoIterable);
	}
	
	
	
	@Test
	public void testUnsuccesfulretriveAll() {
		Alumno alumno = new Alumno();
		alumno.setCarrera("Compútacion");
		alumno.setMatricula(2143008102);
		alumno.setNombre("Pruebin");
		
		Alumno alumno2 = new Alumno();
		alumno.setCarrera("Bioquimica");
		alumno.setMatricula(21291);
		alumno.setNombre("Pruebin2");
		
		
		
		List <Alumno> list = new ArrayList<>();
		list.add(alumno);
		list.add(alumno2);
		Collection <Alumno> collection = list;
		Iterable <Alumno> alumnoIterable = collection;
		
		
		when(alumnoRepositoryMock.findAll()).thenReturn(null);
		alumnoIterable = alumnoService.retriveAll();
		
		assertNull(alumnoIterable);
	}
	
	
	@Test
	public void testSuccessfulUpdate() {

		Alumno alumno = new Alumno();
		alumno.setCarrera("Computación");
		alumno.setMatricula(12345678);
		alumno.setNombre("Pruebin");

		
		Alumno alumnoActualizado = new Alumno();
		alumnoActualizado.setCarrera("Electrónica");
		alumnoActualizado.setMatricula(12345678);
		alumnoActualizado.setNombre("PruebinActualizado");

		// Simula lo que haría el alumnoRepository real cuando le pasan una matricula de alumno
		// que ya ha sido guardado
		when(alumnoRepositoryMock.findById(12345678)).thenReturn(Optional.ofNullable(alumno));

		when(alumnoRepositoryMock.save(alumno)).thenReturn(alumno);
		
		/*
		when(alumnoRepositoryMock.save(alumno)).thenAnswer(new Answer <Alumno> () {
			@Override
			public Alumno answer(InvocationOnMock invocation) throws Throwable {
				// Lo siguiente nos permite acceder al parametro que se le pasa a Save
				Object[] args = invocation.getArguments();
				Alumno alumnoRecibidoComoParametro = (Alumno) args [0];
				
				// Corroboramos que el alumno que es pasado al save tiene sus atributos
				// actualizados
				assertEquals(alumnoActualizado.getCarrera(),alumnoRecibidoComoParametro.getCarrera());
				assertEquals(alumnoActualizado.getNombre(),alumnoRecibidoComoParametro.getNombre());
				
				
				return alumnoRecibidoComoParametro;
			}});*/
	
		boolean result = alumnoService.update(12345678, alumnoActualizado);
		
		assertTrue(result);
	
	}
	
	
	
	//Caso para eliminar un alumno
	@Test
	public void testSuccesfulDelete() {
		Alumno alumno = new Alumno();
		alumno.setCarrera("Computación");
		alumno.setMatricula(12345678);
		alumno.setNombre("Prueba");
		//Simula lo que el alumno repository con la base de datos se guarda si no esta en la base de datos
		when(alumnoRepositoryMock.findById(12345678)).thenReturn(Optional.of(alumno));
		
//		when(alumnoRepositoryMock.deleteById(12345678)).thenReturn(Optional.of(alumno));
//		//Ejecucion de unidad de prueba
		boolean rest = alumnoService.delete(alumno.getMatricula());   
		assertTrue(rest); //Permite que la refierencia a alumno no se nula
	}
	
	
	
	
	
	

}
