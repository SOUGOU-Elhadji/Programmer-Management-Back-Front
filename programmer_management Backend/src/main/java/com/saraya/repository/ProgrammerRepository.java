package com.saraya.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.saraya.model.Programmer;

@Repository
public interface ProgrammerRepository extends JpaRepository<Programmer, Long> {

}
