package com.stomal;


public class SearchTree implements NodeList{

	private ListItem root;
	
	public SearchTree(ListItem root) {
		this.root = root;
	}
	
	@Override
	public ListItem getRoot() {
		return this.root;
	}
	
	@Override
	public boolean addItem(ListItem item) {
		if(this.root == null) {
			this.root = item;
			return true;
		}
		ListItem currentItem = this.root;
		while(currentItem != null) {
			int comparsion = currentItem.compareTo(item);
			if(comparsion > 0) { //current is greater, add it to the left subtree
				if(currentItem.previous() == null) { //left link is null so there is a place for this new item
					currentItem.setPrevious(item);
					return true;
				}
				else {
					currentItem = currentItem.previous();//left link wasnt null so move current item to the previous one and compare again
				}
			}
			else if(comparsion < 0) {//current is smaller, add new item to the right subtree
				if(currentItem.next() == null) {
					currentItem.setNext(item);
					return true;
				}
				else {
					currentItem = currentItem.next();
				}
			}
			else { //items are equal so do not add the new item
				System.out.println("Item "+item.getValue()+" is already on the list, not adding.");
				return false;
			}
		}
		return false;
	}

	@Override
	public boolean removeItem(ListItem item) {
		if(item != null) {
			System.out.println("Removing "+item.getValue());
		}
		ListItem currentItem = this.root;
		ListItem parentItem = currentItem; //this will be needed to performRemoval coz i cant get back on the tree
		while(currentItem != null) {
			int comparsion = currentItem.compareTo(item);
			if(comparsion > 0) { //currentItem is greater, move to the left
				if(currentItem.previous() != null) {
					parentItem = currentItem;
					currentItem = currentItem.previous();
				}
				else { //there is no more items to compare
					System.out.println("There is no "+item.getValue()+" on the list");
					return false;
				}
			}
			else if(comparsion < 0) { //currentItem is smaller, move to the right
				if(currentItem.next() != null) {
					parentItem = currentItem;
					currentItem = currentItem.next();
				}
				else {
					System.out.println("There is no "+item.getValue()+" on the list");
					return false;
				}
			}
			else {//item found, perform removal
				performRemoval(currentItem, parentItem);
				return true;
			}
		}
		
		return false;
	}
	/*
	 * 0. Item has no children
	 * 1. Item has only left child
	 * 2. Item has only right child
	 * 3. Item has both children
	 */
	private void performRemoval(ListItem item, ListItem parent) {
		if(item.next() == null && item.previous() == null) {// 		0 -----------
			if(parent.previous() == item) { //item is left child
				parent.setPrevious(null);
			}
			else if(parent.next() == item) { //item is right child
				parent.setNext(null);
			}
			else if(parent == item) {
				this.root = null;
				System.out.println("The list is empty");
			}
		}
		else if(item.next() == null) { // 							1------------
			if(parent.previous() == item) { //item is left child
				parent.setPrevious(item.previous());
			}
			else if(parent.next() == item) { //item is right child
				parent.setNext(item.previous());
			}
			else {
				this.root = item.previous(); //item == root
			}
		}
		else if(item.previous() == null) { //						2------------
			if(parent.previous() == item) { //item is left child
				parent.setPrevious(item.next());
			}
			else if(parent.next() == item) { //item is right child
				parent.setNext(item.next());
			}
			else {
				this.root = item.next(); //item == root
			}
		}
		else { //													3-------------
			//Go to the right subtree, if possible go left as much as possible to find the smallest number
			ListItem current = item.next(); //going to right subtree
			ListItem currentParent = item;
			while(current.previous() != null) {//looking after smallest value in right subtree
				currentParent = current;
				current = current.previous();
			}
			item.setValue(current.getValue()); //setting deleting item's value to minimum found in right subtree
			if(currentParent.getValue() == current.getValue()) { //after moving to right subtree there was no "previous element"
				item.setNext(current.next());
			}
			else {
				currentParent.setPrevious(current.next()); //if there was no next element, then its null, if there was then it is no problem either
			}
			
		}
		
    }

	@Override
	public void traverse(ListItem root) { //recursive method - go previous till null, print, go next element and again max previous etc.
		if(root != null) {
			traverse(root.previous());
			System.out.println(root.getValue());
			traverse(root.next());
		}
		
	}

	
	
}
