package com.cooksys.ftd.assignments.day.three.collections;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.cooksys.ftd.assignments.day.three.collections.hierarchy.Hierarchy;
import com.cooksys.ftd.assignments.day.three.collections.model.Capitalist;
import com.cooksys.ftd.assignments.day.three.collections.model.FatCat;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.*;
import java.util.Map.Entry;

public class MegaCorp implements Hierarchy<Capitalist, FatCat> {

	Map<FatCat, Set<Capitalist>> company = new HashMap<FatCat, Set<Capitalist>>();
	
    /**
     * Adds a given element to the hierarchy.
     * <p>
     * If the given element is already present in the hierarchy,
     * do not add it and return false
     * <p>
     * If the given element has a parent and the parent is not part of the hierarchy,
     * add the parent and then add the given element
     * <p>
     * If the given element has no parent but is a Parent itself,
     * add it to the hierarchy
     * <p>
     * If the given element has no parent and is not a Parent itself,
     * do not add it and return false
     *
     * @param capitalist the element to add to the hierarchy
     * @return true if the element was added successfully, false otherwise
     */
    @Override
    public boolean add(Capitalist capitalist) {
    	
    	//checks to see if there is a capitalist and returns false if this capitalist is not present
        if(capitalist == null)
        	return false;
        
        //checks to see if the capitalist has a parent capitalist
        if(has(capitalist))
        	return false;
        
        //checks to see if the capitalist is a FatCat
        if(capitalist instanceof FatCat){
        	HashSet<Capitalist> child = new HashSet<Capitalist>(); //creates new Set for the capitalist
        	company.put((FatCat) capitalist, child); //add the child to the parent in the map
        	
        } else {
        	//checks to see if capitalist does not have a parent and returns false
			if(!capitalist.hasParent())
				return false;
		}
        //if the capitalist has parent, we get the parent
        if (capitalist.hasParent()) {
			Capitalist parent = capitalist.getParent(); //we get the parent and add it to the variable

				add(parent); //we add the FatCat to parent
				Set<Capitalist> child = new HashSet<Capitalist>(); //we create a new empty child set for the new FatCat parent
				child.add(capitalist); //we add the new child set to the capitolist
				company.put((FatCat) parent, child);//we put the newly created capitalist into the map
			return true;
		}
		return true;
    }


	/**
     * @param capitalist the element to search for
     * @return true if the element has been added to the hierarchy, false otherwise
     */
    @Override
    public boolean has(Capitalist capitalist) {
    	if(capitalist != null) {
	    	if (capitalist instanceof FatCat){ //check to see if capitalist is a FatCat
		       if(company.containsKey(capitalist)){ //if the map has a key for the capitalist, return true
		    	   return true;
		       }else{
		    	   for(FatCat key : company.keySet()) { //we loop through the capitalists and set keys
		    		   if (company.get(key).contains(capitalist)) //we then look to see if the map contains a key and returns true
		    			   return true;
		    	   }
		   		}
	    	}
    	}
    	return false;
    }

    /**
     * @return all elements in the hierarchy,
     * or an empty set if no elements have been added to the hierarchy
     */
    @Override
    public Set<Capitalist> getElements() {
        Set<Capitalist> child = new HashSet<Capitalist>();//create empty set fit there is none for parent
        for(Capitalist parent : company.keySet()) { //we set the parent to a key
        	child.add(parent); //if parent is a child, add it to the parent
        	child.addAll(getChildren((FatCat) parent));//if there are children, add them
        }
        return child;
    }

    /**
     * @return all parent elements in the hierarchy,
     * or an empty set if no parents have been added to the hierarchy
     */
    @Override
    public Set<FatCat> getParents() {
        if(company.keySet() == null) {
        	Set<FatCat> newSet = new HashSet<FatCat>();
        	return newSet;
        }
        return company.keySet();
        }

    /**
     * @param fatCat the parent whose children need to be returned
     * @return all elements in the hierarchy that have the given parent as a direct parent,
     * or an empty set if the parent is not present in the hierarchy or if there are no children
     * for the given parent
     */
    @Override
    public Set<Capitalist> getChildren(FatCat fatCat) {
    	Set<Capitalist> newSet = new HashSet<>();
    	if(this.has(fatCat)) {
    	newSet.addAll(this.company.get(fatCat));
    	}
    	return newSet;
    }

    /**
     * @return a map in which the keys represent the parent elements in the hierarchy,
     * and the each value is a set of the direct children of the associate parent, or an
     * empty map if the hierarchy is empty.
     */
    @Override
    public Map<FatCat, Set<Capitalist>> getHierarchy() {
    	Map<FatCat, Set<Capitalist>> company = new HashMap<FatCat, Set<Capitalist>>();
    	for(FatCat fatCat : getParents()) {
    		company.put(fatCat,  getChildren(fatCat));
    	}
    	return company;
    }

    /**
     * @param capitalist
     * @return the parent chain of the given element, starting with its direct parent,
     * then its parent's parent, etc, or an empty list if the given element has no parent
     * or if its parent is not in the hierarchy
     */
    @Override
    public List<FatCat> getParentChain(Capitalist capitalist) {
      List<FatCat> parentList = new ArrayList<FatCat>();
      if(capitalist == null)
    	  return parentList;
	return parentList;
    }
}
