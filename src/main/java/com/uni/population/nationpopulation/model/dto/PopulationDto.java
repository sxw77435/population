package com.uni.population.nationpopulation.model.dto;

public class PopulationDto {

    private int nationno;
    private String nationname;
    private int year2020;
    private int year2021;
    private int year2022;
    private int year2023;
    private int year2024;
    private int year2025;
    private String image;

    public int getNationno() {
        return nationno;
    }

    public void setNationno(int nationno) {
        this.nationno = nationno;
    }

    public String getNationname() {
        return nationname;
    }

    public void setNationname(String nationname) {
        this.nationname = nationname;
    }

    public int getYear2020() {
        return year2020;
    }

    public void setYear2020(int year2020) {
        this.year2020 = year2020;
    }

    public int getYear2021() {
        return year2021;
    }

    public void setYear2021(int year2021) {
        this.year2021 = year2021;
    }

    public int getYear2022() {
        return year2022;
    }

    public void setYear2022(int year2022) {
        this.year2022 = year2022;
    }

    public int getYear2023() {
        return year2023;
    }

    public void setYear2023(int year2023) {
        this.year2023 = year2023;
    }

    public int getYear2024() {
        return year2024;
    }

    public void setYear2024(int year2024) {
        this.year2024 = year2024;
    }

    public int getYear2025() {
        return year2025;
    }

    public void setYear2025(int year2025) {
        this.year2025 = year2025;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;

    }

    @Override
    public String toString() {
        return "PopulationDto{" +
                "nationno=" + nationno +
                ", nationname='" + nationname + '\'' +
                ", year2020=" + year2020 +
                ", year2021=" + year2021 +
                ", year2022=" + year2022 +
                ", year2023=" + year2023 +
                ", year2024=" + year2024 +
                ", year2025=" + year2025 +
                ", image='" + image + '\'' +
                '}';
    }


}
