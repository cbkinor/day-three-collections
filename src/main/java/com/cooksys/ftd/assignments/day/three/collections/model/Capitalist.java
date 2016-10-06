package com.cooksys.ftd.assignments.day.three.collections.model;

import com.cooksys.ftd.assignments.day.three.collections.hierarchy.Hierarchical;

public interface Capitalist extends Hierarchical<Capitalist, FatCat> {

    /**
     * @return the name of the capitalist
     */
    String getName();

    /**
     * @return the salary of the capitalist, in dollars
     */
    int getSalary();
}
