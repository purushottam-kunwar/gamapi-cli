package com.api;

import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.QueryValue;
import io.micronaut.http.client.annotation.Client;

@Client("${GamApiCli.api.url}")
public interface GamApiHttpClient {

    @Get("/search?site=stackoverflow")
    ApiResponse<Questions> search(
            @QueryValue("intitle") String query,
            @QueryValue("tagged") String tag,
            @QueryValue("pagesize") int limit,
            @QueryValue("sort") String sort

    );

}
