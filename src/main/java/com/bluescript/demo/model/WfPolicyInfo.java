package com.bluescript.demo.model;

import lombok.Getter;
import lombok.Setter;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import java.util.*;

import org.springframework.stereotype.Component;
import com.bluescript.demo.model.WfPolicyKey;
import com.bluescript.demo.model.WfCPolicyData;
import com.bluescript.demo.model.WfEPolicyData;
import com.bluescript.demo.model.WfHPolicyData;
import com.bluescript.demo.model.WfMPolicyData;

@Data
@Component

public class WfPolicyInfo {
    private WfPolicyKey wfPolicyKey;
    private String wfPolicyData;
    private WfCPolicyData wfCPolicyData;
    private WfEPolicyData wfEPolicyData;
    private WfHPolicyData wfHPolicyData;
    private WfMPolicyData wfMPolicyData;

    public String toFixedWidthString() {
        return wfPolicyKey.toFixedWidthString() + wfPolicyData + wfCPolicyData.toFixedWidthString()
                + wfEPolicyData.toFixedWidthString() + wfHPolicyData.toFixedWidthString()
                + wfMPolicyData.toFixedWidthString();
    }

}