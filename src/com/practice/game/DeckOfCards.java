package com.practice.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public class DeckOfCards {
	
	static HashMap<String,Integer> spadeCards = new HashMap<String,Integer>();
	static HashMap<String,Integer> heartCards = new HashMap<String,Integer>();
	static HashMap<String,Integer> diamondCards = new HashMap<String,Integer>();
	static HashMap<String,Integer> clubCards = new HashMap<String,Integer>();
	static boolean hasValidInput=true;
	static boolean isSpade=false;
	static boolean isHeart=false;
	static boolean isDiamond=false;
	static boolean isClub=false;
	static boolean hasSameSuit=true;
	static boolean sequence=true;
	
	static {
		spadeCards.put("S#A",1);
		spadeCards.put("S#2",2);
		spadeCards.put("S#3",3);
		spadeCards.put("S#4",4);
		spadeCards.put("S#5",5);
		spadeCards.put("S#6",6);
		spadeCards.put("S#7",7);
		spadeCards.put("S#8",8);
		spadeCards.put("S#9",9);
		spadeCards.put("S#10",10);
		spadeCards.put("S#J",11);
		spadeCards.put("S#Q",12);
		spadeCards.put("S#K",13);
		
		heartCards.put("H#A",1);
		heartCards.put("H#2",13);
		heartCards.put("H#3",12);
		heartCards.put("H#4",11);
		heartCards.put("H#5",10);
		heartCards.put("H#6",9);
		heartCards.put("H#7",8);
		heartCards.put("H#8",7);
		heartCards.put("H#9",6);
		heartCards.put("H#10",5);
		heartCards.put("H#J",4);
		heartCards.put("H#Q",3);
		heartCards.put("H#K",2);
		
		diamondCards.put("D#A",1);
		diamondCards.put("D#2",13);
		diamondCards.put("D#3",12);
		diamondCards.put("D#4",11);
		diamondCards.put("D#5",10);
		diamondCards.put("D#6",9);
		diamondCards.put("D#7",8);
		diamondCards.put("D#8",7);
		diamondCards.put("D#9",6);
		diamondCards.put("D#10",5);
		diamondCards.put("D#J",4);
		diamondCards.put("D#Q",3);
		diamondCards.put("D#K",2);
		
		clubCards.put("C#A",1);
		clubCards.put("C#2",13);
		clubCards.put("C#3",12);
		clubCards.put("C#4",11);
		clubCards.put("C#5",10);
		clubCards.put("C#6",9);
		clubCards.put("C#7",8);
		clubCards.put("C#8",7);
		clubCards.put("C#9",6);
		clubCards.put("C#10",5);
		clubCards.put("C#J",4);
		clubCards.put("C#Q",3);
		clubCards.put("C#K",2);
	}
	
	public static void main(String[] args) {				

		Scanner sc=null;
		try {
			sc=new Scanner(System.in);
			String cards=sc.next();		
			if(checkSequence(cards.toUpperCase())) {
				System.out.println("Cards in a sequence");
			}else {
				if(!sequence) 
					System.out.println("Cards not in a sequence");
			}
		}catch (Exception e) {
			e.printStackTrace();			
        }finally { 
            sc.close(); 
        }  
		
	}
	
	public static boolean checkSequence(String cards) {
		
		String[] cardsList=cards.split(",");
		
		ArrayList<Integer> ranks=new ArrayList<Integer>();
		ranks=validateAndFindCardRank(ranks,cardsList);
				
		if (!hasValidInput){
			System.out.println("Invalid Input Values");
			return false;
		}else if (!hasSameSuit){
			System.out.println("Cards are not from same suit");
			return false;
		}else {
			sequence=processRanksSequences(ranks);
		}		
				
		return sequence;		
	}

	private static boolean processRanksSequences(ArrayList<Integer> ranks) {

		if(ranks.get(0) == 1 && ranks.get(1) == 13) {
			for(int i=2;i<ranks.size();i++) {
				if(ranks.get(i) != ranks.get(i-1)-1) {
					return false;
				}
			}
		}else {
			Collections.sort(ranks);
			for(int i=1;i<ranks.size();i++) {
				if(ranks.get(i) - 1 != ranks.get(i-1)) {
					return false;
				}
			}
		}	
		
		return true;
	}

	private static ArrayList<Integer> validateAndFindCardRank(ArrayList<Integer> ranks, String[] cardsList) {
		
		for(String card:cardsList) {
			switch(card.charAt(0)){
				case 'S':
				   if (!isHeart && !isDiamond && !isClub) {
						isSpade=true;
						int rank=spadeCards.get(card) == null ? - 1 : spadeCards.get(card);
						if (rank != -1 ) 
							ranks.add(rank);  
						else 
							hasValidInput=false;						
				   }else {
					   hasSameSuit=false;
				   }
				   break;
				case 'H':
				   if (!isSpade && !isDiamond && !isClub) {
					   isHeart=true;					   
					   int rank=heartCards.get(card) == null ? - 1 : heartCards.get(card);
						if (rank != -1 ) 
							ranks.add(rank);  
						else 
							hasValidInput=false;					   
				   }else {
					   hasSameSuit=false;
				   }
				   break;
				case 'D':
					   if (!isSpade && !isHeart && !isClub) {
						   isDiamond=true;					   
						   int rank=diamondCards.get(card) == null ? - 1 : diamondCards.get(card);
							if (rank != -1 ) 
								ranks.add(rank);  
							else 
								hasValidInput=false;					   
					   }else {
						   hasSameSuit=false;
					   }
					   break;
				case 'C':
					   if (!isSpade && !isHeart && !isDiamond) {
						   isClub=true;					   
						   int rank=clubCards.get(card) == null ? - 1 : clubCards.get(card);
							if (rank != -1 ) 
								ranks.add(rank);  
							else 
								hasValidInput=false;					   
					   }else {
						   hasSameSuit=false;
					   }
					   break;
				default:
					hasValidInput=false;
			}
			if(!hasValidInput || !hasSameSuit) {
				break;
			}
		}
		
		return ranks;
	}
}
