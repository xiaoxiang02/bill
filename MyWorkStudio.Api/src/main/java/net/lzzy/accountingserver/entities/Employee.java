package net.lzzy.accountingserver.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import net.lzzy.accountingserver.enums.GenderEnum;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * @author hp
 */
@Getter
@Setter
@Entity
public class Employee extends BaseEntity{

    private String employeeNumber;
    private String username;
    private String password;
    private String jobPosition;
    private GenderEnum gender;
    private String email;
    private String phone;
    private String identityCardCode;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(timezone="GMT+8",pattern = "yyyy-MM-dd")
    private Date birthDay;

    private String address;
    private String avatar;
    private String salt;

    @ManyToOne
    private WorkPosition workPosition;

    @JsonBackReference
    public WorkPosition getWorkPosition() {
        return workPosition;
    }
}
