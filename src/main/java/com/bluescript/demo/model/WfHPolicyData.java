package com.bluescript.demo.model;

import lombok.Getter;
import lombok.Setter;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import java.util.*;

import org.springframework.stereotype.Component;

@Data
@Component

public class WfHPolicyData {
    private String wfHPropertyType;
    private int wfHBedrooms;
    private int wfHValue;
    private String wfHPostcode;
    private String wfHHouseName;

    public String toFixedWidthString() {
        return wfHPropertyType + wfHBedrooms + wfHValue + wfHPostcode + wfHHouseName;
    }

}