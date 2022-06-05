package cl.instituto.java.repository;

import java.util.List;

import cl.instituto.java.modelo.Carrera;

public interface InterfaceCarrera {
	
	public List<Carrera> list();
	public Carrera search(int id);
	public void create(Carrera carrera);
	public void update(Carrera carrera);
	public void delete(int id);
		
		

}
