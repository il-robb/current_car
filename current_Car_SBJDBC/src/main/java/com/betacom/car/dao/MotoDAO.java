package com.betacom.car.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.betacom.car.models.Moto;
@Repository
public class MotoDAO {
	private final NamedParameterJdbcTemplate jdbcTemplate; //define template con nomi dei parametri
	public MotoDAO(DataSource ds)
	{
		this.jdbcTemplate = new NamedParameterJdbcTemplate(ds);
		 
	}
	
	public List<Moto> getMoto() //a differenza del primo non carico a mano ma tramite template
	{
		return jdbcTemplate.query("select * from moto",BeanPropertyRowMapper.newInstance(Moto.class));
	}
	public List<Moto> getMotoById(Integer id) //a differenza del primo non carico a mano ma tramite template
	{
		SqlParameterSource para = new MapSqlParameterSource("id",id);
		String sql = "select * from moto where id_veicolo =:id";
		
		return jdbcTemplate.query(sql,para,BeanPropertyRowMapper.newInstance(Moto.class));
	}
	
	
	@Transactional (rollbackFor = Exception.class)
	public int addMoto(SqlParameterSource para)
	{
		String sql = "insert into moto (id_veicolo,targa,cilindrata) values (:id_veicolo,:targa,:cilindrata)";
		return jdbcTemplate.update(sql, para);
	}
	
	
	public int insert(Object[] parameters) throws Exception{
		int numero = 0;
		return numero;
	}
	
	public int delete(Object[] parameters) throws Exception{
		int numero = 0;
		
		
		return numero;
	}
	public int update(String qryname,Object[] parameters) throws Exception{
		int numero = 0;
		return numero;
	}
	
}
