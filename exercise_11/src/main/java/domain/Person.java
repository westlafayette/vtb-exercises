package domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
public class Person {
    @Id
    @GeneratedValue
    private UUID id;

    private String name;

    @ManyToMany
    @JoinTable(
            name = "person_product",
            joinColumns = @JoinColumn(name = "person_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private Set<Product> products;

    @Override
    public String toString() {
        return "Person{id: %s, name: %s}".formatted(id, name);
    }
}
