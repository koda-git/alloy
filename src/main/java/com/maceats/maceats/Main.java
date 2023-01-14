package com.maceats.maceats;

import com.maceats.maceats.struct.MenuObject;
import com.maceats.maceats.struct.RestaurantObject;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<RestaurantObject> ros = RestaurantObject.getOpenRestaurant();
        ArrayList<MenuObject> mos = ros.get(0).getMenus();

        for (MenuObject ro : mos) {
            System.out.println(ro.toJson(ro));
        }
    }

}

// Trying to use git
