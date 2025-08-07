package com.exceptionsAndValidation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.exceptionsAndValidation.model.Product;

@Repository
public interface PdtRepository extends JpaRepository<Product, Long>
{

}
