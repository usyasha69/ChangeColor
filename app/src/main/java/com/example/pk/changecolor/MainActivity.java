package com.example.pk.changecolor;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static final String COLOR_KEY = "color";
    public static final String COLORS_KEY = "colors";

    private ArrayList<String> colors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createAndFillingColorsList();
        createFragments();
    }

    private void createAndFillingColorsList() {
        colors = new ArrayList<>();

        colors.add("#ff0000");
        colors.add("#00ff00");
        colors.add("#0000ff");
        colors.add("#ffff00");
        colors.add("#ff00ff");
        colors.add("#00ffff");
        colors.add("#000000");
        colors.add("#cdfab3");
        colors.add("#28dcab");
        colors.add("#78eefc");
    }

    private void createFragments() {
        //create lists with listeners
        ArrayList<ColorFragment> changeColorListenersForFirst = new ArrayList<>();
        ArrayList<ColorFragment> changeColorListenersForSecond = new ArrayList<>();
        ArrayList<ColorFragment> changeColorListenersForThird = new ArrayList<>();
        ArrayList<ColorFragment> changeColorListenersForFourth = new ArrayList<>();

        //create fragments
        ColorFragment firstColorFragment = ColorFragment.newInstance("#0000ff", colors);
        ColorFragment secondColorFragment = ColorFragment.newInstance("#ff0000", colors);
        ColorFragment thirdColorFragment = ColorFragment.newInstance("#00ff00", colors);
        ColorFragment fourthColorFragment = ColorFragment.newInstance("#000000", colors);

        //filling list with listeners for first fragment
        changeColorListenersForFirst.add(secondColorFragment);
        changeColorListenersForFirst.add(thirdColorFragment);
        changeColorListenersForFirst.add(fourthColorFragment);

        //filling list with listeners for second fragment
        changeColorListenersForSecond.add(firstColorFragment);
        changeColorListenersForSecond.add(thirdColorFragment);
        changeColorListenersForSecond.add(fourthColorFragment);

        //filling list with listeners for third fragment
        changeColorListenersForThird.add(firstColorFragment);
        changeColorListenersForThird.add(secondColorFragment);
        changeColorListenersForThird.add(fourthColorFragment);

        //filling list with listeners for fourth fragment
        changeColorListenersForFourth.add(firstColorFragment);
        changeColorListenersForFourth.add(secondColorFragment);
        changeColorListenersForFourth.add(thirdColorFragment);

        //set listeners for fragments
        firstColorFragment.setChangeColorListeners(changeColorListenersForFirst);
        secondColorFragment.setChangeColorListeners(changeColorListenersForSecond);
        thirdColorFragment.setChangeColorListeners(changeColorListenersForThird);
        fourthColorFragment.setChangeColorListeners(changeColorListenersForFourth);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

        ft.add(R.id.first_cf_container, firstColorFragment);
        ft.add(R.id.second_cf_container, secondColorFragment);
        ft.add(R.id.third_cf_container, thirdColorFragment);
        ft.add(R.id.fourth_cf_container, fourthColorFragment);

        ft.commit();
    }
}
