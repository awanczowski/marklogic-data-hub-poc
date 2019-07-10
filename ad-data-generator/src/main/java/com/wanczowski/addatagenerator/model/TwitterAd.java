package com.wanczowski.addatagenerator.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Sample Twitter API response.
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
