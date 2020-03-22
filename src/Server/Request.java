package Server;

public class Request {
    public Request(String country, int month, int startDay, int endDay, int size) {
        this.country = country;
        this.month = month;
        this.startDay = startDay;
        this.endDay = endDay;
        this.count = 0;
        this.max = size;
    }

    String country;
    int month;
    int startDay;
    int endDay;
    int count;
    int max;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getStartDay() {
        return startDay;
    }

    public void setStartDay(int startDay) {
        this.startDay = startDay;
    }

    public int getEndDay() {
        return endDay;
    }

    public void setEndDay(int endDay) {
        this.endDay = endDay;
    }

    @Override
    public String toString() {
        return "Request{" +
                "country='" + country + '\'' +
                ", month=" + month +
                ", startDay=" + startDay +
                ", endDay=" + endDay +
                '}';
    }
}
