package com.bluescript.demo.model;

import lombok.Getter;
import lombok.Setter;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import java.util.*;

import org.springframework.stereotype.Component;

@Data
@Component

public class WfMPolicyData {
    private String wfMMake;
    private String wfMModel;
    private int wfMValue;
    private String wfMRegnumber;

    public String toFixedWidthString() {
        return wfMMake + wfMModel + wfMValue + wfMRegnumber;
    }

}