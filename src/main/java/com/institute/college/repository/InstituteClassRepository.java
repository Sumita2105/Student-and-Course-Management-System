package com.institute.college.repository;

import com.institute.college.model.InstituteClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstituteClassRepository extends JpaRepository<InstituteClass, Integer> {

}
