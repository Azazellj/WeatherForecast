package com.azazellj.weatherforecast;

/**
 * Created by azazellj on 27.05.16.
 */
public class Constants {
    public static final double POSITION_UNKNOWN = 0.0d;
    public static final String STRING_EMPTY = "";
    public static final String FORMAT_TIME = "HH:mm";
    public static final String PARAM_TYPE = "(cities)";

    public static class StatusCode {
        public static final String OK = "OK";
        public static final String ZERO_RESULTS = "ZERO_RESULTS";
        public static final String OVER_QUERY_LIMIT = "OVER_QUERY_LIMIT";
        public static final String REQUEST_DENIED = "REQUEST_DENIED";
        public static final String INVALID_REQUEST = "INVALID_REQUEST";
    }
}
