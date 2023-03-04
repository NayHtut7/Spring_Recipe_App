package com.hostmdy.recipe.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.hostmdy.recipe.domain.UnitsOfMeasurement;

public interface UomRepository extends CrudRepository<UnitsOfMeasurement, Long> {
	
	Optional<UnitsOfMeasurement> findByUom(String uom);//query method
	
}
