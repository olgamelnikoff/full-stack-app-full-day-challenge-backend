package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.model.GroceryList;

public interface GroceryListDao extends JpaRepository<GroceryList, Integer> {
	public List <GroceryList> findAll();
	public GroceryList findById(int id);
}
