package com.site.api.comm.sibe;

import com.site.api.comm.ota.site.SibeBaseResponse;
import com.site.api.comm.ota.site.SibeSearchRequest;



/**
 * The interface Sibe search service.
 */
public interface SibeSearchService {
    /**
     * Search request gds gds search response dto.
     *
     * @param <T>               the type parameter
     * @param sibeSearchRequest the ota search request
     * @return the gds search response dto
     * @throws Exception the exception
     */
    public <T extends SibeBaseResponse> T search(SibeSearchRequest sibeSearchRequest) throws Exception;



}
