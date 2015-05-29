package com.releaser.collector.apiclient;

import com.releaser.collector.exception.ApiClientException;
import com.releaser.collector.model.jaxb.api.xrel.ApiXRelModel;
import com.releaser.collector.release.ReleaseInterface;

import javax.xml.bind.JAXB;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * xRelease Client
 */
public class XRelClient implements ApiClientInterface
{
    /**
     * Reads information from xRel api
     *
     * @param release release object
     *
     * @throws ApiClientException
     *
     * @return output string
     */
    public ApiXRelModel read(ReleaseInterface release) throws ApiClientException
    {
        //TODO put into config
        String urlString = "http://api.xrel.to/api/release/info.xml?dirname=" + release.getName();

        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            //Send request via GET
            connection.setRequestMethod("GET");

            //Accept XML
            connection.setRequestProperty("Accept", "application/xml");

            //Connection successful?
            if (connection.getResponseCode() != 200) {
                throw new ApiClientException("Failed: HTTP error code: " + connection.getResponseCode());
            }

            ApiXRelModel apiXRelModel = JAXB.unmarshal(connection.getInputStream(), ApiXRelModel.class);

            //Close connection
            connection.disconnect();

            return apiXRelModel;

        } catch (MalformedURLException urlException) {
            throw new ApiClientException("Malformed URL: " + urlString, urlException);
        } catch (IOException ioException) {
            throw new ApiClientException("Cannot read response.", ioException);
        }
    }
}
