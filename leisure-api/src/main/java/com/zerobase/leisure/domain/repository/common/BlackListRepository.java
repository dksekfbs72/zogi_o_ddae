package com.zerobase.leisure.domain.repository.common;

import com.zerobase.leisure.domain.entity.common.BlackList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlackListRepository extends JpaRepository<BlackList, Long> {

}
