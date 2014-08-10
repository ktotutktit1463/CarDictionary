package com.myexample.cardictionary;

import java.io.Serializable;

/* それぞれの車の情報をもつクラス、アクティビティ間で渡せるようSerializable */
public class CarClass implements Serializable {
	private static final long serialVersionUID = 1L;

	private int resourceID;
	private String Name;
	private String resourceName;
	private String description;

	/* コンストラクタ */
	public CarClass(int resource_id, String name, String resourceName, String description) {
		this.resourceID = resource_id;
		this.Name = name;
		this.resourceName = resourceName;
		this.description = description;
	}

	public int getResourceID() { return resourceID; }
	public String getresourceName () { return resourceName; }
	public String getName() { return Name; }
	public String getDescription() { return description; }
}
