package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.GroceryItem;
import com.example.model.GroceryList;

@Repository
public interface GroceryItemDao extends JpaRepository <GroceryItem, Integer>{
	//public List<GroceryItem> findByGList(GroceryList g);

}
