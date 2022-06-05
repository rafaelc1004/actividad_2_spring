package cl.instituto.java;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import cl.instituto.java.modelo.Carrera;
import cl.instituto.java.repository.InterfaceCarrera;

@SpringBootApplication
public class SpringInstitutoCrudApplication implements WebMvcConfigurer{

	@Autowired
	private InterfaceCarrera interfaceCarrera;
	
	
	public static void main(String[] args) {
		SpringApplication.run(SpringInstitutoCrudApplication.class, args);
	
	}


	@Override
	public void addFormatters(FormatterRegistry registry) {
		registry.addConverter(new Converter<String, Carrera>(){
			@Override
			public Carrera convert(String strFormularioCarreraId) {
				int id = Integer.parseInt(strFormularioCarreraId);
				Carrera carrera = interfaceCarrera.search(id);
				return carrera;
			}
		});
	}
	
	

}
