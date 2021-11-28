package com.bluescript.demo.repository;

import com.bluescript.demo.entity.KsdsPolyEntity;
import com.bluescript.demo.entity.ksdsPolyKey;
import com.bluescript.demo.model.WfPolicyInfo;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

@Repository
public interface KsdspolyRepository extends CrudRepository<KsdsPolyEntity, ksdsPolyKey> {
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE KSDSPOLY set BPOSTCODE=:wfBPostcode,BSTATUS=:wfBStatus,BCUSTOMER=:wfBCustomer,EWITH_PROFITS=:wfEWithProfits,EEQUITIES=:wfEEquities,EMANAGED_FUND=:wfEManagedFund,EFUND_NAME=:wfEFundName,ELIFE_ASSURED=:wfELifeAssured,HPROPERTY_TYPE=:wfHPropertyType,HBEDROOMS=:wfHBedrooms,HVALUE=:wfHValue,   HPOSTCODE=:wfHPostcode,HHOUSE_NAME=:wfHHouseName,MMAKE=:WfMMake,MMODEL=:wfMModel,FMVALU=:wfMValue,   FMREGNUMBER=:wfMRegnumber WHERE REQUESTID=:wfRequestId and CUSTOMERNUM=:wfCustomerNum and POLICYNUM=:wfPolicyNum", nativeQuery = true)

    void updateKsdsPolyById(@Param("wfBPostcode") String wfBPostcode, @Param("wfBStatus") int wfBStatus,
            @Param("wfBCustomer") String wfBCustomer, @Param("wfEWithProfits") String wfEWithProfits,
            @Param("wfEEquities") String wfEEquities, @Param("wfEManagedFund") String wfEManagedFund,
            @Param("wfEFundName") String wfEFundName, @Param("wfELifeAssured") String wfELifeAssured,
            @Param("wfHPropertyType") String wfHPropertyType, @Param("wfHBedrooms") int wfHBedrooms,
            @Param("wfHValue") int wfHValue, @Param("wfHPostcode") String wfHPostcode,
            @Param("wfHHouseName") String wfHHouseName, @Param("WfMMake") String WfMMake,
            @Param("wfMModel") String wfMModel, @Param("wfMValue") int wfMValue,
            @Param("wfMRegnumber") String wfMRegnumber, @Param("wfRequestId") String wfRequestId,
            @Param("wfCustomerNum") String wfCustomerNum, @Param("wfPolicyNum") String wfPolicyNum);

}