package com.revature.crs.util.interfaces;

import com.revature.crs.util.exceptions.InvalidInputException;

import java.util.List;

public interface Serviceable<O> {
    List<O> findAll();
    O create(O newObject) throws InvalidInputException;
    O findById(int id);
}
