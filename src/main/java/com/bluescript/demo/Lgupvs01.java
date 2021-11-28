package com.bluescript.demo;

import java.net.URI;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import com.bluescript.demo.repository.KsdspolyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import io.swagger.annotations.ApiResponses;
import com.bluescript.demo.model.WfPolicyInfo;
import com.bluescript.demo.model.WfPolicyKey;
import com.bluescript.demo.model.WfCPolicyData;
import com.bluescript.demo.model.WfEPolicyData;
import com.bluescript.demo.model.WfHPolicyData;
import com.bluescript.demo.model.WfMPolicyData;
import com.bluescript.demo.model.ErrorMsg;
import com.bluescript.demo.model.EmVariable;
import com.bluescript.demo.model.Dfhcommarea;
import com.bluescript.demo.model.CaCustomerRequest;
import com.bluescript.demo.model.CaCustsecrRequest;
import com.bluescript.demo.model.CaPolicyRequest;
import com.bluescript.demo.model.CaPolicyCommon;
import com.bluescript.demo.model.CaEndowment;
import com.bluescript.demo.model.CaHouse;
import com.bluescript.demo.model.CaMotor;
import com.bluescript.demo.model.CaCommercial;
import com.bluescript.demo.entity.ksdsPolyKey;
import com.bluescript.demo.model.CaClaim;
import com.bluescript.demo.entity.KsdsPolyEntity;

@Getter
@Setter
@RequiredArgsConstructor
@Log4j2
@Component

@RestController
@RequestMapping("/")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@ApiResponses(value = {
        @io.swagger.annotations.ApiResponse(code = 400, message = "This is a bad request, please follow the API documentation for the proper request format"),
        @io.swagger.annotations.ApiResponse(code = 401, message = "Due to security constraints, your access request cannot be authorized"),
        @io.swagger.annotations.ApiResponse(code = 500, message = "The server/Application is down. Please contact support team.") })

public class Lgupvs01 {

    @Autowired
    private WfPolicyInfo wfPolicyInfo;
    @Autowired
    private WfPolicyKey wfPolicyKey;
    @Autowired
    private WfCPolicyData wfCPolicyData;
    @Autowired
    private WfEPolicyData wfEPolicyData;
    @Autowired
    private WfHPolicyData wfHPolicyData;
    @Autowired
    private WfMPolicyData wfMPolicyData;
    @Autowired
    private ErrorMsg errorMsg;
    @Autowired
    private EmVariable emVariable;
    @Autowired
    private Dfhcommarea dfhcommarea;
    @Autowired
    private CaCustomerRequest caCustomerRequest;
    @Autowired
    private CaCustsecrRequest caCustsecrRequest;
    @Autowired
    private CaPolicyRequest caPolicyRequest;
    @Autowired
    private CaPolicyCommon caPolicyCommon;
    @Autowired
    private CaEndowment caEndowment;
    @Autowired
    private CaHouse caHouse;
    @Autowired
    private CaMotor caMotor;
    @Autowired
    private CaCommercial caCommercial;
    @Autowired
    private CaClaim caClaim;

    @Autowired
    private KsdspolyRepository ksdspoly;

    private int wsResp;
    private int wsResp2;
    private int wsCommareaLen = 0;
    private String wsTime;
    private String wsDate;
    private String caData;
    @Value("${api.LGSTSQ.host}")
    private String LGSTSQ_HOST;
    @Value("${api.LGSTSQ.uri}")
    private String LGSTSQ_URI;
    private String caErrorMsg;
    private int eibcalen;

