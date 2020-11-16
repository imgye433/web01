package models;

import javax.persistence.*;

import org.json.JSONException;
import org.json.JSONObject;

@Entity
@Table(name = "products")
public class Product {
	@Id
	@GeneratedValue
	@Column(name = "product_id")
	private int id;

	@Column(name = "product_name")
	private String name;

	@Column(name = "unit_price")
	private float price;

	public Product() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
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
			obj.put("product_id", id);
			obj.put("product_name", name);
			obj.put("unit_price", price);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return obj;
	}

}
