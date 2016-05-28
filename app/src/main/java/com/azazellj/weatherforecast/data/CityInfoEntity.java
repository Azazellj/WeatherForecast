package com.azazellj.weatherforecast.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by azazellj on 28.05.16.
 */
public class CityInfoEntity {
    private Result result;
    private String status;
    @SerializedName("html_attributions")
    private List<?> htmlAttributions;

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<?> getHtmlAttributions() {
        return htmlAttributions;
    }

    public void setHtmlAttributions(List<?> htmlAttributions) {
        this.htmlAttributions = htmlAttributions;
    }

    public static class Result {
        @SerializedName("adr_address")
        private String adrAddress;
        @SerializedName("formatted_address")
        private String formattedAddress;
        private Geometry geometry;
        private String icon;
        private String id;
        private String name;
        @SerializedName("place_id")
        private String placeId;
        private String reference;
        private String scope;
        private String url;
        @SerializedName("utc_offset")
        private int utcOffset;
        private String vicinity;
        @SerializedName("address_components")
        private List<AddressComponents> addressComponents;
        private List<Photos> photos;
        private List<String> types;

        public String getAdrAddress() {
            return adrAddress;
        }

        public void setAdrAddress(String adrAddress) {
            this.adrAddress = adrAddress;
        }

        public String getFormattedAddress() {
            return formattedAddress;
        }

        public void setFormattedAddress(String formattedAddress) {
            this.formattedAddress = formattedAddress;
        }

        public Geometry getGeometry() {
            return geometry;
        }

        public void setGeometry(Geometry geometry) {
            this.geometry = geometry;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
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

        public String getScope() {
            return scope;
        }

        public void setScope(String scope) {
            this.scope = scope;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public int getUtcOffset() {
            return utcOffset;
        }

        public void setUtcOffset(int utcOffset) {
            this.utcOffset = utcOffset;
        }

        public String getVicinity() {
            return vicinity;
        }

        public void setVicinity(String vicinity) {
            this.vicinity = vicinity;
        }

        public List<AddressComponents> getAddressComponents() {
            return addressComponents;
        }

        public void setAddressComponents(List<AddressComponents> addressComponents) {
            this.addressComponents = addressComponents;
        }

        public List<Photos> getPhotos() {
            return photos;
        }

        public void setPhotos(List<Photos> photos) {
            this.photos = photos;
        }

        public List<String> getTypes() {
            return types;
        }

        public void setTypes(List<String> types) {
            this.types = types;
        }

        public static class Geometry {
            private Location location;
            private Viewport viewport;

            public Location getLocation() {
                return location;
            }

            public void setLocation(Location location) {
                this.location = location;
            }

            public Viewport getViewport() {
                return viewport;
            }

            public void setViewport(Viewport viewport) {
                this.viewport = viewport;
            }

            public static class Location {
                private double lat;
                private double lng;

                public double getLat() {
                    return lat;
                }

                public void setLat(double lat) {
                    this.lat = lat;
                }

                public double getLng() {
                    return lng;
                }

                public void setLng(double lng) {
                    this.lng = lng;
                }
            }

            public static class Viewport {
                private Northeast northeast;
                private Southwest southwest;

                public Northeast getNortheast() {
                    return northeast;
                }

                public void setNortheast(Northeast northeast) {
                    this.northeast = northeast;
                }

                public Southwest getSouthwest() {
                    return southwest;
                }

                public void setSouthwest(Southwest southwest) {
                    this.southwest = southwest;
                }

                public static class Northeast {
                    private double lat;
                    private double lng;

                    public double getLat() {
                        return lat;
                    }

                    public void setLat(double lat) {
                        this.lat = lat;
                    }

                    public double getLng() {
                        return lng;
                    }

                    public void setLng(double lng) {
                        this.lng = lng;
                    }
                }

                public static class Southwest {
                    private double lat;
                    private double lng;

                    public double getLat() {
                        return lat;
                    }

                    public void setLat(double lat) {
                        this.lat = lat;
                    }

                    public double getLng() {
                        return lng;
                    }

                    public void setLng(double lng) {
                        this.lng = lng;
                    }
                }
            }
        }

        public static class AddressComponents {
            @SerializedName("long_name")
            private String longName;
            @SerializedName("short_name")
            private String shortName;
            private List<String> types;

            public String getLongName() {
                return longName;
            }

            public void setLongName(String longName) {
                this.longName = longName;
            }

            public String getShortName() {
                return shortName;
            }

            public void setShortName(String shortName) {
                this.shortName = shortName;
            }

            public List<String> getTypes() {
                return types;
            }

            public void setTypes(List<String> types) {
                this.types = types;
            }
        }

        public static class Photos {
            private int height;
            @SerializedName("photo_reference")
            private String photoReference;
            private int width;
            @SerializedName("html_attributions")
            private List<String> htmlAttributions;

            public int getHeight() {
                return height;
            }

            public void setHeight(int height) {
                this.height = height;
            }

            public String getPhotoReference() {
                return photoReference;
            }

            public void setPhotoReference(String photoReference) {
                this.photoReference = photoReference;
            }

            public int getWidth() {
                return width;
            }

            public void setWidth(int width) {
                this.width = width;
            }

            public List<String> getHtmlAttributions() {
                return htmlAttributions;
            }

            public void setHtmlAttributions(List<String> htmlAttributions) {
                this.htmlAttributions = htmlAttributions;
            }
        }
    }
}
