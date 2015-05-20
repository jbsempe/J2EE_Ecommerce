package epsi.main;

import epsi.dao.PlatHome;
import epsi.model.Plat;


public class Main {
	
	public static void main (String[] args){
				
		try{		
			PlatHome platDao = new PlatHome();
		
			for(Plat plat: platDao.find()){
				System.out.println(plat);
			}
			
		}
		catch(Exception e){
			System.err.println("an error has occured: " + e.getMessage());
			e.printStackTrace();	
		}
	}

}