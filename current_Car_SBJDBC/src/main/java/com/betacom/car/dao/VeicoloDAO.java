package com.betacom.car.dao;

import java.util.List;
import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.betacom.car.models.Veicolo;

@Repository
public class VeicoloDAO {
	
	private final NamedParameterJdbcTemplate jdbcTemplate; //define template con nomi dei parametri
	public VeicoloDAO(DataSource ds)
	{
		this.jdbcTemplate = new NamedParameterJdbcTemplate(ds);
		 
	}
	
	public Object getElem(String qry,SqlParameterSource el)
	{
		System.out.println("ciao");
		return jdbcTemplate.query(qry,el,BeanPropertyRowMapper.newInstance(Object.class));
	}
	
	public List<Veicolo> getVeicolo() //a differenza del primo non carico a mano ma tramite template
	{
		return jdbcTemplate.query("select * from veicolo",BeanPropertyRowMapper.newInstance(Veicolo.class));
	}
	public List<Veicolo> getVeicoloById(Integer id) //a differenza del primo non carico a mano ma tramite template
	{
		SqlParameterSource para = new MapSqlParameterSource("id",id);
		String sql = "select * from veicolo where id_veicolo =:id";
		
		return jdbcTemplate.query(sql,para,BeanPropertyRowMapper.newInstance(Veicolo.class));
	}
	
	
	@Transactional (rollbackFor = Exception.class)
	public int addVeicolo(SqlParameterSource para)
	{
		String sql = "insert into veicolo (tipo_veicolo,numero_ruote,id_alimentazione,id_categoria,id_colore,id_marca,anno_prod,modello) values (:tipo_veicolo,:numero_ruote,:id_alimentazione,:id_categoria,:id_colore,:id_marca,:anno_prod,:modello)";
		
		KeyHolder kh = new GeneratedKeyHolder();
		jdbcTemplate.update(sql, para,kh,new String[]{"id_veicolo"});
		return kh.getKey().intValue();
		
		
	}
	
	
	
	public int insert(Object[] paramsV, Object[] paramsS) throws Exception{
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
