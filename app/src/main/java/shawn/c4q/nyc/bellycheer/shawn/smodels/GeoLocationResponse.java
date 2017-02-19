package shawn.c4q.nyc.bellycheer.shawn.smodels;

import java.util.ArrayList;

/**
 * Created by shawnspeaks on 2/18/17.
 */

public class GeoLocationResponse {

    private ArrayList<Results> results = new ArrayList<>();
    private String status;


    public ArrayList<Results> getResultsArrayList() {
        return results;
    }
}
