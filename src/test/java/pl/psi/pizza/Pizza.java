// ******************************************************************
//
// Copyright 2023 PSI Software AG. All rights reserved.
// PSI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms
//
// ******************************************************************

package pl.psi.pizza;

/**
 * TODO: Describe this class (The first line - until the first dot - will interpret as the brief description).
 */
public class Pizza {

    int price;

    public Pizza(int aPrice) {
        price = aPrice;
    }

    int getPrice(){
        return price;
    }

    @Override
    public String toString() {
        return "Pizza z sosem pimidorowym, serem ...";
    }
}
