package com.wanczowski.addatagenerator.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

/**
 * {
 * "id": "6042542123268",
 * "name": "My Website Clicks Ad",
 * "insights": {
 * "data": [
 * {
 * "impressions": "9708",
 * "date_start": "2016-03-06",
 * "date_stop": "2016-04-01"
 * }
 * ],
 * "paging": {
 * "cursors": {
 * "before": "MAZDZD",
 * "after": "MAZDZD"
 * }
 * }
 * }
 * }
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
