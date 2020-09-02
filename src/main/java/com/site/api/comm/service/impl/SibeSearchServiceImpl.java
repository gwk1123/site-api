package com.site.api.comm.service.impl;

import com.site.api.comm.ota.site.SibeBaseResponse;
import com.site.api.comm.ota.site.SibeSearchRequest;
import com.site.api.comm.sibe.SibeSearchService;
import org.springframework.stereotype.Service;

@Service
public class SibeSearchServiceImpl implements SibeSearchService {
    @Override
    public <T extends SibeBaseResponse> T search(SibeSearchRequest sibeSearchRequest) throws Exception {
        return null;
    }
}
