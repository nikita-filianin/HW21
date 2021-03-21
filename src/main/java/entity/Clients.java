package entity;

import lombok.Data;

@Data
public class Clients {
    private long id;
    private String name;
    private String email;
    private Long phone;
    private String about;
}