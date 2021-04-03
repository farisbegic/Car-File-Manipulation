package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        ArrayList<Car> carsFixed = new ArrayList<>();
        ArrayList<Car> carsAcceleration = new ArrayList<>();
        ArrayList<Car> carsHorsepower = new ArrayList<>();
        Map<String, ArrayList<Car>> byOrigin = new HashMap<>();
        // write cars in csv file but fixed
        carsFixed = readFile("input/cars.csv");
        writeFile(carsFixed, "output/carsFixed.csv");
        // write cars in csv file sorted by acceleration (from fastest to slowest)
        carsAcceleration = readFile("input/cars.csv");
        Collections.sort(carsAcceleration);
        writeFile(carsAcceleration, "output/carsAcceleration.csv");
        // write cars in csv file sorted by horsepower (from strongest to slowest)
        carsHorsepower = readFile("input/cars.csv");
        Comparator<Car> compareByHP = new Comparator<Car>() {
            @Override
            public int compare(Car o1, Car o2) {
                if (o1.getHorsepower() < o2.getHorsepower())
                    return 1;
                else
                    return -1;
            }
        };
        carsHorsepower.sort(compareByHP);
        writeFile(carsHorsepower, "output/carsHorsepower.csv");
        // sort cars by origin and write each origin in separate .txt file (ex US.txt)
        byOrigin = sortMap(carsFixed);
        for (String s : byOrigin.keySet()) {
            File file = new File("output/" + s + "." + "txt");
            FileWriter fw = new FileWriter(file);
            for (Car c : byOrigin.get(s)) {
                try {
                    fw.write(c.toString());
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
            fw.close();
        }
    }

    private static Map<String, ArrayList<Car>> sortMap(ArrayList<Car> carsFixed) {
        Map<String, ArrayList<Car>> mapa = new HashMap<>();
        for (Car c : carsFixed) {
            if (!mapa.containsKey(c.getOrigin())){
                mapa.put(c.getOrigin(), new ArrayList<Car>());
                mapa.get(c.getOrigin()).add(c);
            } else{
                mapa.get(c.getOrigin()).add(c);
            }
        }
        return mapa;
    }

    private static void writeFile(ArrayList<Car> cars, String path) {
        File file = new File(path);
        try {
            FileWriter fw = new FileWriter(file.getPath());
            fw.write("Name" + "," + "MPG" + "," + "Cylinders" + "," + "Displacement" + "," + "Horsepower" + "," + "Weight" + "," + "Acceleration" + "," + "Model"  + "," + "Origin" + "\n");
            for (Car c : cars) {
                fw.write(c.getName() + "," + c.getMPG() + "," + c.getCylinders() + "," + c.getDisplacement() + "," + c.getHorsepower() + "," + c.getWeight() + "," + c.getAcceleration() + "," + c.getModel() + "," + c.getOrigin() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static ArrayList<Car> readFile(String s) {
        ArrayList<Car> cars = new ArrayList<>();
        File file = new File(s);
        try {
            Scanner scan = new Scanner(file);
            scan.nextLine(); scan.nextLine();
            while (scan.hasNextLine()){
                String[] parts = scan.nextLine().split(";");
                cars.add(new Car(parts[0], Double.parseDouble(parts[1]), Integer.parseInt(parts[2]), Double.parseDouble(parts[3]),Double.parseDouble(parts[4]), Double.parseDouble(parts[5]), Double.parseDouble(parts[6]), Integer.parseInt(parts[7]), parts[8]));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return cars;
    }
}
