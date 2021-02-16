/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.utcluj.ssatr.catalog;

import java.sql.SQLException;

public class EleviService {
    
    private DBAccess db;

    public EleviService() throws ClassNotFoundException, SQLException {
        db = new DBAccess();
    }
  
    public synchronized String handleNewElev(String nume,String prenume, double nota) throws SQLException{
       
            Elevi e = new Elevi(nume, nota,prenume);
            db.insertElev(e);
            return "Noul Elev a fost introdus, nume: "+e.getNUME()+ " prenume: "+ e.getPRENUME()+" nota: "+ e.getNOTA();
       
        
    }
    public synchronized String deleteEntity(String name) throws SQLException{
           Elevi c = db.findByName(name);
           if(c!=null){
           db.deleteByNume(name);
           return "Elev Sters";
           }
           else{
           return " Elevul "+name+" nu exista in baza de date!";
           }
        
    }
   
    public static void main(String[] args) throws Exception {
        EleviService e = new EleviService();
        System.out.println(e.handleNewElev("BODEA","Daniela",5));
    }
    
}