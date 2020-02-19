package com.king.dao;

public interface DAO {
public void writeToFile();
	public static <T>T readFromFile(T t) {
		return t;
	}
}
