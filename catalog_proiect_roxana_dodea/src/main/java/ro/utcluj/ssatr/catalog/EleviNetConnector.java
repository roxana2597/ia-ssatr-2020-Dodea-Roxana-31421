/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.utcluj.ssatr.catalog;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;


public class EleviNetConnector {
    
   EleviService eService;
    public EleviNetConnector() throws ClassNotFoundException, SQLException {
        eService = new EleviService();
    }
    
    public void startServer(){
        
        try{
        
        ServerSocket ss =new ServerSocket(4050);
        
        while(true){
            System.out.println("Astept conexiune de la client...");
            Socket s = ss.accept(); //metoda blocanta
            System.out.println("Clientul s-a conectat!");
            //...... 
            BufferedReader fluxIn = new BufferedReader(new InputStreamReader(s.getInputStream()));
            PrintWriter fluxOut = new PrintWriter(new OutputStreamWriter(s.getOutputStream()),true);
            //......
 
            String resp=fluxIn.readLine();
            System.out.println(resp);
            
            if("new".equals(resp))
            {
                String nume = fluxIn.readLine();
                String prenume = fluxIn.readLine();
                double nota = Double.parseDouble(fluxIn.readLine());
                String result = eService.handleNewElev(nume,prenume,nota);
                fluxOut.println(result);
            }
            else if("delete".equals(resp))
            {
                String Nume2 = fluxIn.readLine();
                String result2 =eService.deleteEntity(Nume2);
                fluxOut.println(result2);
            }
            s.close();
        }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        EleviNetConnector netCon = new EleviNetConnector();
        netCon.startServer();
    }
}