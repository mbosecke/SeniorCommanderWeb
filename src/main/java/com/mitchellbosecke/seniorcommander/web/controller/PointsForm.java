package com.mitchellbosecke.seniorcommander.web.controller;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by mitch_000 on 2016-10-11.
 */
public class PointsForm {

    @NotNull
    @Size(min = 1, max = 50)
    private String pointsPlural;

    @NotNull
    @Min(0)
    @Max(100)
    private Integer pointsOnline;

    public String getPointsPlural() {
        return pointsPlural;
    }

    public void setPointsPlural(String pointsPlural) {
        this.pointsPlural = pointsPlural;
    }

    public Integer getPointsOnline() {
        return pointsOnline;
    }

    public void setPointsOnline(Integer pointsOnline) {
        this.pointsOnline = pointsOnline;
    }
}
