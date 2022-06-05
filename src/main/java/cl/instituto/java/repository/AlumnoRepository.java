package cl.instituto.java.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import cl.instituto.java.modelo.Alumno;
import cl.instituto.java.modelo.Carrera;
@Repository
public class AlumnoRepository implements InterfaceAlumno {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	InterfaceCarrera interfaceCarrera;
	
	private Alumno makeObject(ResultSet rs, int fileNum)throws SQLException{
		int id = rs.getInt("id");
		String nombre =rs.getString("nombre");
		LocalDate fecha =rs.getObject("fecha_nacimiento", LocalDate.class);
		int carreraId = rs.getInt("idCarrera");
		Carrera carrera = interfaceCarrera.search(carreraId);
		return new Alumno(id, nombre, fecha, carrera);
	}
	
	@Override
	public List<Alumno> list() {
		String sql = "Select * from alumnos";
		return jdbcTemplate.query(sql, this::makeObject);
	}

	@Override
	public Alumno search(int id) {
		String sql = "select * from alumnos where id = ?";
		return jdbcTemplate.queryForObject(sql, this::makeObject, id);
		
	}

	@Override
	public void create(Alumno alumno) {
		String sql = "Insert into alumnos(nombre, fecha_nacimiento, idCarrera) values(?,?,?)";
		jdbcTemplate.update(sql, alumno.getNombre(), alumno.getFechaNacimiento(), alumno.getCarrera().getId());

	}

	@Override
	public void update(Alumno alumno) {
		String sql = "update alumnos set nombre = ?, fecha_nacimiento = ?, idCarrera = ? where id = ?";
		jdbcTemplate.update(sql, alumno.getNombre(), alumno.getFechaNacimiento(), alumno.getCarrera().getId(), alumno.getId());

	}

	@Override
	public void delete(int id) {
		String sql = " delete from alumnos where id =?";
		jdbcTemplate.update(sql, id);

	}

}
