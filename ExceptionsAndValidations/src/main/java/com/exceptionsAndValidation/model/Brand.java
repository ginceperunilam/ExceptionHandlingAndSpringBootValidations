package com.exceptionsAndValidation.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "BRAND", uniqueConstraints=@UniqueConstraint(columnNames={"BRAND_NAME","BRAND_PRIORITY"}))
public class Brand
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="BRAND_ID")
	private Long brandId;
	@Column(name="BRAND_CODE", unique = true, nullable=false)
	private Long brandCode;
	@Column(name="BRAND_NAME", length=100)
	private String brandName;
	@Column(name="BRAND_PRIORITY", nullable=false)
	private Long brandPriority;
}
