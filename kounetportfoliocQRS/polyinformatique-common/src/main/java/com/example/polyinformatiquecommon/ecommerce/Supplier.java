package com.example.polyinformatiquecommon.ecommerce;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.*;

import org.hibernate.annotations.GenericGenerator;

import java.util.List;

@Entity(name = "common_supplier")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Supplier {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    private String fullname;
    private String city;
    private String email;
    private String personToContact;

    @OneToMany(mappedBy = "supplier")
    private List<Stock> stocks;

    @OneToMany(mappedBy = "supplier")
    private List<Purchase > purchases;
}

