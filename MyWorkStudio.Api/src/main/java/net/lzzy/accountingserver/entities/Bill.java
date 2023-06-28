package net.lzzy.accountingserver.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 * @author hp
 */
@Getter
@Setter
@Entity
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Double money;
    private String notes;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;

    @ManyToOne
    private Type type;
    @ManyToOne
    private Category category;

    public Bill() {
        createTime = new Date();
        updateTime = createTime;
    }

    @JsonBackReference
    public Type getType() {
        return type;
    }

    @JsonBackReference
    public Category getCategory() {
        return category;
    }
}
