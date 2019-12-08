package com.org.simplelab.database.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.org.simplelab.controllers.BaseController;
import com.org.simplelab.database.DBUtils;
import com.org.simplelab.database.entities.interfaces.UserCreated;
import com.org.simplelab.restcontrollers.dto.DTO;
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
               cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE})
    private Equipment equipmentOne;

    @ManyToOne(fetch = FetchType.EAGER,
               cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE})
    private Equipment equipmentTwo;

    //TODO: change this back to lazy evaluation after testing
    @ManyToOne(fetch = FetchType.EAGER,
                cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE})
    private Equipment result;


    public boolean exists(){
        return getId() != -1;
    }

    private static Recipe NO_RECIPE_GEN(){
        Recipe r = new Recipe();
        r.setName("NO RECIPE");
        r.setId(-1);
        return r;
    }


}
