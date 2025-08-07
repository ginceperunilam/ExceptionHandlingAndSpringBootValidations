package com.exceptionsAndValidation.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="PRODUCT", uniqueConstraints=@UniqueConstraint(columnNames= {"PRODUCT_CODE","BRAND_ID"}))
public class Product
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="PRODUCT_ID")
	private Long prdId;
	@Column(name="PRODUCT_CODE", unique = true, nullable=false)
	@NotNull(message="Product Code is Mandatory")
	private Long prdCode;
	@Column(name="PRODUCT_NAME", length=100)
	@Size(min=2, max=20)
	private String prdName;
	@Column(name="PRODUCT_ELIGIBLE_DICOUNT")
	private Boolean pdtEligibleDiscount;
	@Column(name="PRODUCT_PRICE")
	@Min(value =10, message="Minimum Value is 10")
	@Max(value =100000, message="Maximum Value is 100000")
	private Double pdtPrice;
	@ManyToOne
	@JoinColumn(name="BRAND_ID", nullable=false)
	private Brand brand;

}
