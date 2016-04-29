package TAGLServer;

import java.util.ArrayList;
import java.util.HashMap;

public class TableHachage {
	
	private HashMap<Object, Object> h;
	
	public TableHachage(){
		h = new HashMap<Object, Object>();
	}
	
	public int valueOfString(Object v){
		int res = -1;
		if (v instanceof String){
			String st = v.toString(); 
			int i =0;
			/*
			 * res=1 si float
			 * res=0 si int
			 * res=-1 si non reel
			 */
			if (st.charAt(i)=='+' || st.charAt(i)=='-'){
				i++;
			}
			res=0;
			for (i=i;i<st.length();i++){
				if(st.charAt(i)=='.' && res!=1){
					res =1;
				}
				else if(st.charAt(i)=='.' && res==1){
					res=-1;
					break;
				}
				else if (!(st.charAt(i)>='0' && st.charAt(i)<='9')){
					res = -1;
					break;
				}
			}
		}
		return res;
	}
	
	public String set(Object k, Object v){
		if (valueOfString(v) == 0){
			int res = Integer.parseInt((String)v);
			h.put(k, res);
		}
		else if (valueOfString(v) == 1){
			Double res = Double.parseDouble((String)v);
			//System.out.println(res);
			h.put(k, res);
		}
		else {
			h.put(k, v);
		}
		return "";
	}
	
	public String get(Object k){
		if (h.containsKey(k)){
			return h.get(k).toString();
		}
		else {
			//System.out.println("La cle n'existe pas");
			return "La cle n'existe pas";
		}
	}
	
	public String del(Object k){
		if (h.containsKey(k)){
			h.remove(k);
			return "La cle a ete supprime";
		}
		else {
			return "La cle n'existe pas";
		}
	}
	
	public String incr(Object k){
		if (h.containsKey(k)){
			if (h.get(k) instanceof Integer){
				int i = (Integer)h.get(k);
				i++;
				h.put(k, i);
				return "";
			}
			else if (h.get(k) instanceof Float){
				float i = (Float)h.get(k);
				i++;
				h.put(k, i);
				return "";
			}
			else if (h.get(k) instanceof Double){
				double i = (Double)h.get(k);
				i++;
				h.put(k, i);
				return "";
			}
			else {
				return "La valeur n'est pas un nombre";
			}
		}
		else {
			return "La cle n'existe pas";
		}
	}
	
	public String rPush(Object k,Object v){
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
		return "";
	}
	
	public String lPush(Object k,Object v){
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
		return "";
	}
	
	public String lRange(Object k,int begin, int end){
		if (h.containsKey(k)){
			String s="";
			if (h.get(k) instanceof ArrayList){
				ArrayList<Object> list = (ArrayList<Object>)h.get(k);
				if (end<0 && begin>=0){
					for(int i = begin;i<list.size();i++){
						s=s+list.get(i).toString()+ "\n";
					}
					return s;
				}
				else if (begin>=0 && end<list.size()){
					for(int i = begin;i<=end;i++){
						s=s+list.get(i).toString()+" ";
					}
					return s;
				}
				else {
					return "Il n'y a pas autant d'objet dans cette liste";
				}
			}
			else {
				Object v = h.get(k);
				return v.toString();
			}
		}
		else {
			return "La cle n'existe pas";
		}
	}
	
	public String lLen(Object k){
		if (h.containsKey(k)){
			if (h.get(k) instanceof ArrayList){
				ArrayList<Object> list = (ArrayList<Object>)h.get(k);
				return ""+list.size()+"";
			}
			else {
				return "Attention ce n'est pas une liste";
			}
		}
		else {
			return "La cle n'existe pas";
		}
	}
	
	public String lPop(Object k){
		if (h.containsKey(k)){
			if (h.get(k) instanceof ArrayList){
				ArrayList<Object> list = (ArrayList<Object>)h.get(k);
				Object v = list.get(0);
				list.remove(0);
				h.remove(k);
				h.put(k, list);
				return "La valeur supprimee est :"+v.toString();
			}
			else {
				return "Attention ce n'est pas une liste";
			}
		}
		else {
			return "La cle n'existe pas";
		}
	}
	
	public String rPop(Object k){
		if (h.containsKey(k)){
			if (h.get(k) instanceof ArrayList){
				ArrayList<Object> list = (ArrayList<Object>)h.get(k);
				Object v = list.get(list.size()-1);
				list.remove(list.size()-1);
				h.remove(k);
				h.put(k, list);
				return "La valeur supprimee est :"+v.toString();
			}
			else {
				return "Attention ce n'est pas une liste";
			}
		}
		else {
			return "La cle n'existe pas";
		}
	}
}
