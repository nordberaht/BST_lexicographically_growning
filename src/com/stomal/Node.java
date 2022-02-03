package com.stomal;

public class Node extends ListItem {
	
	public Node(Object value) {
		super(value);
	}

	@Override
	protected ListItem next() {
		return this.rightLink;
	}

	@Override
	protected void setNext(ListItem item) {
		this.rightLink = item;
	}

	@Override
	protected ListItem previous() {
		return this.leftLink;
	}

	@Override
	protected void setPrevious(ListItem item) {
		this.leftLink = item;
	}

	@Override
	protected int compareTo(ListItem item) {
		return ((String)this.getValue()).compareTo(((String)item.getValue()));
	}

	
}
