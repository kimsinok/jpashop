package com.example.jpashop.domain;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// Value Object
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Address {

    private String city;
    private String street;
    private String zipcode;

}
