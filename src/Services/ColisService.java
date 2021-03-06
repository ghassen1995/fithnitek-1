/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import DataBase.MyDB;
import Entities.Colis;
import IServices.IColisService;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Hamza Mlaouhi
 */
public class ColisService implements IColisService{
     private Connection con;
    
    public ColisService(){
    con = MyDB.getInstance().getConnection();}

    @Override
    public void AjouterColis(Colis c) {
         try {
            PreparedStatement pste = con.prepareStatement("INSERT INTO colis VALUES (?,?,?,?,?,?,?);");
            pste.setInt(1,c.getId());
            pste.setString(2,c.getDepart());
            pste.setString(3,c.getDestination());
            pste.setString(4,c.getDate_limit());
            pste.setString(5,c.getLabel()); 
            pste.setString(6,c.getDescription()); 
            pste.setInt(7,c.getIdUtilisateur()); 
            pste.executeUpdate();
        } catch (SQLException ex) {
           ex.getMessage();
        } 
    }

    @Override
    public void ModifierColis(Colis c) {
        try {
            String sql = "UPDATE colis SET depart=? ,destination=? ,date_limit=? ,label=? ,description=?  WHERE id=?";
            PreparedStatement pste = con.prepareStatement(sql);
            pste.setString(1,c.getDepart());
            pste.setString(2,c.getDestination());
            pste.setString(3,c.getDate_limit());
            pste.setString(4,c.getLabel());
            pste.setString(5,c.getDescription());
            pste.setInt(6,c.getId());
            pste.executeUpdate();
            } 
        catch (SQLException ex) {
            ex.getMessage();
        }  
    }

    @Override
    public void SupprimerColis(Colis c) {
        try{
      PreparedStatement ste = con.prepareStatement("DELETE FROM colis WHERE id=? ;");
      ste.setInt(1,c.getId());
      ste.executeUpdate();
        }catch(SQLException ex){
            ex.getMessage();
        }
        }

    @Override
    public List<Colis> AfficherColis() {
      List<Colis> listColis = new ArrayList<Colis>();
        try {
            String sql = "Select * From colis ";
            Statement ste = con.createStatement();
            ResultSet res = ste.executeQuery(sql);
            while(res.next())
            {
                Colis c = new Colis(res.getInt("id"),res.getString("depart"),res.getString("destination"),res.getString("date_limit"),res.getString("label"),res.getString("description"),res.getInt("idUtilisateur") );
                listColis.add(c);
            }
     }catch(SQLException ex){
         ex.getMessage(); 
     }
        return listColis;
    }
    }
