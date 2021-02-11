package com.example.controller;

import java.sql.Timestamp;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.GroceryItem;
import com.example.model.GroceryList;
import com.example.service.GroceryListService;

@RestController("groceryListController")
@RequestMapping(value = "/grocery-lists")
@CrossOrigin(origins = "*")
public class GroceryListController {
	
	@Autowired
	private GroceryListService gListServ;
	
	@GetMapping("/all")
	public ResponseEntity<List<GroceryList>> getAllGroceryLists() {
		List<GroceryList> gList = gListServ.retrieveAllGroceryLists();
		if(gList.size() == 0) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(gList, HttpStatus.OK);
		}
	}
	
	/*@GetMapping("/{id}")
	public ResponseEntity<List<GroceryItem>> getAllGroceryItemsForGroceryList(@PathVariable("id") int id) {
		GroceryList gList = gListServ.retrieveGroceryListById(id);
		if(gList == null) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		} else {
			List<GroceryItem> itemList = gListServ.retrieveAllGroceryItemsByGroceryList(gList);
			return new ResponseEntity<>(itemList, HttpStatus.OK);
		}
	}*/
	
	@PostMapping("/new")
	public ResponseEntity<String> postGroceryList(@RequestBody LinkedHashMap groceryListMap) {
		String nameOfList = (String)groceryListMap.get("nameOfList");
		
		String groceryStoreName = (String)groceryListMap.get("groceryStoreName");
		
		GroceryList gList = new GroceryList(nameOfList, groceryStoreName);
		
		gListServ.createNewGroceryList(gList);
		
		return new ResponseEntity<>("A new grocery list successfully added", HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteGroceryList(@PathVariable("id") int id) {
		GroceryList gList = gListServ.retrieveGroceryListById(id);
		if(gList == null) {
			return new ResponseEntity<>("Grocery list failed to be deleted", HttpStatus.NOT_FOUND);
		} else {
			gListServ.deleteGroceryList(gList);
			return new ResponseEntity<>("Grocery list sucessfully deleted", HttpStatus.OK);
		}
	}
	
	@CrossOrigin(origins = "*")
	@PostMapping(path = "/{id}/item/new")
	public ResponseEntity<String> createItem(@RequestBody LinkedHashMap groceryItem, @PathVariable("id") int id) {
		GroceryList gList = gListServ.retrieveGroceryListById(id);
		if(gList == null) {
			return new ResponseEntity<>("Grocery list not found", HttpStatus.NOT_FOUND);
		} else {
			String itemName = (String)groceryItem.get("itemName");
			
			String itemCostasString = (String)groceryItem.get("itemCost");
			
			int itemCost = Integer.parseInt(itemCostasString);
			
			String itemType = (String)groceryItem.get("itemType");
			
			GroceryItem gItem = new GroceryItem(itemName, itemCost, itemType, gList);
			
			gListServ.createNewGroceryItem(gItem);
			
			return new ResponseEntity<>("Grocery item sucessfully added", HttpStatus.OK);
		}
	}
	
	@DeleteMapping("/{id}/item/delete/{itemId}")
	public ResponseEntity<String> deleteGroceryItem(@PathVariable("id") int id, @PathVariable("itemId") int itemId) {
		GroceryList gList = gListServ.retrieveGroceryListById(id);
		if(gList == null) {
			return new ResponseEntity<>("Grocery list not found", HttpStatus.NOT_FOUND);
		} else {
			gListServ.deleteGroceryItem(itemId);
			return new ResponseEntity<>("Grocery item sucessfully deleted", HttpStatus.OK);
		}
	}
}
