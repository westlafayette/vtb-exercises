package domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue
    private UUID id;

    private String name;

    private Long price;

    @ManyToMany
    @JoinTable(
            name = "person_product",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "person_id")
    )
    private Set<Person> persons;

    @Override
    public String toString() {
        return "Product{id: %s, name: %s, price: %s}".formatted(id, name, price);
    }
}
