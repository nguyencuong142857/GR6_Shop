/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import Model.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *Select * from Product order by p_id OFFSET " + (i - 1) * 8 + "  Row Fetch next 8 ROWS ONLY
 * @author Minh
 */
public class DAO extends DBContext {
    public List<User> getAllU(String param){
        List<User> list=new ArrayList<>();
        int i=Integer.parseInt(param);
    
          
        try {
            
            String com="Select * from users order by user_id Limit "+(i-1)*8 +" ,8";
            PreparedStatement st=connection.prepareStatement(com);
            ResultSet rs=st.executeQuery();
            while(rs.next()){
                User u=new User(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getDouble(9));
                list.add(u);
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    public int getPage(){
        try {
            int c=0;
            String com="Select count(user_id) from users";
            PreparedStatement st=connection.prepareStatement(com);
            ResultSet rs=st.executeQuery();
            while(rs.next()){
                c=rs.getInt(1);
            }
            return c;
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
}
