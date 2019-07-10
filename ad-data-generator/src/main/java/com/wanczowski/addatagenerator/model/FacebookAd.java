package com.wanczowski.addatagenerator.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Sample Facebook Ad API Response.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FacebookAd {

  private String id;
  private String name;
  private Insights insights;
  private Paging paging;

  @Data
  @AllArgsConstructor
  public class Insights {
    private List<AnalyticData> data;
  }

  @Data
  @AllArgsConstructor
  public class AnalyticData {
    private String impressions;

    @JsonProperty("date_start")
    private LocalDate dateStart;

    @JsonProperty("date_stop")
    private LocalDate dateStop;
  }

  @Data
  @AllArgsConstructor
  public class Paging {
    private Cursors cursors;
  }

  @Data
  @AllArgsConstructor
  public class Cursors {
    private String before;
    private String after;
  }

}
