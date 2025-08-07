package com.exceptionsAndValidation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.exceptionsAndValidation.model.Brand;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long>
{

}
