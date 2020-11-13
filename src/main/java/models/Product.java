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

	public Product() {
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public int getCatId() {
		return catId;
	}

	public void setCatId(int catId) {
		this.catId = catId;
	}

	public BigDecimal getUprice() {
		return uprice;
	}

	public void setUprice(BigDecimal uprice) {
		this.uprice = uprice;
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