    @PostMapping("/lgupvs01")
    @Transactional
    public ResponseEntity<Dfhcommarea> mainline(@RequestBody Dfhcommarea payload) {
        BeanUtils.copyProperties(payload, dfhcommarea);
        log.warn("mainline started:" + dfhcommarea);
        wfPolicyKey.setWfRequestId(dfhcommarea.getCaRequestId().substring(3, 4));
        wfPolicyKey.setWfPolicyNum(String.valueOf(dfhcommarea.getCaPolicyRequest().getCaPolicyNum()));
        wfPolicyKey.setWfCustomerNum(String.valueOf(dfhcommarea.getCaCustomerNum()));
        log.warn(" wfPolicyKey:" + wfPolicyKey.toString());
        switch (wfPolicyKey.getWfRequestId()) {
        case "C":
            wfCPolicyData.setWfBPostcode(dfhcommarea.getCaPolicyRequest().getCaCommercial().getCaBPostcode());
            wfCPolicyData.setWfBStatus(dfhcommarea.getCaPolicyRequest().getCaCommercial().getCaBStatus());
            wfCPolicyData.setWfBCustomer(
                    dfhcommarea.getCaPolicyRequest().getCaCommercial().getCaBCustomer().substring(0, 31));
            log.warn("wfCPolicyData:" + wfCPolicyData.toString());
            break;
        case "E":
            wfEPolicyData.setWfEWithProfits(dfhcommarea.getCaPolicyRequest().getCaEndowment().getCaEWithProfits());
            wfEPolicyData.setWfEEquities(dfhcommarea.getCaPolicyRequest().getCaEndowment().getCaEEquities());
            wfEPolicyData.setWfEManagedFund(dfhcommarea.getCaPolicyRequest().getCaEndowment().getCaEManagedFund());
            wfEPolicyData.setWfEFundName(dfhcommarea.getCaPolicyRequest().getCaEndowment().getCaEFundName());
            wfEPolicyData.setWfELifeAssured(
                    dfhcommarea.getCaPolicyRequest().getCaEndowment().getCaELifeAssured().substring(0, 30));
            break;
        case "H":
            wfHPolicyData.setWfHPropertyType(dfhcommarea.getCaPolicyRequest().getCaHouse().getCaHPropertyType());
            wfHPolicyData.setWfHBedrooms(dfhcommarea.getCaPolicyRequest().getCaHouse().getCaHBedrooms());
            wfHPolicyData.setWfHValue(dfhcommarea.getCaPolicyRequest().getCaHouse().getCaHValue());
            wfHPolicyData.setWfHPostcode(dfhcommarea.getCaPolicyRequest().getCaHouse().getCaHPostcode());
            wfHPolicyData
                    .setWfHHouseName(dfhcommarea.getCaPolicyRequest().getCaHouse().getCaHHouseName().substring(0, 9));
            break;
        case "M":
            wfMPolicyData.setWfMMake(dfhcommarea.getCaPolicyRequest().getCaMotor().getCaMMake());
            wfMPolicyData.setWfMModel(dfhcommarea.getCaPolicyRequest().getCaMotor().getCaMModel());
            wfMPolicyData.setWfMValue(dfhcommarea.getCaPolicyRequest().getCaMotor().getCaMValue());
            wfMPolicyData.setWfMRegnumber(dfhcommarea.getCaPolicyRequest().getCaMotor().getCaMRegnumber());
            break;
        default:
            wfPolicyInfo.setWfPolicyData("");
            break;
        }

        // wfPolicyKey.setWfPolicyNum(String.valueOf(caPolicyRequest.getCaPolicyNum()));
        try {

            ksdsPolyKey policykey = new ksdsPolyKey(dfhcommarea.getCaRequestId().substring(3, 4),
                    String.valueOf(dfhcommarea.getCaCustomerNum()),
                    String.valueOf(dfhcommarea.getCaPolicyRequest().getCaPolicyNum()));
            log.warn("polickey:" + policykey.toString());
            String wsFilein = ksdspoly.findById(policykey).get().toString();
            log.warn("Wsfilein:" + wsFilein);
        } catch (Exception e) {
            log.error(e);
            wsResp = 1;
        }

        if (wsResp > 1) {
            dfhcommarea.setCaReturnCode(81);
            writeErrorMessage();
            log.error("Error code :, LGV3");
            // throw new LGV3Exception("LGV3");
            /* return */

        }
        try {
            int k = ksdspoly.updateKsdsPolyById(wfCPolicyData.getWfBPostcode(), wfCPolicyData.getWfBStatus(),
                    wfCPolicyData.getWfBCustomer(), wfEPolicyData.getWfEWithProfits(), wfEPolicyData.getWfEEquities(),
                    wfEPolicyData.getWfEManagedFund(), wfEPolicyData.getWfEFundName(),
                    wfEPolicyData.getWfELifeAssured(), wfHPolicyData.getWfHPropertyType(),
                    wfHPolicyData.getWfHBedrooms(), wfHPolicyData.getWfHValue(), wfHPolicyData.getWfHPostcode(),
                    wfHPolicyData.getWfHHouseName(), wfMPolicyData.getWfMMake(), wfMPolicyData.getWfMModel(),
                    wfMPolicyData.getWfMValue(), wfMPolicyData.getWfMRegnumber(), wfPolicyKey.getWfRequestId(),
                    wfPolicyKey.getWfCustomerNum(), wfPolicyKey.getWfPolicyNum());
            log.warn("K update:" + k);
        } catch (Exception e) {
            log.error(e);
            wsResp = 1;
        }

        if (wsResp > 0) {
            dfhcommarea.setCaReturnCode(82);
            writeErrorMessage();
            log.error("Error code :, LGV4");
            // throw new LGV4Exception("LGV4");
            /* return */

        }
        return new ResponseEntity<>(dfhcommarea, HttpStatus.OK);
    }

    public void writeErrorMessage() {
        log.debug("MethodwriteErrorMessagestarted..");
        String wsAbstime = LocalTime.now().toString();
        wsDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        wsTime = LocalTime.now().toString();
        errorMsg.setEmDate(wsDate.substring(0, 8));
        errorMsg.setEmTime(wsTime.substring(0, 6));
        WebClient webclientBuilder = WebClient.create(LGSTSQ_HOST);
        try {
            Mono<ErrorMsg> lgstsqResp = webclientBuilder.post().uri(LGSTSQ_URI)
                    .body(Mono.just(errorMsg), ErrorMsg.class).retrieve().bodyToMono(ErrorMsg.class)
                    .timeout(Duration.ofMillis(10_000));
            errorMsg = lgstsqResp.block();
        } catch (Exception e) {
            log.error(e);
        }

        if (eibcalen > 0) {
            if (eibcalen < 91) {
                try {
                    Mono<ErrorMsg> lgstsqResp = webclientBuilder.post().uri(LGSTSQ_URI)
                            .body(Mono.just(errorMsg), ErrorMsg.class).retrieve().bodyToMono(ErrorMsg.class)
                            .timeout(Duration.ofMillis(10_000));
                    errorMsg = lgstsqResp.block();
                } catch (Exception e) {
                    log.error(e);
                }

            } else {
                try {
                    Mono<String> lgstsqResp = webclientBuilder.post().uri(LGSTSQ_URI)
                            .body(Mono.just(caErrorMsg), String.class).retrieve().bodyToMono(String.class)
                            .timeout(Duration.ofMillis(10_000));
                    caErrorMsg = lgstsqResp.block();
                } catch (Exception e) {
                    log.error(e);
                }

            }

        }

        log.debug("Method writeErrorMessage completed..");
    }
    /* End of program */
}