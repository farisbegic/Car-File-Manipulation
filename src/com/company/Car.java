package com.company;

public class Car implements Comparable<Car>{
    private String name;
    private double MPG;
    private int cylinders;
    private double displacement;
    private double horsepower;
    private double weight;
    private double acceleration;
    private int model;
    private String origin;

    public Car(String name, double MPG, int cylinders, double displacement, double horsepower, double weight, double acceleration, int model, String origin) {
        this.name = name;
        this.MPG = MPG;
        this.cylinders = cylinders;
        this.displacement = displacement;
        this.horsepower = horsepower;
        this.weight = weight;
        this.acceleration = acceleration;
        this.model = model;
        this.origin = origin;
    }

    public String getName() {
        return name;
    }

    public double getMPG() {
        return MPG;
    }

    public int getCylinders() {
        return cylinders;
    }

    public double getDisplacement() {
        return displacement;
    }

    public double getHorsepower() {
        return horsepower;
    }

    public double getWeight() {
        return weight;
    }

    public double getAcceleration() {
        return acceleration;
    }

    public int getModel() {
        return model;
    }

    public String getOrigin() {
        return origin;
    }

    @Override
    public String toString() {
        return name + " " + MPG + " " + cylinders + " " + displacement + " " + horsepower + " " + weight + " " + acceleration + " " + model + " " + origin + "\n";
    }

    @Override
    public int compareTo(Car o) {
        if (this.getAcceleration() > o.getAcceleration()) return 1;
        else return -1;
    }
}
