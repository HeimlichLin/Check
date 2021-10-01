package idv.heimlich.check.common.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RowMapList {
	
	private List<RowMap> rowMapList = new ArrayList<RowMap>();

	public void add(RowMap rowMap) {
		this.rowMapList.add(rowMap);
	}
	
	public void remove(RowMap rowMap){
		this.rowMapList.remove(rowMap);
	}
	
	public Iterator<RowMap> iterator() {
		return this.rowMapList.iterator();
	}
	
	public List<RowMap> getRowMapList() {
		return rowMapList;
	}	
	
	public int size() {
		return this.rowMapList.size();
	}

}
