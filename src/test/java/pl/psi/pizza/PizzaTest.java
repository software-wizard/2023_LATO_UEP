// ******************************************************************
//
// Copyright 2023 PSI Software AG. All rights reserved.
// PSI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms
//
// ******************************************************************

package pl.psi.pizza;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/**
 * TODO: Describe this class (The first line - until the first dot - will interpret as the brief description).
 */
public class PizzaTest {

    @Test
    void pizzaShouldCost22(){
        Pizza pizza = new Pizza(15);
        pizza = new PizzaHamDecorator(pizza);
        pizza = new PizzaMushromsDecorator(pizza);

        assertThat(pizza.getPrice()).isEqualTo(22);

        Pizza pizza2 = new Pizza(15);
        pizza2 = new PizzaMushromsDecorator(pizza2);
        pizza2 = new PizzaMushromsDecorator(pizza2);
        assertThat(pizza2.getPrice()).isEqualTo(18);

    }
}
