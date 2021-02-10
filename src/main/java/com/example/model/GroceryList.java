package com.example.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Data
@Entity
@Table (name = "GroceryList")
public class GroceryList {
	
	@Id
	@Column (name = "listId")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Setter(AccessLevel.NONE)
	private int listId;
	
	@Column (name = "nameOfList", unique = true, nullable = false)
	private String nameOfList;
	
	@Column (name = "groceryStoreName")
	private String groceryStoreName;
	
	//@JsonManagedReference //Prevents recursion in retrieve requests
	@OneToMany(mappedBy = "gList", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonIgnore
	private List<GroceryItem> listItems = new ArrayList<>();

	public GroceryList() {
		super();
	}

	public GroceryList(int listId, String nameOfList, String groceryStoreName, List<GroceryItem> listItems) {
		super();
		this.listId = listId;
		this.nameOfList = nameOfList;
		this.groceryStoreName = groceryStoreName;
		this.listItems = listItems;
	}

	public GroceryList(String nameOfList, String groceryStoreName, List<GroceryItem> listItems) {
		super();
		this.nameOfList = nameOfList;
		this.groceryStoreName = groceryStoreName;
		this.listItems = listItems;
	}

	public GroceryList(String nameOfList, String groceryStoreName) {
		super();
		this.nameOfList = nameOfList;
		this.groceryStoreName = groceryStoreName;
	}
	
	//@Transient private int poster_id;
}
