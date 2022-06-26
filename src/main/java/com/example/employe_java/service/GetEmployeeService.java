package com.example.employe_java.service;


import com.example.employe_java.model.ModelEmploye;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface GetEmployeeService extends JpaRepository<ModelEmploye, Long> {
   // List<ModelEmploye> getDataEmployee();
   List<ModelEmploye> findByUsername(String username);
    }
