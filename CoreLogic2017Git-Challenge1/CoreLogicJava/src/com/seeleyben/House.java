package com.seeleyben;

import java.util.HashMap;
import java.util.Vector;


/**
 * Created by brendan on 1/20/2017.
 */

public class House {
    public final int ID;
    public HashMap<String, String> dataMap;

    public House(int ID){
        this.ID = ID;
        dataMap = new HashMap<>(30);
    }

    //Copy Constructor
    public House(House house){
        this.ID = house.ID;
        dataMap = (HashMap<String, String>) house.dataMap.clone();
    }

    @Override
    public String toString() {
        Vector<String> headersVector  = HeadersKey.getInstance().getArray(HeadersKey.ArrayEnum.ALL);

        String toS = "ID: " + ID + "\n";
        toS += dataMap.toString();

        return toS;
    }


    public void toStringPrint() {
        Vector<String> headersVector  = HeadersKey.getInstance().getArray(HeadersKey.ArrayEnum.ALL);

        String toS = "ID: " + ID + "\n";
        toS += dataMap.toString();

        System.out.println(toS);
    }
}
