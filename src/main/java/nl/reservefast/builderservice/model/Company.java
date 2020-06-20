package nl.reservefast.builderservice.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
public class Company {
    private UUID id;

    private Date createdAt;

    private Date updatedAt;

    private UUID userId;

    private String name;

    private String address;

    private String zipcode;

    private String city;

    private String country;

    private String email;

    private String mollieApi;

    public Company() {}

    public Company(UUID id, UUID userId, String name, String address, String zipcode, String city, String country, String email) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.address= address;
        this.zipcode = zipcode;
        this.city = city;
        this.country = country;
        this.email = email;
    }
}
