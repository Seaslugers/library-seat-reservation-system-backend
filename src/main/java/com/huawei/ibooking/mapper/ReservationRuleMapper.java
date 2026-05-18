package com.huawei.ibooking.mapper;

import com.huawei.ibooking.model.ReservationRuleDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ReservationRuleMapper {
    ReservationRuleDO getRules();

    int saveRules(@Param("rules") ReservationRuleDO rules);
}