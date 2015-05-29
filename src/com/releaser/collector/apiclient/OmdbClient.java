package com.releaser.collector.apiclient;

import com.releaser.collector.exception.ApiClientException;
import com.releaser.collector.model.jaxb.api.omdb.ApiOmdbModel;
import com.releaser.collector.release.ReleaseInterface;

import javax.xml.bind.JAXB;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by droenicke on 27.05.2015.
 */
public class OmdbClient implements ApiClientInterface
{
    public ApiOmdbModel read(ReleaseInterface release) throws ApiClientException
    {
        String imdbId = release.getXRelModel().getImdbId();
        String urlString = "http://www.omdbapi.com/?r=xml&i=" + imdbId;

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

            ApiOmdbModel apiOmdbModel = JAXB.unmarshal(connection.getInputStream(), ApiOmdbModel.class);

            //Close connection
            connection.disconnect();

            return apiOmdbModel;

        } catch (MalformedURLException urlException) {
            throw new ApiClientException("Malformed URL: " + urlString, urlException);
        } catch (IOException ioException) {
            throw new ApiClientException("Cannot read response.", ioException);
        }
    }
}
