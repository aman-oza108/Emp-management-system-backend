package com.aman.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aman.entity.Employee;

@Repository
public interface emprepository extends JpaRepository<Employee, Integer>{

}
