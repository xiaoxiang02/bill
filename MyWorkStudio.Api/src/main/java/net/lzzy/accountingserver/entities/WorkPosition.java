package net.lzzy.accountingserver.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author hp
 */
@Getter
@Setter
@Entity
public class WorkPosition extends BaseEntity{

    private String workName;
    private int staffs;
    private String description;

    @OneToMany(mappedBy = "workPosition")
    private List<Employee> employees = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()){
            return false;
        }
        WorkPosition workPosition = (WorkPosition) o;
        return id == workPosition.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }


    @JsonManagedReference
    public List<Employee> getEmployees() {
        return employees;
    }
}
