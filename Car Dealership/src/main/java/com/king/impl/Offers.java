package com.king.impl;

import java.util.ArrayList;
import java.util.List;

public class Offers {
	
	List<Offers> offers = new ArrayList<Offers>();
public List<Offers> getOffer(Offers number) {
	offers.add(number);
	return offers;
	
}
}
