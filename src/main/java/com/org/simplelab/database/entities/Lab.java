package com.org.simplelab.database.entities;

import com.org.simplelab.database.DBUtils;
import lombok.Data;

import javax.persistence.*;
import java.util.*;

@Data
@Entity(name = DBUtils.LAB_TABLE_NAME)
@Table(name = DBUtils.LAB_TABLE_NAME)
public class Lab extends BaseTable{

    private String name;
    private String description;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "creator_id")
    private User creator;

    @OneToMany(cascade = {CascadeType.PERSIST},
               fetch = FetchType.LAZY)
    private Set<Equipment> equipments;

    public Lab(){
        this.equipments = new HashSet<>();
    }
}
