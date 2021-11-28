package com.bluescript.demo.model;

import lombok.Getter;
import lombok.Setter;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import java.util.*;

import org.springframework.stereotype.Component;

@Data
@Component

public class WfCPolicyData {
    private String wfBPostcode;
    private int wfBStatus;
    private String wfBCustomer;

    public String toFixedWidthString() {
        return wfBPostcode + wfBStatus + wfBCustomer;
    }

}