package com.mccreadie.springlabworklocator.repository;

import com.mccreadie.springlabworklocator.model.LaboratoryProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LaboratoryProductRepository extends JpaRepository<LaboratoryProduct, Integer> {


}
