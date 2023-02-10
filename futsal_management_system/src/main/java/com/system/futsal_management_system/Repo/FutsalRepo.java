package com.system.futsal_management_system.Repo;

import com.system.futsal_management_system.entity.Futsal;
import com.system.futsal_management_system.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FutsalRepo extends JpaRepository < Futsal, Integer>{
}
