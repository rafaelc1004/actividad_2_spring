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

import cl.instituto.java.modelo.Carrera;
import cl.instituto.java.repository.InterfaceCarrera;

@Controller
@RequestMapping("/carrera")
public class CarreraControlador {
	
	@Autowired
	InterfaceCarrera interfaceCarrera;
	
	@GetMapping("/nuevo")
	public String carreraNuevo(Carrera carrera) {
		return "carrera/form";
	}
	
	@GetMapping("/editar/{carreraId}")
	public String carreraEditar(@PathVariable int carreraId, Model modelo) {
		Carrera carrera = interfaceCarrera.search(carreraId);
		modelo.addAttribute("carrera", carrera);
		return "carrera/form";
	}
	
	@GetMapping("/eliminar")
	public String eliminar(@RequestParam(name="id", required = true)int id) {
		interfaceCarrera.delete(id);
		return "redirect:/carrera/listado";
	}

	@PostMapping("/procesar")
	public String procesarForm(@Valid Carrera carrera, BindingResult informeValidacion) {
		if(informeValidacion.hasErrors()) {
			return "carrera/form";
		}
		
		if(carrera.getId()==0) {
			interfaceCarrera.create(carrera);
		}else {
			interfaceCarrera.update(carrera);
		}
		
		return "redirect:/carrera/listado";
	}
	
	
	
	
	@GetMapping("/listado")
	public String editar(Model modelo) {
		List<Carrera> lista = interfaceCarrera.list();
		modelo.addAttribute("listadoCarrera", lista);
		return "carrera/listado";
	}
	
	
	

}
