/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.utcluj.ssatr.catalog;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBAccess {
    private Connection conn;
    
    public DBAccess() throws ClassNotFoundException, SQLException {
         Class.forName("org.apache.derby.jdbc.ClientDriver");
         conn = DriverManager.getConnection("jdbc:derby://localhost/ELEVI;create=false","APP","APP");
    }
   
    public void insertElev(Elevi e) throws SQLException{
        Statement s = conn.createStatement();
        PreparedStatement pstmt = conn.prepareStatement("INSERT INTO ELEVI " 
    + "(NUME, PRENUME, NOTA) VALUES (?, ?, ?)");
        pstmt.setString(1, e.getNUME());
        pstmt.setString(2, e.getPRENUME());
        pstmt.setDouble(3, e.getNOTA());
        pstmt.executeUpdate();
            
        s.close();
    }
     public Elevi findByName(String name) throws SQLException{
        Statement s = conn.createStatement();
        ResultSet rs = s.executeQuery("SELECT * FROM ELEVI WHERE NUME='"+name+"'");
        if(rs.next()){
            return new Elevi(rs.getString("NUME"), rs.getDouble("NOTA"), rs.getString("PRENUME"));
        }else{
            return null;
        }           
    }
    public void deleteByNume(String nume) throws SQLException{
        Statement s = conn.createStatement();
        s.executeUpdate("DELETE FROM ELEVI WHERE NUME='"+nume+"'");   
        s.close();
    }
    
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        DBAccess db = new DBAccess();
        Elevi e = new Elevi("DODEA", 10,"ROXANA");
        db.insertElev(e);
        Elevi result = db.findByName("DODEA");
        System.out.println(result);
        if(result!=null){
            db.deleteByNume(result.getNUME());
            System.out.println("ELEV STERS!");
        }else{
            System.out.println("ELEV NEGASIT!");
        }
        
        }
        
    }
