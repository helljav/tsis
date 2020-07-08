package mx.uam.tsis.ejemplobackend.negocio;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import mx.uam.tsis.ejemplobackend.datos.AlumnoRepository;
import mx.uam.tsis.ejemplobackend.negocio.modelo.Alumno;
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
	

}
