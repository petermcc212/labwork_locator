package com.mccreadie.springlabworklocator.repository;


import com.mccreadie.springlabworklocator.model.Patient;
import com.mccreadie.springlabworklocator.model.Prosthesis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ProsthesisRepository extends JpaRepository<Prosthesis, Integer> {

// enum values for status: 0=sent, 1=returned, 2=completed, 3=requires_attention, 4=void

    //Lab work due today
//    @Query(value = "select * from prosthesis p where p.dateDue = CURRENT_DATE ", nativeQuery = true)
    @Query("select p from Prosthesis p where p.dateDue= :todaysDate and not (p.status = 1 or p.status = 2 or p.status = 4)")
    List<Prosthesis> findWorkDueToday(@Param("todaysDate") LocalDate todaysDate);

    // Lab work due tomorrow
    @Query("select p from Prosthesis p where p.dateDue= :tomorrowsDate and not (p.status = 1 or p.status = 2 or p.status = 4)")
    List<Prosthesis> findWorkDueTomorrow(@Param("tomorrowsDate") LocalDate tomorrowsDate);


    // lab work overdue
    @Query("select p from Prosthesis p where p.dateDue< :todaysDate and not (p.status = 1 or p.status = 2 or p.status = 4)")
    List<Prosthesis> findOverdueWork(@Param("todaysDate") LocalDate todaysDate);




    @Query(value = "select * from patient p where p.firstName like %:keyword% or p.lastName like %:keyword%", nativeQuery = true)
    List<Patient> findByKeyword(@Param("keyword") String keyword);

}
