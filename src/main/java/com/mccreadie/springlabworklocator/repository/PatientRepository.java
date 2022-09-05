package com.mccreadie.springlabworklocator.repository;


import com.mccreadie.springlabworklocator.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {

    //Custom query
    @Query(value = "select * from patient p where first_name like %:keyword% or last_name like %:keyword%", nativeQuery = true)
    List<Patient> findByKeyword(@Param("keyword") String keyword);

    @Query(value = "select * from patient p where first_name is %:keyword%", nativeQuery = true)
    List<Patient> findByFirstName(@Param("keyword") String keyword);


}
