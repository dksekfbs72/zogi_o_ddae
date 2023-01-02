package com.zerobase.zogi_o_ddae.domain.repository.accommodation;

import com.zerobase.zogi_o_ddae.domain.entity.accommodation.AccommodationWishList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccommodationWishListRepository extends JpaRepository<AccommodationWishList, Long> {

}