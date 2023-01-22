package com.example.sqlitedemo.model;

import java.util.ArrayList;

public interface FlagsDAOInt {
    /***
     * zieht 10 zufällige Flaggen aus der DB
     * @param fd die DB
     * @return ArrayList aus 10 Flaggen
     */
    public ArrayList<FlagsModel> getRandomTenQuestion(FlagsDatabase fd);

    /***
     * zieht 3 zufällige Flaggen aus der DB die als falsche Antwort benutzt
     * @param fd die DB
     * @param flag_id die id von der richtigen Flagge Antwort
     * @return Arraylist von 3 falsche Flaggen
     */
    public ArrayList<FlagsModel> getRandomThreeOptions(FlagsDatabase fd, int flag_id);
}
