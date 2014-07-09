package com.myexample.cardictionary;

import java.io.Serializable;

public class CarClass implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int resourceID;
	private String Name;
	private String description;
	
	public CarClass(int resource_id, String name, String description) {
		this.resourceID = resource_id;
		this.Name = name;
		this.description = description;
	}
	
	public int getResourceID() { return resourceID; }
	public String getName() { return Name; }
	public String getDescription() { return description; }
}
