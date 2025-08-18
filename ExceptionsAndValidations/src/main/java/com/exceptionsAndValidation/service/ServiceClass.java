package com.exceptionsAndValidation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.exceptionsAndValidation.InvalidPriceException;
import com.exceptionsAndValidation.model.Brand;
import com.exceptionsAndValidation.model.Product;
import com.exceptionsAndValidation.model.ReturnResponse;
import com.exceptionsAndValidation.repository.BrandRepository;
import com.exceptionsAndValidation.repository.PdtRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolationException;

@Service
public class ServiceClass
{

	/**setter injection**/
	private BrandRepository brandRepository;
	@Autowired
	public void setBrandRepository(BrandRepository brandRepository)
	{
		this.brandRepository = brandRepository;
	}
	
	/**field injectopn**/
	@Autowired
	PdtRepository pdtRepository;
	
	@Transactional
	public ReturnResponse saveBrand(Brand brand)
	{
		ReturnResponse response = new ReturnResponse();
		try
		{
			Brand savedBrand = brandRepository.save(brand);

			response.setEntityResponse("Brand Saved success...",savedBrand);
		} 
		catch(NullPointerException ne)
		{
			response.setExceptionDetails("Null value not allowed",ne);
		}
		catch(ConstraintViolationException cve)
		{
			response.setExceptionDetails("Constraint Violated",cve);
		}
		catch(DataIntegrityViolationException die)
		{
			response.setExceptionDetails(die.getCause().getMessage(),die);
		}
		catch (Exception e)
		{
			response.setExceptionDetails("Some exception occur:",e);
		}
		return response;
	}

	public Product saveProduct(Product product)
	{
		if(product.getPdtPrice() == null || product.getPdtPrice() <= 0d)
		{
			throw new  InvalidPriceException("Please enter a valid price");
		}
		return pdtRepository.save(product);

	}

}
