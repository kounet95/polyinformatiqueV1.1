package org.example.ecpolyquery.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "eco_product")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Product {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    private String name;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime closedAt;
    private boolean isActive;

    @ManyToOne
    @JoinColumn(name = "subcategory_id")
    private Subcategory subcategory;

    @ManyToOne
    @JoinColumn(name = "social_group_id")
    private SocialGroup socialGroup;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<ProductSize> sizes;
}

