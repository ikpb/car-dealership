package com.example.maindriver;

import com.example.dao.PetDaoImpl;
import com.example.model.Pet;

public class MainDriver {

	public static void main(String[] args) {
		PetDaoImpl petDaoImpl = new PetDaoImpl();
//		Pet petOne = new Pet("Siri", "The Doggo");
		Pet petTwo = new Pet("Zed","The Cat");
		Pet petThree = new Pet("bobby", "The Fox");
		
		
		
		petDaoImpl.insertPet(petTwo);
//		petDaoImpl.insertPet(petTwo);
//		System.out.println(petDaoImpl.selectPetByName("Siri"));

//		//to update pet
//		petOne.setType("The Pupper");
//		petDaoImpl.updatePet(petOne);
//		
//		System.out.println(petDaoImpl.selectAllPets());
//		petDaoImpl.deletePet(petThree);
//		System.out.println(petDaoImpl.selectAllPets());
	}

}
