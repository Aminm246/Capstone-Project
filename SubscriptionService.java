package edu.metro.subscriptionshepard;

import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class SubscriptionService {

    private Connection con = null;
    private String username = "root";
    private String password = "ics311";
    private String dbUrl = "jdbc:mysql://localhost:3306/subshepard";

    private SubscriptionService(){
        try{
            con = DriverManager.getConnection(dbUrl,username,password);
        } catch (SQLException e) {
            Logger.getLogger(SubscriptionService.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void create(Subscription sub) {
        try {
            String sql = "insert into subscriptions (name, price, duration, paymentFreq) values (?, ?, ?, ?)";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, sub.getName());
            statement.setDouble(2, sub.getPrice());
            statement.setString(3, sub.getDuration());
            statement.setString(4, sub.getPaymentFrequency());
            statement.execute();
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(SubscriptionService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void update(Subscription sub){
        try {
            Statement statement = con.createStatement();
            String sql = "update subscriptions set name =?, price =?, duration =?, paymentFreq =? where id =?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, sub.getName());
            stmt.setDouble(2, sub.getPrice());
            stmt.setString(3, sub.getDuration());
            stmt.setString(4, sub.getPaymentFrequency());
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException ex){
            Logger.getLogger(SubscriptionService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void delete(int id){
        try{
            Statement statement = con.createStatement();
            String sql = "delete from subscriptions where id =" + id;
            statement.execute(sql);
            statement.close();
        } catch (SQLException ex){
            Logger.getLogger(SubscriptionService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Subscription retrieve(int id){
        Subscription sub = null;
        try {
            Statement statement = con.createStatement();
            String sql = "select name, price, duration, paymentFreq from subscriptions where id=" + id;
            ResultSet results = statement.executeQuery(sql);
            while (results.next()){
                String name = results.getString("name");
                Double price = results.getDouble("price");
                String duration = results.getString("duration");
                String paymentFreq = results.getString("paymentFreq");
                sub = new Subscription(id, name, price, duration, paymentFreq);
            }
            results.close();
            statement.close();
        } catch (SQLException ex){
            Logger.getLogger(SubscriptionService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sub;
    }

    public List<Subscription> retrieveAll(){
        ArrayList<Subscription> subscriptions = new ArrayList<>();
        try {
            Statement statement = con.createStatement();
            String sql = "select id, name, price, duration, paymentFreq from subscriptions";
            ResultSet results = statement.executeQuery(sql);
            while (results.next()){
                int id = results.getInt("id");
                String name = results.getString("name");
                Double price = results.getDouble("price");
                String duration = results.getString("duration");
                String paymentFreq = results.getString("paymentFreq");
                subscriptions.add(new Subscription(id, name, price, duration, paymentFreq));
            }
        } catch (SQLException ex){
            Logger.getLogger(SubscriptionService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return subscriptions;
    }
}


