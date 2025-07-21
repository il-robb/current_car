package com.betacom.car.dao;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.betacom.car.models.Uffici;


@Repository  //anche questo è un component , 
public class UfficiDAO {

	//private final JdbcTemplate jdbcTemplate; //crea un template jdbc, gestito da springboot
	private final NamedParameterJdbcTemplate jdbcTemplate; //define template con nomi dei parametri
	public UfficiDAO(DataSource ds)
	{
		this.jdbcTemplate = new NamedParameterJdbcTemplate(ds); //carico il template con i dati del datasource
	}
	
//	public List<Uffici> getUffici(){
//		
//		return jdbcTemplate.query("select * from uffici", rs->{
//			List<Uffici> uffici = new ArrayList<Uffici>();
//			while(rs.next())
//			{
//				Uffici u = new Uffici();
//				u.setId_ufficio(rs.getInt("id_uffici"));
//				u.setNome_ufficio(rs.getString("nome_ufficio"));
//				uffici.add(u);
//			}
//			return uffici;
//			}	
//		);
//	}
	
	public List<Uffici> getUffici() //a differenza del primo non carico a mano ma tramite template
	{
		return jdbcTemplate.query("select * from uffici",BeanPropertyRowMapper.newInstance(Uffici.class));
	}
	
	public List<Uffici> getUfficioById(Integer id) //a differenza del primo non carico a mano ma tramite template
	{
		SqlParameterSource para = new MapSqlParameterSource("id",id);
		String sql = "select * from uffici where id_uffici =:id";
		
		return jdbcTemplate.query(sql,para,BeanPropertyRowMapper.newInstance(Uffici.class));
	}
	
	@Transactional (rollbackFor = Exception.class)
	public int addUfficio(String nome)
	{
		String sql = "insert into uffici (nome_ufficio) values ( :nome)";
		SqlParameterSource para = new MapSqlParameterSource("nome",nome);
		return jdbcTemplate.update(sql, para);
		
	}
	
}
