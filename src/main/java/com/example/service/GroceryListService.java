package com.example.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.model.GroceryItem;
import com.example.model.GroceryList;
import com.example.repository.GroceryItemDao;
import com.example.repository.GroceryListDao;

@Service("groceryListService")
public class GroceryListService {
	
	@Autowired
	GroceryListDao groceryListDao;
	
	@Autowired
	GroceryItemDao groceryItemDao;
	
	public List<GroceryList> retrieveAllGroceryLists() {
		return groceryListDao.findAll();
	}
	
	public GroceryList retrieveGroceryListById(int id) {
		return groceryListDao.findById(id);
	}
	
	/*@Transactional
	public List<GroceryItem> retrieveAllGroceryItemsByGroceryList(GroceryList gList) {
		List<GroceryItem> itemList = new ArrayList<>();
		itemList = 
		return groceryItemDao.findByGList(gList);
	}*/
	
	public void createNewGroceryList(GroceryList gList) {
		groceryListDao.save(gList);
	}
	
	public void deleteGroceryList(GroceryList gList) {
		groceryListDao.delete(gList);
	}
	
	public void createNewGroceryItem(GroceryItem groceryItem) {
		groceryItemDao.save(groceryItem);
	}
	
	public void deleteGroceryItem(int groceryItemId) {
		groceryItemDao.deleteById(groceryItemId);
	}
}
