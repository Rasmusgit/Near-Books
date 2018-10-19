package com.group8.ciu196.beaconproject;

public class Event {

    private String title;
    private String image;
    private String date;
    private String startTime, endTime;
    private String details;


    public Event(String title, String imageStr, String date, String startTime, String endTime, String details){
        this.title = title;
        this.image = imageStr;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.details = details;
    }

    public String getDetails() {
        return details;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getStartTimeWithDot(){
        String startTimeWithDot = startTime.substring(0,2) + "." + startTime.substring(2,4);
        return startTimeWithDot;
    }

    public String getEndTime() {
        return endTime;
    }

    public String getEndTimeWithDot(){
        String endTimeWithDot = endTime.substring(0,2) + "." + endTime.substring(2,4);
        return endTimeWithDot;
    }

    public String getDate() {
        return date;
    }

    public String getDateString() {
        String dateString = date.substring(6,8);

        switch (date.substring(4,6)){
            case "01":
                dateString += " januari ";
                break;
            case "02":
                dateString += " februari ";
                break;
            case "03":
                dateString += " mars ";
                break;
            case "04":
                dateString += " april ";
                break;
            case "05":
                dateString += " maj ";
                break;
            case "06":
                dateString += " juni ";
                break;
            case "07":
                dateString += " juli ";
                break;
            case "08":
                dateString += " augusti ";
                break;
            case "09":
                dateString += " september ";
                break;
            case "10":
                dateString += " oktober ";
                break;
            case "11":
                dateString += " november ";
                break;
            case "12":
                dateString += " december ";
                break;
            default:
                dateString += " okänd månad ";
        }
        dateString += date.substring(0,4);
        return dateString;
    }

    public String getImage() {
        return image;
    }

    public String getTitle() {
        return title;
    }
}
