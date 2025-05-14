package com.example.polyinformatiquecommon;

import com.example.polyinformatiquecommon.blog.Comment;
import com.example.polyinformatiquecommon.blog.Item;
import com.example.polyinformatiquecommon.ecommerce.Orderecommerce;
import jakarta.persistence.*;
import lombok.*;

import org.hibernate.annotations.GenericGenerator;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Customer {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    // Champs e-commerce
    private String firstname;
    private String lastname;
    private String shippingAddress;
    private String billingAddress;

    // Champs blog
    private String username;
    private String password;
    private String name; //
    private String address;

    private String email;
    private String phone;

    // Relations e-commerce
    @OneToMany(mappedBy = "customer")
    private List<Orderecommerce> orders;

    // Relations blog
    @OneToMany
    private List<Comment> commentList;

    @OneToMany
    @JoinColumn(name = "utilisateur")
    private List<Item> items;
}