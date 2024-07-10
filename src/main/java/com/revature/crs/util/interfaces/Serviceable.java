package com.revature.crs.util.interfaces;

public interface Serviceable<O> {
    O create(O newObject);
    O findById(int id);
}
