package com.seeleyben;

import com.seeleyben.House;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;

//Headers Import
import com.seeleyben.HeadersKey;
import com.seeleyben.HeadersKey.ArrayEnum;

/**
 * Created by brendan on 1/19/2017.
 */
public class CSVReader {

    private String mFileName;
    private Reader reader;


    CSVReader() {

    }

    CSVReader(String fileName) {
        mFileName = fileName;

        try {
            reader = new FileReader(new File(fileName));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void newFileName(String fileName){
        mFileName = fileName;
    }

    private void startReader(){
        if(reader == null){
            try {
                reader = new FileReader(new File(mFileName));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    private void endReader(){
        if(reader != null){
            try {
                reader.close();
                reader = null;
            }
            catch(Exception ex){
                ex.printStackTrace();
            }
        }
    }

    //NOTE: CREATES NEW VECTOR ARRAY
    //Input: [Header specification]
    //Output: [Vector of Houses with "Specified Headers"]
    public Vector<House> readCSV(ArrayEnum en) {
        Vector<String> headersArray = HeadersKey.getInstance().getArray(en);
        Vector<House> houseV = new Vector<House>(70000);

        try {
            startReader();
            Iterable<CSVRecord> records = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(reader);

            int index = 0;
            for (CSVRecord record : records) {                      //record is entire row of data in CSV
                House house = new House(++index);                   //Creates a new house

                for (String header : headersArray) {                //headersArray Defines all headers to be selected

                    String value = record.get(header);              //Gets header specific value

                    if(!( value.equals("") && value.equals("NULL") )){
                        house.dataMap.put(header, value);           //Key is header string, value is header specific row data
                    }
                }

                houseV.add(house);                                  //Adds house to vector
            }
            endReader();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return houseV;
    }

    //TODO: DOES NOT WORK, IS SUPPOSED TO MODIFY VECTOR BY REFERENCE
    //NOTE: MODIFIES EXISTING VECTOR ARRAY
    //Input: [Header specification] [Existing Housing Vector]
    //Output: [Vector of Houses with "Specified Headers"] [
    public void readCSV(ArrayEnum en, Vector<House> houseV) {
        Vector<String> headersArray = HeadersKey.getInstance().getArray(en);

        try {
            startReader();
            Iterable<CSVRecord> records = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(reader);

            int index = 0;
            for (CSVRecord record : records) {                      //record is entire row of data in CSV

                House house = houseV.get(index);
                for (String header : headersArray) {                //headersArray Defines all headers to be selected

                    String value = record.get(header);              //Gets header specific value

                    if(!( value.equals("") && value.equals("NULL") )){
                        //Vector of houses is accessed and modified
                        house.dataMap.put(header, value);           //Key is header string, value is header specific row data
                    }
                }
                index++;
            }
            endReader();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public Vector<House> readCSV(String header) {
        Vector<House> houseV = new Vector<House>(70000);

        try {
            startReader();
            try {
                Iterable<CSVRecord> records = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(reader);

                int index = 0;
                for (CSVRecord record : records) {                      //record is entire row of data in CSV

                    House house = new House(++index);                   //Creates a new house

                    String value = record.get(header);                  //Gets header specific value

                    if (!( value.equals("") && value.equals("NULL") )) {
                        house.dataMap.put(header, value);               //Key is header string, value is header specific row data
                    }
                    houseV.add(house);                                  //Adds house to vector
                }
            }
            catch (IOException ex){
                ex.printStackTrace();
            }
        } finally {
            endReader();
        }
        return houseV;
    }

    public void readCSV(String header, Vector<House> houseV) {
        try {
            startReader();
            Iterable<CSVRecord> records = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(reader);

            int index = 0;
            for (CSVRecord record : records) {                      //record is entire row of data in CSV

                House house = houseV.get(index);                    //Modifies Existing house
                String value = record.get(header);                  //Gets header specific value

                if ( !( value.equals("") && value.equals("NULL") ) ) {
                    house.dataMap.put(header, value);               //Key is header string, value is header specific row data
                }
                index++;
            }

            endReader();
        } catch (IOException ex){
            ex.printStackTrace();
        }
    }
}

/*
    //Input: [Header specification], [Line Number]
    //Output: [New House]
    public House readCSV(ArrayEnum en, int lineID){
        House house = new House(lineID);


        return house;
    }

    //Input: [Header specification], [Line Number], [Existing House]
    //Output: [Modified House]
    public House readCSV(ArrayEnum en, int lineID, House house){


        return house;
    }
*/

