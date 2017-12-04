package com.seeleyben;

import java.util.Vector;

/**
 * Created by brendan on 1/20/2017.
 */

public class HeadersKey {
    //Singleton Stuff
    private static HeadersKey ourInstance = new HeadersKey();
    public static HeadersKey getInstance() {
        return ourInstance;
    }

    //Enum for arrays
    public enum ArrayEnum{
        ALL, STREET, LOCATION, ID, SIZING
    }ArrayEnum arrayEnum;

                                                    //Keys
    //Street Data
    public static final String street_name = "SITUS STREET NAME";
    public static final String street_type = "SITUS MODE";
    public static final String city = "SITUS CITY";
    public static final String state = "SIT STATE";
    public static final String zip = "SITUS ZIP CODE";
    public static final String carrier_code = "SITUS CARRIER CODE";

    //Location Data
    public static final String latitude = "PARCEL LEVEL LATITUDE";
    public static final String longitude = "PARCEL LEVEL LONGITUDE";
    public static final String house_num = "SIT HOUSE NUMBER";

    public static final String elevation = "ELEVATION";

    //ID Data
    public static final String fips = "FIPS CODE";
    public static final String apn = "FORMATTED APN";
    public static final String iris = "P-ID-IRIS-FRMTD";
    public static final String census_tract = "CENSUS TRACT";
    public static final String land_use = "LAND USE";

    //Sizing Data
    public static final String acres = "ACRES";
    public static final String front_footage = "SITUS CITY";
    public static final String depth_footage = "DEPTH FOOTAGE";
    public static final String land_square_footage = "LAND SQUARE FOOTAGE";
    public static final String building_square_footage = "UNIVERSAL-BUILDING-SQUARE-FEET";


    private HeadersKey() {
        //Constructor Populates arrays with corresponding headers
        streetVector.add(street_name);
        streetVector.add(street_type);
        streetVector.add(city);
        streetVector.add(state);
        streetVector.add(zip);
        streetVector.add(carrier_code);

        locationVector.add(latitude);
        locationVector.add(longitude);
        locationVector.add(house_num);

        IDVector.add(fips);
        IDVector.add(apn);
        IDVector.add(iris);
        IDVector.add(census_tract);
        IDVector.add(land_use);

        sizingData.add(acres);
        sizingData.add(front_footage);
        sizingData.add(depth_footage);
        sizingData.add(land_square_footage);
        sizingData.add(building_square_footage);


        //Builds allVector
        for(String header : streetVector){
            allVector.add(header);
        }
        for(String header : locationVector){
            allVector.add(header);
        }
        for(String header : IDVector){
            allVector.add(header);
        }
        for(String header : sizingData){
            allVector.add(header);
        }

    }

    public Vector<String> getArray(ArrayEnum en){

        if(en == ArrayEnum.STREET){
            return streetVector;
        }
        else if (en == ArrayEnum.LOCATION){
            return locationVector;
        }
        else if (en == ArrayEnum.ID){
            return IDVector;
        }
        else if (en == ArrayEnum.SIZING) {
            return sizingData;
        }
        else
            return allVector;
    }

    private Vector<String> allVector = new Vector<String>();

    private Vector<String> streetVector = new Vector<String>();
    private Vector<String> locationVector = new Vector<String>();
    private Vector<String> IDVector = new Vector<String>();
    private Vector<String> sizingData = new Vector<String>();

}
