package com.org.simplelab.database.entities.sql;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.org.simplelab.database.DBUtils;
import com.org.simplelab.database.entities.interfaces.UserCreated;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = DBUtils.RECIPE_TABLE_NAME)
@Table(name = DBUtils.RECIPE_TABLE_NAME)
public class Recipe extends BaseTable implements UserCreated {
    //Recipe which represents that no recipe was found. Replaces "null" return
    public static final Recipe NO_RECIPE = NO_RECIPE_GEN();

    private String name;

    @JsonIgnore
    @OneToOne(cascade = {CascadeType.PERSIST},
            fetch = FetchType.EAGER)
    @JoinColumn(name = "creator_id")
    private User creator;

    @ManyToOne(fetch = FetchType.EAGER,
               cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE},
               optional = false)
    private Equipment equipmentOne;
    @ManyToOne(fetch = FetchType.EAGER,
               cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE},
               optional = false)
    private Equipment equipmentTwo;

    //TODO: change this back to lazy evaluation after testing
    @ManyToOne( fetch = FetchType.EAGER,
                cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE},
                optional = false)
    private Equipment result;

    private int rationOne;
    private int rationTwo;

    @Override
    public String toString(){
        return  "Name: " + getName() + " \n" +
                "EQ1: " + equipmentOne.getName() + " Properties: " + equipmentOne.getProperties().toString() + " \n" +
                "EQ2: " + equipmentTwo.getName() + " Properties: " + equipmentTwo.getProperties().toString() + " \n" +
                "Result: " + result.getName() + " Properties: " + result.getProperties().toString();
    }


    private static Recipe NO_RECIPE_GEN(){
        Recipe r = new Recipe();
        r.setName("NO RECIPE");
        r.setId(-1);
        return r;
    }


}
