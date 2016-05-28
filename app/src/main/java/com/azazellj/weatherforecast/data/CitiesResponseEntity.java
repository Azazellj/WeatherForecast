package com.azazellj.weatherforecast.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by azazellj on 28.05.16.
 */
public class CitiesResponseEntity {
    private String status;
    private List<Predictions> predictions;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Predictions> getPredictions() {
        return predictions;
    }

    public void setPredictions(List<Predictions> predictions) {
        this.predictions = predictions;
    }

    public static class Predictions {
        private String description;
        private String id;
        @SerializedName("place_id")
        private String placeId;
        private String reference;
        @SerializedName("matched_substrings")
        private List<MatchedSubstrings> matchedSubStrings;
        private List<Terms> terms;
        private List<String> types;

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getPlaceId() {
            return placeId;
        }

        public void setPlaceId(String placeId) {
            this.placeId = placeId;
        }

        public String getReference() {
            return reference;
        }

        public void setReference(String reference) {
            this.reference = reference;
        }

        public List<MatchedSubstrings> getMatchedSubStrings() {
            return matchedSubStrings;
        }

        public void setMatchedSubStrings(List<MatchedSubstrings> matchedSubStrings) {
            this.matchedSubStrings = matchedSubStrings;
        }

        public List<Terms> getTerms() {
            return terms;
        }

        public void setTerms(List<Terms> terms) {
            this.terms = terms;
        }

        public List<String> getTypes() {
            return types;
        }

        public void setTypes(List<String> types) {
            this.types = types;
        }

        public static class MatchedSubstrings {
            private int length;
            private int offset;

            public int getLength() {
                return length;
            }

            public void setLength(int length) {
                this.length = length;
            }

            public int getOffset() {
                return offset;
            }

            public void setOffset(int offset) {
                this.offset = offset;
            }
        }

        public static class Terms {
            private int offset;
            private String value;

            public int getOffset() {
                return offset;
            }

            public void setOffset(int offset) {
                this.offset = offset;
            }

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }
        }
    }
}
