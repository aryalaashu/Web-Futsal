package com.system.futsal_management_system.Service;

import com.system.futsal_management_system.Pojo.FutsalPojo;
import com.system.futsal_management_system.entity.Futsal;

import java.io.IOException;
import java.util.List;

public interface FutsalService {
    FutsalPojo savefutsal(FutsalPojo futsalPojo) throws IOException;

    Futsal fetchById(Integer id);

    List<Futsal> fetchAll();

    void deleteById(Integer id);
}
