package edu.metro.subscriptionshepard;

public class Subscription {

    private int id;
    private String name;
    private double price;
    private String duration;
    private String paymentFrequency;

    // Without id
    public Subscription(String name, double price, String duration, String paymentFrequency) {
        this.name = name;
        this.price = price;
        this.duration = duration;
        this.paymentFrequency = paymentFrequency;
    }

    // With id
    public Subscription(int id, String name, double price, String duration, String paymentFrequency) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.duration = duration;
        this.paymentFrequency = paymentFrequency;
    }

    //No arg
    public Subscription(){ }

    //Getters
    public int getId() {
        return id;
    }
    public String getName(){
        return name;
    }
    public double getPrice(){
        return price;
    }
    public String getDuration(){
        return duration;
    }
    public String getPaymentFrequency(){
        return paymentFrequency;
    }

    //Setters
    public void setId(int id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public void setDuration(String duration) {
        this.duration = duration;
    }
    public void setPaymentFrequency(String paymentFrequency) {
        this.paymentFrequency = paymentFrequency;
    }

}
