package cl.instituto.java.repository;

import java.util.List;

import cl.instituto.java.modelo.Alumno;

public interface InterfaceAlumno {
	
	public List<Alumno> list();
	public Alumno search(int id);
	public void create(Alumno alumno);
	public void update(Alumno alumno);
	public void delete(int id);
		
		

}
