package com.szines.accountapi.errors;

import org.eclipse.jetty.http.HttpStatus;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.HashMap;

@Provider
public class WebExceptionMapper implements ExceptionMapper<WebApplicationException> {

    WebApplicationException webApplicationException;

    @Override
    public Response toResponse(final WebApplicationException e) {

        webApplicationException = e;

        return Response.status(status())
                .type(MediaType.APPLICATION_JSON_TYPE)
                .entity(responseJson())
                .build();
    }

    private int status() {
        return webApplicationException.getResponse() == null ? 500 : webApplicationException.getResponse().getStatus();
    }

    private String msg() {
        return webApplicationException.getMessage() == null ? HttpStatus.getMessage(status()) : webApplicationException.getMessage();
    }

    private HashMap<String, String> responseJson() {
        HashMap<String, String> json = new HashMap<>();

        json.put("status_code", String.valueOf(status()));
        json.put("error_message", msg());

        return json;
    }
}
