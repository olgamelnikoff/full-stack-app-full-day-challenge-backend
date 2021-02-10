package com.example.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Data
@Entity
@Table (name = "GroceryItem")
public class GroceryItem {
	@Id
	@Column (name = "itemId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Setter(AccessLevel.NONE)
	private int itemId;
	
	@Column (name = "itemName", unique = true, nullable = false)
	private String itemName;
	
	@Column(name = "itemCost")
	private int itemCost;
	
	@Column (name = "itemType")
	private String itemType;
	
	//@JsonBackReference // Meant to help when trying to create a List. - Prevents recursion in retrieve requests, this is the better solution.
	@ManyToOne(cascade=CascadeType.MERGE, fetch = FetchType.LAZY)
	@JoinColumn(name = "groceryListId")
	@JsonIgnore
	private GroceryList gList;
	
	@Transient
	private int gListId;
	
	@Transient
	private String gListName;
	
	@Transient
	private String gListStoreName;
	
	public GroceryItem() {

	}
	
	public void setUpFields() {
		this.gListId = gList.getListId();
		this.gListName = gList.getNameOfList();
		this.gListStoreName = gList.getGroceryStoreName();
	}

	public GroceryItem(int itemId, String itemName, int itemCost, String itemType, GroceryList gList) {
		super();
		this.itemId = itemId;
		this.itemName = itemName;
		this.itemCost = itemCost;
		this.itemType = itemType;
		this.gList = gList;
		setUpFields();
	}

	public GroceryItem(String itemName, int itemCost, String itemType, GroceryList gList) {
		super();
		this.itemName = itemName;
		this.itemCost = itemCost;
		this.itemType = itemType;
		this.gList = gList;
		setUpFields();
	}

	public GroceryItem(String itemName, int itemCost, String itemType) {
		super();
		this.itemName = itemName;
		this.itemCost = itemCost;
		this.itemType = itemType;
		setUpFields();
	}
}
