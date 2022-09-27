package com.ndphuc.dp.dbtt;

public class Thoitiet {
    public String Day;
    public String Status;
    public String Image;
    public String MaxTemp;
    public String MinTemp;

    public Thoitiet(String day, String status, String image, String maxTemp, String minTemp) {
        Day = day;
        Status = status;
        Image = image;
        MaxTemp = maxTemp;
        MinTemp = minTemp;
    }

    public String getDay() {
        return Day;
    }

    public String getStatus() {
        return Status;
    }

    public String getImage() {
        return Image;
    }

    public String getMaxTemp() {
        return MaxTemp;
    }

    public String getMinTemp() {
        return MinTemp;
    }
}
