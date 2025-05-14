package com.example.polyinformatiquecommon.ecommerce;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

@Entity(name = "common_stock")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Stock {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    private double purchasePrice;
    private double promoPrice;
    private double salePrice;
    private int stockAvailable;

    @ManyToOne
    @JoinColumn(name = "product_size_id")
    private ProductSize productSize;

    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;
}

