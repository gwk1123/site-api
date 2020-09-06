package com.site.api.web.rest.ctrip;

import com.baomidou.mybatisplus.core.toolkit.SystemClock;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.site.api.util.constants.SibeConstants;
import comm.ota.ctrip.model.CtripSearchRequest;
import comm.ota.ctrip.model.CtripSearchResponse;
import comm.ota.ctrip.transform.TransformCtripSearchRequest;
import comm.ota.site.SibeSearchRequest;
import comm.sibe.SibeSearchService;
import comm.utils.exception.CustomSibeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/flight")
public class SearchCtripResource {

    private Logger logger = LoggerFactory.getLogger(SearchCtripResource.class);
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private TransformCtripSearchRequest transformCtripSearchRequest;
    @Autowired
    private SibeSearchService sibeSearchService;

    @RequestMapping(value = "/search", method = RequestMethod.POST,
            consumes = MediaType.TEXT_PLAIN_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String searchCtrip(@RequestBody String request) throws Exception {
        CtripSearchRequest ctripSearchRequest = null;
        try {
            objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
            ctripSearchRequest = objectMapper.readValue(request, CtripSearchRequest.class);
        } catch (Exception e) {
            logger.error("请求无法解析成json格式 Exception", e);
            throw new CustomSibeException(SibeConstants.RESPONSE_STATUS_1, "请求无法解析成json格式 JsonParseException", "00000", "search");
        }
        SibeSearchRequest sibeSearchRequest = transformCtripSearchRequest.toSearchRequest(ctripSearchRequest);
        logger.info("uuid:" + sibeSearchRequest.getUuid() + " search请求:"
                + " 渠道" + sibeSearchRequest.getChannel()
                + " " + sibeSearchRequest.getTripType()
                + "出发地" + sibeSearchRequest.getFromCity()
                + "-" + sibeSearchRequest.getToCity()
                + "出发时间" + sibeSearchRequest.getFromDate()
                + "-" + sibeSearchRequest.getRetDate());

        CtripSearchResponse ctripSearchResponse = (CtripSearchResponse) sibeSearchService.search(sibeSearchRequest);

        Long s = (SystemClock.now() - sibeSearchRequest.getStartTime()) / (1000);
        logger.info("uuid:" + sibeSearchRequest.getUuid() + " search返回消耗:" + s + "秒");
        return objectMapper.writeValueAsString(ctripSearchResponse);

    }


}

