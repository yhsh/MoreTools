package com.qfy.locationdemo;

public class PointDouble {
    double x, y;

    PointDouble(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public String toString() {
        return "x=" + x + ", y=" + y;
    }
}
