package TAGLClient;

import java.util.ArrayList;
import java.util.HashMap;

public class TableHachage {
	
	private HashMap<Object, Object> h;
	
	public TableHachage(){
		h = new HashMap<Object, Object>();
	}
	
	public void set(Object k, Object v){
		h.put(k, v);
	}
	
	public void get(Object k){
		if (h.containsKey(k)){
			h.get(k);
		}
		else {
			System.out.println("La clé n'existe pas");
		}
	}
	
	public void del(Object k){
		if (h.containsKey(k)){
			h.remove(k);
		}
		else {
			System.out.println("La clé n'existe pas");
		}
	}
	
	public void incr(Object k){
		if (h.containsKey(k)){
			if (h.get(k) instanceof Integer){
				int i = (Integer)h.get(k);
				i++;
				h.put(k, i);
			}
			else if (h.get(k) instanceof Float){
				float i = (Float)h.get(k);
				i++;
				h.put(k, i);
			}
			else if (h.get(k) instanceof Double){
				double i = (Double)h.get(k);
				i++;
				h.put(k, i);
			}
			else {
				System.out.println("la valeur n'est pas un nombre");
			}
		}
		else {
			System.out.println("la clé n'existe pas");
		}
	}
	
	public void rPush(Object k,Object v){
		if (h.containsKey(k)){
			if (h.get(k) instanceof ArrayList){
				ArrayList<Object> list = (ArrayList<Object>)h.get(k);
				list.add(v);
				h.put(k, list);
			}
			else {
				Object vlist = h.get(k);
				ArrayList<Object> list = new ArrayList<>();
				list.add(vlist);
				list.add(v);
				h.put(k, list);
			}
		}
		else {
			ArrayList<Object> list = new ArrayList<>();
			list.add(v);
			h.put(k, list);
		}
	}
	
	public void lPush(Object k,Object v){
		if (h.containsKey(k)){
			if (h.get(k) instanceof ArrayList){
				ArrayList<Object> list = (ArrayList<Object>)h.get(k);
				list.add(0,v);
				h.put(k, list);
			}
			else {
				Object vlist = h.get(k);
				ArrayList<Object> list = new ArrayList<>();
				list.add(v);
				list.add(vlist);
				h.put(k, list);
			}
		}
		else {
			ArrayList<Object> list = new ArrayList<>();
			list.add(v);
			h.put(k, list);
		}
	}
	
	public void lRange(Object k,int begin, int end){
		if (h.containsKey(k)){
			if (h.get(k) instanceof ArrayList){
				ArrayList<Object> list = (ArrayList<Object>)h.get(k);
				if (end<0 && begin>=0){
					for(int i = begin;i<list.size();i++){
						System.out.println(list.get(i).toString());
					}
				}
				else if (begin>=0 && end<list.size()){
					for(int i = begin;i<end;i++){
						System.out.println(list.get(i).toString());
					}
				}
			}
			else {
				Object v = h.get(k);
				System.out.println(v.toString());
			}
		}
		else {
			System.out.println("La clé n'existe pas");
		}
	}
	
	public int lLen(Object k){
		if (h.containsKey(k)){
			if (h.get(k) instanceof ArrayList){
				ArrayList<Object> list = (ArrayList<Object>)h.get(k);
				return list.size();
			}
			else {
				System.out.println("Attention ce n'est pas une liste");
				return 1;
			}
		}
		else {
			System.out.println("La clé n'existe pas");
			return 0;
		}
	}
}
