package com.example.sqlitedemo;

import static org.junit.Assert.assertNotNull;

import android.content.Context;

import com.example.sqlitedemo.model.FlagsDAO;
import com.example.sqlitedemo.model.FlagsDatabase;
import com.example.sqlitedemo.model.FlagsModel;

import org.junit.Test;


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

public class ModelTest {

    @Test
    public void flagsTest() {
        FlagsModel model = new FlagsModel(1, "flag_id", "flag_image");
        assertNotNull(model);
    }

    @Test
    public void daoTest() {
        FlagsDAO flagsDAO = new FlagsDAO();
        assertNotNull(flagsDAO);
    }
    /*@Test
    public void daoTest1(){
        Context context = new Context();
        FlagsDatabase fd = new FlagsDatabase(context);
        FlagsDAO flagsDAO = new FlagsDAO();
        flagsDAO.getRandomThreeOptions(fd, 1);
        flagsDAO.getRandomTenQuestion(fd);
    }*/

}