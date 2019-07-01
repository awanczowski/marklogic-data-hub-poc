package com.wanczowski.addatagenerator.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * {
 * "request": {
 * "params": {
 * "campaign_ids": [
 * "8fgzf"
 * ],
 * "start_time": "2017-05-19T00:00:00Z",
 * "end_time": "2017-05-26T00:00:00Z",
 * "account_id": "18ce54d4x5t"
 * }
 * },
 * "data_type": "reach",
 * "data": [
 * {
 * "id": "8fgzf",
 * "total_audience_reach": 1217,
 * "average_frequency": 1.01
 * }
 * ]
 * }
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TwitterAd {

    private transient String id;

    private Request request;

    @JsonProperty("data_type")
    private String dataType;

    private List<AnalyticData> data;

    @Data
    @AllArgsConstructor
    public class Request {
        private Params params;
    }

    @Data
    @AllArgsConstructor
    public class Params {

        @JsonProperty("campaign_ids")
        private List<String> campaignIds;

        @JsonProperty("start_time")
        private LocalDateTime startTime;

        @JsonProperty("end_time")
        private LocalDateTime endTime;

        @JsonProperty("account_id")
        private String accountId;
    }

    @Data
    @AllArgsConstructor
    public class AnalyticData {

        private String id;

        @JsonProperty("total_audience_reach")
        private Integer totalAudienceReach;

        @JsonProperty("average_frequency")
        private Double averageFrequency;

    }
}
