package cl.instituto.java.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import cl.instituto.java.modelo.Carrera;
@Repository
public class CarreraRepository implements InterfaceCarrera {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private Carrera makeObject(ResultSet rs, int fileNum)throws SQLException{
		int id = rs.getInt("id");
		String nombre =rs.getString("nombre");
		String descripcion = rs.getString("descripcion");
		
		return new Carrera(id, nombre, descripcion);
	}
	
	@Override
	public List<Carrera> list() {
		String sql = "Select * from Carreras";
		return jdbcTemplate.query(sql, this::makeObject);
	}

	@Override
	public Carrera search(int id) {
		String sql = "select * from Carreras where id =?";
		return jdbcTemplate.queryForObject(sql, this::makeObject, id);
		
	}

	@Override
	public void create(Carrera carrera) {
		String sql = "Insert into carreras(nombre, descripcion) values(?,?)";
		jdbcTemplate.update(sql, carrera.getNombre(), carrera.getDescripcion());

	}

	@Override
	public void update(Carrera carrera) {
		String sql = "update carreras set nombre = ?, descripcion = ? where id = ?";
		jdbcTemplate.update(sql, carrera.getNombre(), carrera.getDescripcion(), carrera.getId());

	}

	@Override
	public void delete(int id) {
		String sql = " delete from carreras where id =?";
		jdbcTemplate.update(sql, id);

	}

}
