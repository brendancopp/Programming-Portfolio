package com.seeleyben;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Vector;

public class Main {

    public static void main(String[] args) {
        String readCSVPath = "C:\\Users\\brendan\\Documents\\github\\CoreLogicGrandChallenge2017\\CoreLogicJava\\Data\\GrandChallengeRefined.csv";
        String writeCSVPath = "C:\\Users\\brendan\\Documents\\github\\CoreLogicGrandChallenge2017\\CoreLogicJava\\Data\\GrandChallengeElevation.csv";
        String elevationReadCSVPath = "";

        //Creates Python Process
        try{
            //Use Python to Get Elevation
            ProcessBuilder pb = new ProcessBuilder("python","elevation_parser.py", readCSVPath, writeCSVPath, HeadersKey.latitude, HeadersKey.longitude);
            Process p = pb.start();

            BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
            elevationReadCSVPath = in.readLine();
            System.out.println(elevationReadCSVPath);

        }catch(Exception e){System.out.println(e);}

        //Reads in as java objects
        CSVReader reader = new CSVReader(readCSVPath);
        Vector<House> houseV;

        //Creates new vector, and adds street values
        houseV = reader.readCSV(HeadersKey.ArrayEnum.STREET);

        //Create Elevation Reader
        CSVReader elevationReader = new CSVReader(elevationReadCSVPath);

        //Read from python filepath, and modify current vector
        elevationReader.readCSV(HeadersKey.elevation, houseV);

        //Modifies current vector, adds location values
        //reader.readCSV(HeadersKey.ArrayEnum.LOCATION, houseV);


        //*** Test ***


        //Iterates and prints all houses in vector
        for(int i = 0; i < 20; i++){
            houseV.get(i).toStringPrint();
        }

        System.out.println("*****************************************");

        for(int i = 0; i < 20; i++){
            System.out.println(houseV.get(i).ID + " : " + houseV.get(i).dataMap.get(HeadersKey.street_name));
        }

    }
}