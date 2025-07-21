package com.betacom.car.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.betacom.car.models.Bici;


@Repository
public class BiciDAO {
	
	private final NamedParameterJdbcTemplate jdbcTemplate; //define template con nomi dei parametri
	public BiciDAO(DataSource ds)
	{
		this.jdbcTemplate = new NamedParameterJdbcTemplate(ds);
		 
	}
	
	public List<Bici> getVeicolo() //a differenza del primo non carico a mano ma tramite template
	{
		return jdbcTemplate.query("select * from bici",BeanPropertyRowMapper.newInstance(Bici.class));
	}
	public List<Bici> getMacchinaById(Integer id) //a differenza del primo non carico a mano ma tramite template
	{
		SqlParameterSource para = new MapSqlParameterSource("id",id);
		String sql = "select * from bici where id_veicolo =:id";
		
		return jdbcTemplate.query(sql,para,BeanPropertyRowMapper.newInstance(Bici.class));
	}
	
	
	@Transactional (rollbackFor = Exception.class)
	public int addBici(SqlParameterSource para)
	{
		String sql = "insert into bici (id_veicolo,n_marce,id_sospensione,pieghevole) values (:id_veicolo,:n_marce,:id_sospensione,:pieghevole)";
		return jdbcTemplate.update(sql, para);
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
