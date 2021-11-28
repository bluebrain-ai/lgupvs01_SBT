package com.bluescript.demo.model;

import lombok.Getter;
import lombok.Setter;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import java.util.*;

import org.springframework.stereotype.Component;

@Data
@Component

public class WfEPolicyData {
    private String wfEWithProfits;
    private String wfEEquities;
    private String wfEManagedFund;
    private String wfEFundName;
    private String wfELifeAssured;

    public String toFixedWidthString() {
        return wfEWithProfits + wfEEquities + wfEManagedFund + wfEFundName + wfELifeAssured;
    }

}