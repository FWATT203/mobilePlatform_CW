package com.example.mpd_cw_fraser_watt;         //FRASER WATT - S1620179

public class CurrentIncidents {
    private String title;
    private String coordinates;
    private String publishDate;
    private double latitude;
    private double longitude;
    private double tempLat;
    private double tempLong;



////////////////////////////////////////////////////////////////////////////////////////////////////



    public CurrentIncidents() {

    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }




    public void splitCoords(String coordinates){
        String[] split = coordinates.split(" ");
        tempLat = Double.parseDouble(split[0]);
        tempLong = Double.parseDouble(split[1]);
        setLatitude(tempLat);
        setLongitude(tempLong);
    }
}