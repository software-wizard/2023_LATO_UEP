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
public class PizzaHamDecorator extends Pizza
{
    private final Pizza decorated;

    public PizzaHamDecorator( Pizza aPizza )
    {
        super(0);
        decorated = aPizza;
    }

    @Override
    int getPrice()
    {
        int result = decorated.getPrice();
        return result + 4;
    }
}
