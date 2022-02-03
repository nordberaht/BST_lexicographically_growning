package com.stomal;

public class MyLinkedList implements NodeList {

	private ListItem root;
	
	public MyLinkedList(ListItem root) {
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
			if(comparsion > 0) { //currentItem is greater so add new item before
				if(currentItem.previous() == null) { //set new root
					currentItem.setPrevious(item);
					item.setNext(currentItem);
					this.root = item;
					return true;
				}
				else {
					item.setPrevious(currentItem.previous());
					item.setNext(currentItem);
					currentItem.previous().setNext(item);
					currentItem.setPrevious(item);
					return true;
				}
			}
			else if(comparsion < 0) { //currentItem is smaller so add new item after
				if(currentItem.next() == null) {
					currentItem.setNext(item);
					item.setPrevious(currentItem);
					return true;
				}
				else {
					currentItem = currentItem.next();
				}
			}
			else { //currentItem is equal
				System.out.println("Item " + item.getValue() + " is already on the list, not adding");
				currentItem = currentItem.next();
			}
		}
		
		return false;
	}

	@Override
	public boolean removeItem(ListItem item) {
		ListItem currentItem = this.root;
		while(currentItem != null) {
			int comparsion = currentItem.compareTo(item);
			if(comparsion < 0) {
				currentItem = currentItem.next();
			}
			else if(comparsion == 0) {
				if(currentItem.previous() == null) { //deleting item is root
					this.root = this.root.next();
					this.root.previous().setNext(null);
					this.root.setPrevious(null);
					return true;
				}
				else if(currentItem.next() == null) { //deleting last item
					currentItem.previous().setNext(null);
					currentItem.setPrevious(null);
					return true;
				}
				else { //deleting middle item
					currentItem.previous().setNext(currentItem.next());
					currentItem.next().setPrevious(currentItem.previous());
					currentItem.setPrevious(null);
					currentItem.setNext(null);
					return true;
				}
			}
			else {
				System.out.println("You dont have " + item.getValue() + " on your list");
				return false;
			}
			
		}
		return false;
	}

	@Override
	public void traverse(ListItem root) {
		while(root != null) {
			System.out.println(root.getValue());
			root = root.next();
		}
		
	}

}
