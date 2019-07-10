package com.wanczowski.addatagenerator.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Sample Instagram API Response.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InstagramAd {

  private List<AnalyticData> data;

  private transient String id;

  @Data
  @AllArgsConstructor
  public class AnalyticData {
    private String name;
    private String period;
    private List<Value> values;
    private String title;
    private String description;
    private String id;
  }

  @Data
  @AllArgsConstructor
  public class Value {
    private Integer value;
  }
}
