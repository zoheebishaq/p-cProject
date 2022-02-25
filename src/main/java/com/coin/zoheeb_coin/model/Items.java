package com.coin.zoheeb_coin.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "ITEMS")
public class Items {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private long id;
    @Basic
    @Column(name = "NAME", nullable = false, length = 1000)
    private String name;
    @Basic
    @Column(name = "DESCRIPTION", nullable = false, length = 1000)
    private String description;
    @Basic
    @Column(name = "PRICE", nullable = false)
    private int price;
    @Basic
    // changer nullable
    @Column(name = "IMAGE", nullable = true, length = 250)
    private String image;
    @ManyToOne
    @JoinColumn(name = "CATEGORIES_ID")
    private Categories categories;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Items item = (Items) o;
        return id == item.id && price == item.price && name.equals(item.name) && description.equals(item.description) && image.equals(item.image);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, price, image);
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", image='" + image + '\'' +
                '}';
    }
}
