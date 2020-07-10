package mx.uam.tsis.ejemplobackend.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import mx.uam.tsis.ejemplobackend.datos.AlumnoRepository;
import mx.uam.tsis.ejemplobackend.negocio.modelo.Alumno;

//Para las pruebas de integracion no usaremos Mockito usaremos el siguiente
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) 
public class AlumnoControllerIntegrationTest {
	
	//Con este onbjetos nos permitira habalr directamente con el end point
	@Autowired
	private TestRestTemplate restTemplate;
	
	//Con esto podemos acceder a la base de datos
	@Autowired
	private AlumnoRepository alumnoRepository;
	
	
	
	@BeforeEach
	public void prepare() {
		
	}
	
	@Test
	public void testCreate201() {
		Alumno alumno = new Alumno();
		alumno.setCarrera("Compútacion");
		alumno.setMatricula(2143008102);
		alumno.setNombre("Pruebin");
		
		//Creo el encabezado
		// Estamos hablando en html (Simulamos como si estuvieramos en la pagina)
		HttpHeaders headers = new HttpHeaders();
		headers.set("content-type", MediaType.APPLICATION_JSON_VALUE.toString());
		
		//Creo la peticion con el alumno como body y el encabezado 
		HttpEntity <Alumno> request = new HttpEntity <> (alumno, headers);
		
		
		//Aqui hacemos una simulacion como Ajax o Jquery ya que se va a comunicar con el endpoint
		ResponseEntity <Alumno> responseEntity = restTemplate.exchange("/alumnos", HttpMethod.POST, request, Alumno.class );
		
		//Corroboro que el endpoint me regresa el status deseado
		assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
		
		//Corroboro que en la base de datos se haya almacenado
		Optional<Alumno> opAlumno = alumnoRepository.findById(2143008102);  
		assertEquals(alumno, opAlumno.get());
		
		
		}
	
	
	@Test
	public void testCreate404() {
		
		
		Alumno alumno = new Alumno();
		alumno.setCarrera("Compútacion");
		alumno.setMatricula(2143008102);
		alumno.setNombre("Pruebin");
		
		//Creo el encabezado
		// Estamos hablando en html (Simulamos como si estuvieramos en la pagina)
		HttpHeaders headers = new HttpHeaders();
		headers.set("content-type", MediaType.APPLICATION_JSON_VALUE.toString());
		
		//Creo la peticion con el alumno como body y el encabezado 
		HttpEntity <Alumno> request = new HttpEntity <> (alumno, headers);
		
		
		//Aqui hacemos una simulacion como Ajax o Jquery ya que se va a comunicar con el endpoint
		ResponseEntity <Alumno> responseEntity = restTemplate.exchange("/alumnos", HttpMethod.POST, request, Alumno.class );
		
		
		assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
		
	}
}
