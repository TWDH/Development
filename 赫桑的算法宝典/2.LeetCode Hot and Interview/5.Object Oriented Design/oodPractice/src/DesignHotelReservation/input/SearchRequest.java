package DesignHotelReservation.input;

import java.util.Date;

public class SearchRequest {
    private Date startDate;
    private Date endDate;

    public SearchRequest(Date startDate, Date endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "SearchRequest{" +
                "startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
