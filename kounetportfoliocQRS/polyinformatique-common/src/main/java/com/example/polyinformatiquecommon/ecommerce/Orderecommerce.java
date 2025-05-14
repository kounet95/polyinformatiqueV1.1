package com.example.polyinformatiquecommon.ecommerce;

import jakarta.persistence.*;
import lombok.*;
import com.example.polyinformatiquecommon.Customer;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "common_orderecommerce")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Orderecommerce {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    private LocalDateTime createdAt;
    private String orderStatus;
    private String paymentMethod;
    private double total;
    private String barcode;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(mappedBy = "orderecommerce", cascade = CascadeType.ALL)
    private List<OrderLine> lines;

    @OneToOne(mappedBy = "orderecommerce", cascade = CascadeType.ALL)
    private Invoice invoice;

    @OneToOne(mappedBy = "orderecommerce", cascade = CascadeType.ALL)
    private Shipping shipping;
}