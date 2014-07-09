package com.myexample.cardictionary;

import android.graphics.Bitmap;

public class CarClass {
	private int resourceID;
	private String Name;
	private Bitmap img;
	private String description;
	
	public CarClass(int resource_id, String name, Bitmap img, String description) {
		this.resourceID = resource_id;
		this.Name = name;
		this.img = img;
		this.description = description;
	}
	
	public int getResourceID() { return resourceID; }
	public String getName() { return Name; }
	public Bitmap getImage() { return img; }
	public String getDescription() { return description; }
}
