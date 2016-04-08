package TAGLServer;

import java.util.HashMap;


@SuppressWarnings("serial")
public class TableHachage<K,V> {

	private HashMap<K,V> h;
	
	public TableHachage(){
		h = new HashMap<K,V>();
	}
	
	public void set(K k,V v){
		h.put(k, v);
	}
	
	public V get(K k){
		return h.get(k);
	}

	public void del(K k){
		h.remove(k);
	}
	
	public void incr(K k){
		if (h.containsKey(k)){
			V v = h.get(k);
			if (v instanceof Integer){
				Integer i = (Integer)v;
				i++;
				h.remove(k);
				h.put(k,(V)i);
			}
			else if (v instanceof Float){
				Float i = (Float)v;
				i++;
				h.remove(k);
				h.put(k,(V)i);
			}
			else if (v instanceof Double){
				Double i = (Double)v;
				i++;
				h.remove(k);
				h.put(k,(V)i);
			}
		}
		else {
			Integer i =1;
			h.put(k,(V)i);
		}
	}
	
	
	
}
