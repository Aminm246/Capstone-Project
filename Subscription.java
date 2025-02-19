package edu.metro.iteration2;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
