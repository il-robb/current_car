package com.betacom.carjdbc.process;

import java.util.List;

import com.betacom.carjdbc.dao.BiciDAO;
import com.betacom.carjdbc.dao.MacchinaDAO;
import com.betacom.carjdbc.dao.MotoDAO;
import com.betacom.carjdbc.dao.VeicoloDAO;
import com.betacom.carjdbc.exception.AcademyException;
import com.betacom.carjdbc.singletone.SQLConfiguration;


public class StartVeicolo {
	
	private VeicoloDAO daoV = new VeicoloDAO();
	private MotoDAO daoMo = new MotoDAO();
	private MacchinaDAO daoMa = new MacchinaDAO();
	private BiciDAO daoB = new BiciDAO();
	
			
	public boolean execute(List<String> params) {
		System.out.println("Begin StartVeicolo");
		boolean rc = true;
		try {
			SQLConfiguration.getInstance().getConnection();
			
			for(String inp:params) {
				String[] oper = inp.split(":");
				Object[] params1 = oper[1].split(",");
				switch (oper[0]) {
				case "add": {

					Object[] paramsV = new Object[8];
					Object[] paramsM = new Object[4];
					int k=1;

					for(int i = 0;i<8;i++)
					{
						paramsV[i]= params1[i];
					}
					
					switch (paramsV[0].toString()){
					case "moto": {
						
						for(int i = 8;i<10;i++)
						{
							paramsM[k]= params1[i];
							k++;
						}
						
						break;
					}
					case "macchina": {
						
						for(int i = 8;i<11;i++)
						{
							paramsM[k]= params1[i];
							k++;
						}
						
						break;
					}
					case "bici": {
					
					for(int i = 8;i<11;i++)
					{
						paramsM[k]= params1[i];
						k++;
					}
						break;
					}
					default:
						throw new IllegalArgumentException("Unexpected value: " + paramsV[0].toString());
					}
					daoV.insert(paramsV, paramsM);
					
				}
				case "update": {
					
					Object[] paramsV = new Object[8];
					Object[] paramsM = new Object[4];
					int k=1;

					for(int i = 0;i<8;i++)
					{
						paramsV[i]= params1[i];
					}
					
					switch (paramsV[0].toString()){
					case "moto": {
						
						for(int i = 8;i<10;i++)
						{
							paramsM[k]= params1[i];
							k++;
						}
						
						break;
					}
					case "macchina": {
						
						for(int i = 8;i<11;i++)
						{
							paramsM[k]= params1[i];
							k++;
						}
						
						break;
					}
					case "bici": {
					
					for(int i = 8;i<11;i++)
					{
						paramsM[k]= params1[i];
						k++;
					}
						break;
					}
					default:
						throw new IllegalArgumentException("Unexpected value: " + paramsV[0].toString());
					}
					daoV.update(paramsV, paramsM);
					
				}
				case "delete": {
					switch (params1[0].toString()) {
					case "moto": {
						daoMo.delete(new Object[] {params1[1]});
					}
					case "macchina": {
						daoMa.delete(new Object[] {params1[1]});
					}
					case "bici": {
						daoB.delete(new Object[] {params1[1]});
					}
					default:
						throw new IllegalArgumentException("Unexpected veicolo: " + params1[1]);
					}
					
				}
				case "list": {
					
					System.out.println(daoV.findAll());
				}
					
				default:
					throw new IllegalArgumentException("Unexpected value: " + oper[0]);
				}
				// scan input and dispatch per operation
			
			
			}
		} catch (Exception e) {
			System.out.println ("Error found:" + e.getMessage());
			rc = false;
			
		}
		try {
			SQLConfiguration.getInstance().closeConnection();
		} catch (AcademyException e) {
			System.out.println("Error in close connection:" + e.getMessage());
		}
		System.out.println("Connection is closed....");
		return rc;
	}
		
		
		
		
		
		
}