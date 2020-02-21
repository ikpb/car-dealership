/*JDBC - Java Database Connectivity
* - conneciont -> allows us to connect to our db
* -Statement-> Do a raw SQL query
* -prepared Statement -> precompiles the SQL string without parameters,
* once parameters are added, they are only treated as values, never use
* keywords
* Callable statement -> same idea as ps, also prevents SQL 
* injection but is typically used for stored procedures(PL/SQL)
**/
package com.example.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.model.Pet;

public class PetDaoImpl implements PetDao {
	//There should be system variables once connected to AWS:RDS
	//Note: STS will not have accesss to new environment variables until
	//you restart it
	private static String url ="jdbc:postgresql://localhost:5000/postgres";
	//jdbc:postgresql://host:port/database_name
	private static String username="postgres";
	private static String password="root";

	public void insertPet(Pet p) {
		try{
			Connection conn = DriverManager.getConnection(url,username,password);
			//puttingn in a native sql query utilizing a perpared statemtn
			PreparedStatement ps = conn.prepareStatement("Insert INTO Pets VALUES(?,?)");
			ps.setString(1,p.getName());
			//seting the first question mark to be the name that is passed as
			//paramenter, that belongs to our pet object
			ps.setString(2,p.getType() );
			//setting the second question mark to be the type that belongs
			//to our pet object
			ps.execute();
			//allows us to execute a query without a result
			conn.close();
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public Pet selectPetByName(String name) {
		Pet pet = null;
		try{
			Connection conn = DriverManager.getConnection(url,username,password);
			//puttingn in a native sql query utilizing a perpared statemnt
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM Pets WHERE name=?");
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();
			//we are executing the query and storing the result set in 
			//a Resultset
			while(rs.next()) {
				pet = new Pet(rs.getString("name"), rs.getString("type"));
			}
			
			ps.execute();
			//allows us to execute a query without a result
			conn.close();
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pet;
	}

	public List<Pet> selectAllPets() {
		List<Pet> pets = new ArrayList<Pet>();
		try{
			Connection conn = DriverManager.getConnection(url,username,password);
			//putting in a native sql query utilizing a perpared statemnt
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM Pets");
			ResultSet rs = ps.executeQuery();
			//we are executing the query and storing the result set in 
			//a Resultset
			while(rs.next()) {
				pets.add(new Pet(rs.getString("name"), rs.getString("type")));
			}
			
			ps.execute();
			//allows us to execute a query without a result
			conn.close();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}return pets;
	}

	public void updatePet(Pet p) {
		try{
			Connection conn = DriverManager.getConnection(url,username,password);
			//putting in a native sql query utilizing a perpared statemnt
			PreparedStatement ps = conn.prepareStatement("UPDATE Pets SET type=? WHERE name=?");
			ps.setString(1, p.getType());
			ps.setString(2, p.getName());
			ResultSet rs = ps.executeQuery();
			//we are executing the query and storing the result set in 
			//a Resultset
			ps.execute();
			//allows us to execute a query without a result
			conn.close();
	
		
	}catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}

	public void deletePet(Pet p) {
		try{
			Connection conn = DriverManager.getConnection(url,username,password);
			//putting in a native sql query utilizing a perpared statemnt
			PreparedStatement ps = conn.prepareStatement("DELETE FROM Pets WHERE name=?");
			ps.setString(1, p.getName());
			ps.executeUpdate();
			//allows us to execute a query without a result
			conn.close();
	}catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}


}

	
//	public void deletePet(Pet p) {
//		// TODO Auto-generated method stub
//
//	}
//
//}
