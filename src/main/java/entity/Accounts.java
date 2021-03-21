package entity;

import lombok.Data;

@Data
public class Accounts {
    private long id;
    private long client_id;
    private String number;
    private double value;
}