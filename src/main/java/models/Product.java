package models;

import java.math.BigDecimal;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Product {
	int pid;
	String pname;
	int catId;
	BigDecimal uprice;

	public Product(int id, String name, int cid, BigDecimal price) {
		pid = id;
		pname = name;
		catId = cid;
		uprice = price;
	}

	public JSONObject toJSONObject() {
		try {
			Class.forName("org.json.JSONObject");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		JSONObject obj = new JSONObject();
		try {
			obj.put("product_id", pid);
			obj.put("product_name", pname);
			obj.put("category_id", catId);
			obj.put("unit_price", uprice);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return obj;
	}

}
