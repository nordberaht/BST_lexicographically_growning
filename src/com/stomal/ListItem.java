package com.stomal;

public abstract class ListItem {

	protected ListItem leftLink;
	protected ListItem rightLink;
	
	protected Object value;
	
	public ListItem(Object value) {
		this.value = value;
	}
	
	protected abstract ListItem next();
	protected abstract void setNext(ListItem item);
	protected abstract ListItem previous();
	protected abstract void setPrevious(ListItem item);
	protected abstract int compareTo(ListItem item);

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}
	
	
}
