package com.org.simplelab;

import com.org.simplelab.database.entities.Equipment;
import com.org.simplelab.database.entities.EquipmentProperty;
import com.org.simplelab.database.services.DBService;
import org.junit.jupiter.api.Test;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
public class LabEquipmentTests extends SpringTestConfig {

    @Test
    void testInsertEquipment() throws Exception{
        Equipment e = TestUtils.createJunkEquipment();
        equipmentDB.insert(e);

        Iterable<Equipment> found = equipmentDB.getRepository().findAll();
        boolean isPresent = false;
        for (Equipment foundEq: found){
            if (foundEq.getName().equals(e.getName())){
                isPresent = true;
                break;
            }
        }
        assertTrue(isPresent);
    }

    @Test
    void testInsertEqWithProperties() throws Exception{
        int NUM_PROPERTIES = 10;
        Equipment e = TestUtils.createJunkEquipmentWithProperties(NUM_PROPERTIES);
        equipmentDB.insert(e);

        List<Equipment> foundList = equipmentDB.getRepository().findByName(e.getName());
        Equipment found = foundList.get(0);

        DBService.EntitySetManager<EquipmentProperty, Equipment> set = equipmentDB.getPropertiesOfEquipment(found.getId());
        System.out.println("Set properties: " + set.getEntitySet().toString());
        assertEquals(NUM_PROPERTIES, set.getEntitySet().size());
    }

    @Test
    void testDeleteEqWithProperties() throws Exception{
        int NUM_PROPERTIES = 10;
        Equipment e = TestUtils.createJunkEquipmentWithProperties(NUM_PROPERTIES);
        equipmentDB.insert(e);

        List<Equipment> foundList = equipmentDB.getRepository().findByName(e.getName());
        Equipment found = foundList.get(0);

        equipmentDB.deleteById(found.getId());
        foundList = equipmentDB.getRepository().findByName(e.getName());

        assertEquals(0, foundList.size());
    }

    @Test
    void testUpdateEqAndProperties() throws Exception{
        int NUM_PROPERTIES = 20;
        Equipment e = TestUtils.createJunkEquipmentWithProperties(NUM_PROPERTIES);
        equipmentDB.insert(e);

        List<Equipment> foundList = equipmentDB.getRepository().findByName(e.getName());
        Equipment found = foundList.get(0);

        String UPDATED_NAME = found.getName() + "UPDATED";
        String UPDATED_VAL = "UPDATED_VAL";
        DBService.EntitySetManager<EquipmentProperty, Equipment> set = equipmentDB.getPropertiesOfEquipment(found.getId());
        for (EquipmentProperty ep: set.getEntitySet()){
            ep.setProperty_value(UPDATED_VAL);
        }
        set.getFullEntity().setName(UPDATED_NAME);
        equipmentDB.update(set.getFullEntity());

        foundList = equipmentDB.getRepository().findByName(e.getName());
        found = foundList.get(0);
        set = equipmentDB.getPropertiesOfEquipment(found.getId());
        assertEquals(UPDATED_NAME, found.getName());
        for (EquipmentProperty ep: set.getEntitySet()){
            assertEquals(UPDATED_VAL, ep.getProperty_value());
        }

    }



}
