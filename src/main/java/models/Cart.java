package models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.json.JSONException;
import org.json.JSONObject;

@Entity
@Table(name = "carts")
public class Cart {
	@Id
	@GeneratedValue
	@Column(name = "cart_id")
	private int id;

	@Column(name = "cart_owner")
	private String owner;

	@Column(name = "created_time")
	private Date time;

	public Cart() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date datetime) {
		this.time = datetime;
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
			obj.put("cart_id", id);
			obj.put("cart_owner", owner);
			obj.put("created_time", time);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return obj;
	}
}
