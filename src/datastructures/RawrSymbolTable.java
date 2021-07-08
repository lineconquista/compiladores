package datastructures;
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
		return map.get(symbolName);
	}
}
