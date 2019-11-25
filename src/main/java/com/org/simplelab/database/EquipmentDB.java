package com.org.simplelab.database;

import com.org.simplelab.database.entities.Equipment;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Component
public class EquipmentDB extends DBService<Equipment> {

    @Override
    public boolean insert(Equipment toInsert) {
        return false;
    }
}
