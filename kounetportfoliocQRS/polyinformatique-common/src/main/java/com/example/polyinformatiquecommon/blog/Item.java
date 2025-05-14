package com.example.polyinformatiquecommon.blog;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import com.example.polyinformatiquecommon.Customer;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TYPE", length = 10)
@Entity(name = "common_item")
public abstract class Item {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    private String content;
    private String urlMedia;
    private String title;
    private LocalDate createdAt;

    @ManyToOne
    private Customer utilisateur;

    @OneToMany
    private List<Media> mediaList;

    @OneToMany
    private List<Comment> comments;

    @ManyToMany
    @JoinTable(
            name = "tag_items",
            joinColumns = @JoinColumn(name = "item_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private List<Tag> tags = new ArrayList<>();
}