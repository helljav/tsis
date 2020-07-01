package mx.uam.tsis.ejemplobackend.presentacion;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;
import mx.uam.tsis.ejemplobackend.servicios.AlumnoController;

/**
 * Controlador web
 * 
 * @author HP
 *
 */
@Controller
@Slf4j
public class MainController {
	//private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(AlumnoController.class);
	@GetMapping("/")
	public String index() {
		
		log.info("Se invocó el método index()");
	
		return "index";
	}

	@RequestMapping("/ejemplo")
	@ResponseBody
	public String ejemplo() {
		
	
		return "esto es un ejemplo";
	}

}
