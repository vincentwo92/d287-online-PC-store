package com.example.demo.domain;

import com.example.demo.validators.ValidDeletePart;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import org.springframework.validation.BindingResult;

/**
 *
 *
 *
 *
 */
@Setter
@Getter
@Entity
@ValidDeletePart
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="part_type",discriminatorType = DiscriminatorType.INTEGER)
@Table(name="Parts")
public abstract class Part implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;
    String name;
    @Min(value = 0, message = "price value must be positive")
    double price;
    int inv;
    @Min(value = 0)
    int minInv;
    @Max(value = 999)
    int maxInv;

    @ManyToMany
    @JoinTable(name="product_part", joinColumns = @JoinColumn(name="part_id"),
            inverseJoinColumns=@JoinColumn(name="product_id"))
    Set<Product> products= new HashSet<>();

    public Part() {
    }

    public Part(String name, double price, int inv) {
        this.name = name;
        this.price = price;
        this.inv = inv;
    }

    public Part(long id, String name, double price, int inv) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.inv = inv;
    }

    public String toString(){
        return this.name;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Part part = (Part) o;

        return id == part.id;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

    public boolean isValidInven() {
        return inv >= minInv && inv <= maxInv;
    }

    public boolean isValidInventory() {
        return inv >= minInv && inv <= maxInv;
    }

    public void validateInventory(BindingResult bindingResult) {
        if (inv < 0) {
            bindingResult.rejectValue("inv", "inventory.negative", "inventory cannot be negative");
        } else if (!isValidInventory()) {
            bindingResult.rejectValue("inv", "inventory.outOfRange", "inventory must be between " + minInv + " and " + maxInv);
        }
    }

}
