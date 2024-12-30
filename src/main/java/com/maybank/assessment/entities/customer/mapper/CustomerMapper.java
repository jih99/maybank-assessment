package com.maybank.assessment.entities.customer.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    com.maybank.assessment.model.generated.Customer toGeneratedModel(
            com.maybank.assessment.entities.customer.Customer entityCustomer
    );

    com.maybank.assessment.entities.customer.Customer toEntity(
            com.maybank.assessment.model.generated.Customer generatedCustomer
    );

    @Mapping(target = "oid", ignore = true)
    com.maybank.assessment.entities.customer.Customer fromGeneratedRequestBodytoEntity(
            com.maybank.assessment.model.generated.CustomerRequestBody requestBody
    );
}
