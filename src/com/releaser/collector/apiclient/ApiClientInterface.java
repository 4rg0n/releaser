package com.releaser.collector.apiclient;

import com.releaser.collector.model.jaxb.api.ApiModelInterface;
import com.releaser.collector.exception.ApiClientException;
import com.releaser.collector.release.ReleaseInterface;

/**
 * Interface for ApiClients
 */
public interface ApiClientInterface
{
    /**
     * Reads information from an api
     *
     * @param release release object
     *
     * @return output from api
     */
    public ApiModelInterface read(ReleaseInterface release) throws ApiClientException;
}
