package cl.instituto.java.controller;


import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cl.instituto.java.modelo.Alumno;
import cl.instituto.java.modelo.Carrera;
import cl.instituto.java.repository.InterfaceAlumno;
import cl.instituto.java.repository.InterfaceCarrera;

@Controller
@RequestMapping("/alumno")
public class AlumnoControlador {
	
	@Autowired
	InterfaceAlumno interfaceAlumno;
	
	@Autowired
	InterfaceCarrera interfaceCarrera;
	
	@GetMapping("/nuevo")
	public String alumnoForm(Alumno alumno, Model modelo) {
		List<Carrera> listaCarreras = interfaceCarrera.list();
		modelo.addAttribute("carreras", listaCarreras);
		return "alumno/form";
	}
	
	@GetMapping("/editar/{alumnoId}")
	public String alumnoEditar(@PathVariable int alumnoId, Model modelo) {
		Alumno alumno = interfaceAlumno.search(alumnoId);
		modelo.addAttribute("alumno", alumno);
		List<Carrera> lista = interfaceCarrera.list();
		modelo.addAttribute("carreras", lista);
		return "alumno/form";
	}
	
	@GetMapping("/eliminar")
	public String eliminar(@RequestParam(name="id", required = true)int id) {
		interfaceAlumno.delete(id);
		return "redirect:/alumno/listado";
	}

	@PostMapping("/procesar")
	public String procesarForm(@Valid Alumno alumno, BindingResult informeValidacion) {
		if(informeValidacion.hasErrors()) {
			return "alumno/form";
		}
		
		if(alumno.getId()==0) {
			interfaceAlumno.create(alumno);
		}else {
			interfaceAlumno.update(alumno);
		}
		
		return "redirect:/alumno/listado";
	}
	
	
	
	
	@GetMapping("/listado")
	public String editar(Model modelo) {
		List<Alumno> lista = interfaceAlumno.list();
		modelo.addAttribute("listadoAlumno", lista);
		return "alumno/listado";
	}
	
	
	

}
