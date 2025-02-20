package edu.metro.subscriptionshepard;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Subscription {

    private int id;
    private String name;
    private double price;
    private String duration;
    private String paymentFrequency;

}
