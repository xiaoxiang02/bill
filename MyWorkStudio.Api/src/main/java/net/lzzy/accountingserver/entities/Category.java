package net.lzzy.accountingserver.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author hp
 */
@Getter
@Setter
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String categoryName;

    @OneToMany(mappedBy = "category")
    private List<Bill> bills = new ArrayList<>();

    @JsonManagedReference
    public List<Bill> getBills() {
        return bills;
    }
}
