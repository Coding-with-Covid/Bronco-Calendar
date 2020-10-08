package com.example.springboot;

public class Concatenation {
	
	public String concatenateString(String word1, String word2){
		return word1 + word2;
	}
	
	public boolean compareString(String word1, String word2)
	{
		if(word1.equalsIgnoreCase(word2)){
			return true;
		}
		else{
			return false;
		}
	}
}
