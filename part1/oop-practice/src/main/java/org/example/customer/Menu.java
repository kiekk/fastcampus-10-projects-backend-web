package org.example.customer;

import java.util.List;

public class Menu {

    private final List<MenuItem> menuItems;

    public Menu(List<MenuItem> menuItems) {
        this.menuItems = menuItems;
    }

    public MenuItem choose(String name) {
        return new MenuItem("돈까스", 5_000);
    }
}
