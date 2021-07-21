package com.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.micronaut.core.annotation.Introspected;

import java.util.Collections;
import java.util.List;

@Introspected
public class ApiResponse<T> {
    public List<Questions> items = Collections.emptyList();

    @JsonProperty("has_more")
    public boolean hasMore;

    @JsonProperty("quota_max")
    public int quotaMax;

    @JsonProperty("quota_remaining")
    public int quotaRemaining;
}
