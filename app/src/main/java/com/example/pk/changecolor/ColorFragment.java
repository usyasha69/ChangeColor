package com.example.pk.changecolor;


import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class ColorFragment extends Fragment implements ChangeColorListener {
    private View view;
    private String color;
    private ArrayList<ColorFragment> changeColorListeners;

    public void setChangeColorListeners(ArrayList<ColorFragment> changeColorListeners) {
        this.changeColorListeners = changeColorListeners;
    }

    public static ColorFragment newInstance(String color) {

        Bundle args = new Bundle();
        args.putString(MainActivity.COLOR_KEY, color);

        ColorFragment fragment = new ColorFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null && getArguments().containsKey(MainActivity.COLOR_KEY)) {
            color = getArguments().getString(MainActivity.COLOR_KEY);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_color, container, false);

        final View fragment = view.findViewById(R.id.cf_background);
        fragment.setBackgroundColor(Color.parseColor(color));

        fragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //array with colors for fragments
                int[] colorArray = new int[changeColorListeners.size()];

                //get background color
                int backgroundColor = 0;

                Drawable background = view.getBackground();

                if (background instanceof ColorDrawable) {
                    backgroundColor = ((ColorDrawable) background).getColor();
                }

                while (true) {
                    //filling array with colors
                    for (int i = 0; i < colorArray.length; i++) {
                        colorArray[i] = Color.parseColor(
                                MainActivity.colors.get(
                                        (int) (Math.random() * MainActivity.colors.size())));
                    }

                    //result variable
                    boolean result = true;

                    //checked result
                    for (int i = 0; i < colorArray.length; i++) {
                        for (int j = i + 1; j < colorArray.length; j++) {
                            if (colorArray[i] == colorArray[j]) {
                                result = false;
                            }
                        }
                    }

                    //checked background color
                    for (int aColorArray : colorArray) {
                        if (aColorArray == backgroundColor) {
                            result = false;
                        }
                    }

                    if (result) {
                        break;
                    }
                }

                //filling colors in fragments
                for (int i = 0; i < changeColorListeners.size(); i++) {
                    changeColorListeners.get(i).changeColor(colorArray[i]);
                }
            }
        });

        return view;
    }

    @Override
    public void changeColor(int color) {
        (view.findViewById(R.id.cf_background)).setBackgroundColor(color);
    }
}
