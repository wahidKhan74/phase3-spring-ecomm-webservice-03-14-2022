package com.simplilearn.webservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.simplilearn.webservice.entity.EProduct;

public interface ProductRepository extends JpaRepository<EProduct, Long>{

}
