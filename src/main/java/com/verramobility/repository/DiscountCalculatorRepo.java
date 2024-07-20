package com.verramobility.repository;

import com.verramobility.constant.CommonConstant;
import com.verramobility.model.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DiscountCalculatorRepo extends JpaRepository<ProductModel,Long> {

    @Query(value = CommonConstant.FETCH_PERCENTAGE,nativeQuery = true)
    Double findDiscountByProductName(@Param(CommonConstant.PARAM) String name);

}
