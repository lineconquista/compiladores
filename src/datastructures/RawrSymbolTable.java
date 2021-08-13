package datastructures;
import java.util.ArrayList;
import java.util.HashMap;

public class RawrSymbolTable {
	
	private HashMap<String, RawrSymbol> map;
	
	public RawrSymbolTable() {
		map = new HashMap<String, RawrSymbol>();
	}
	
	public void add (RawrSymbol symbol) {
		map.put(symbol.getName(), symbol);
	}
	
	public boolean exists (String symbolName) {
		return map.get(symbolName) != null;
	}
	
	public RawrSymbol get(String symbolName) {
		RawrSymbol symbol = map.get(symbolName);
		symbol.used();
		
		return symbol;
	}

	public ArrayList <RawrSymbol> getAll(){
		ArrayList <RawrSymbol> list = new ArrayList <RawrSymbol>();
		for (RawrSymbol symbol: map.values()) {
			list.add(symbol);
		}
		return list;
	}
	
	public void checkAllVarUsed() {
		for (RawrSymbol symbol: map.values()) {
			if (!symbol.getUsed()) {
				System.out.println("\nVariable " + symbol.getName() + " declared but not used");
			}
		}
	}
}