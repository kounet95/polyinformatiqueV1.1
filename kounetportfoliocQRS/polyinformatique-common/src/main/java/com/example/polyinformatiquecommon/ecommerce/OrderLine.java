package com.example.polyinformatiquecommon.ecommerce;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

@Entity(name = "common_orderlineecommerce")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class OrderLine {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    private int qty;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Orderecommerce orderecommerce;

    @ManyToOne
    @JoinColumn(name = "product_size_id")
    private ProductSize productSize;
}
