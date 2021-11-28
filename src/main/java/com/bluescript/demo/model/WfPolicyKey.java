package com.bluescript.demo.model;

import lombok.Getter;
import lombok.Setter;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import java.util.*;

import org.springframework.stereotype.Component;

@Data
@Component

public class WfPolicyKey {
    private String wfRequestId;
    private String wfCustomerNum;
    private String wfPolicyNum;

    public String toFixedWidthString() {
        return wfRequestId + wfCustomerNum + wfPolicyNum;
    }

}