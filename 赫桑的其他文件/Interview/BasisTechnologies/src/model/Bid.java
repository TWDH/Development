package model;

public class Bid {
    private Integer requestId;

    private String pageURL;

    private String country;

    private String dimension;

    public Bid() {
    }

    public Bid(Integer requestId, String pageURL, String country, String dimension) {
        this.requestId = requestId;
        this.pageURL = pageURL;
        this.country = country;
        this.dimension = dimension;
    }

    public Integer getRequestId() {
        return requestId;
    }

    public void setRequestId(Integer requestId) {
        this.requestId = requestId;
    }

    public String getPageURL() {
        return pageURL;
    }

    public void setPageURL(String pageURL) {
        this.pageURL = pageURL;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDimension() {
        return dimension;
    }

    public void setDimension(String dimension) {
        this.dimension = dimension;
    }

    @Override
    public String toString() {
        return "Bid{" +
                "requestId=" + requestId +
                ", pageURL='" + pageURL + '\'' +
                ", country='" + country + '\'' +
                ", dimension='" + dimension + '\'' +
                '}';
    }
}
