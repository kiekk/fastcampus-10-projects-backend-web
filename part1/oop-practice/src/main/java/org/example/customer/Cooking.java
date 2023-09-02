package org.example.customer;

public class Cooking {
    public Cook makeCook(MenuItem menuItem) {
        return new Cook("돈까스", 5_000);
    }
}
