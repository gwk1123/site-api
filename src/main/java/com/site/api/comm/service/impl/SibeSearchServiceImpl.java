package com.site.api.comm.service.impl;

import com.site.api.comm.config.SibeProperties;
import com.site.api.comm.ota.site.SibeBaseResponse;
import com.site.api.comm.ota.site.SibeSearchRequest;
import com.site.api.comm.service.transform.TransformCommonOta;
import com.site.api.comm.sibe.SibeSearchService;
import com.site.api.comm.utils.exception.CustomSibeException;
import com.site.api.util.constants.SibeConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SibeSearchServiceImpl implements SibeSearchService {

    private Logger logger =LoggerFactory.getLogger(SibeSearchService.class);

    @Autowired
    private SibeProperties sibeProperties;


    @Override
    public <T extends SibeBaseResponse> T search(SibeSearchRequest sibeSearchRequest) throws Exception {


        //1 请求参数校验，cid校验
        this.cidValidate( sibeSearchRequest,  sibeProperties);
        //2转化站点数据
        TransformCommonOta.constructSibeBaseRequest( sibeSearchRequest,  sibeProperties);
        //3站点开关校验 // TODO

        //4 获得Redis系统配置数据，将获取到的数据设置到sibeSearchRequest

        return null;
    }









    /**
     * 校验CID
     * @param sibeSearchRequest  基础search信息
     * @param sibeProperties     the sibe properties
     */
    private void cidValidate (final SibeSearchRequest sibeSearchRequest, SibeProperties sibeProperties) {
        String uuid=sibeSearchRequest.getUuid();
        if(!sibeProperties.getOta().getCid().equals(sibeSearchRequest.getCid())){
            logger.error("uuid:"+ sibeSearchRequest.getUuid()+" 错误" +"cid error or format error");
            throw new CustomSibeException(SibeConstants.RESPONSE_STATUS_1, "cid ERROR"+SibeConstants.RESPONSE_MSG_1, uuid,"search");
        }
    }

}
