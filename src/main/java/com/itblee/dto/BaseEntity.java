package com.itblee.dto;

import java.io.Serializable;

public abstract class BaseEntity implements Serializable {
    public abstract Integer getID();
    public abstract void setID(Integer id);
}
