package com.example.dao;

import java.util.List;

import com.example.model.Pet;

public interface PetDao {
	/*
	 *  the purpoes of the DAO interface is to provide us with the guide line for our 
	 *  crud operations to be performed on on objects of type Model (pet)
	 */
	
	//Create
	public void insertPet(Pet p);
	//read
	public Pet selectPetByName(String name);
	public List<Pet> selectAllPets();
	//Update
	public void updatePet(Pet p);
	//Delete
	public void deletePet(Pet p);
}
