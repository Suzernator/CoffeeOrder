/**
 * @author SSene - Suzette Senephansiri
 * CIS175 - Spring 2022
 * Feb 2, 2022
 */

package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="orders")
public class OrderList {
	@Id
	@GeneratedValue
	@Column(name="queue_number")
	private int number;
	@Column(name="customer_name")
	private String name;
	@Column(name="drink_item")
	private String drink;
	
	public OrderList() {
		//default, no-arg constructor
	}
	
	public OrderList(String name, String drink) {
		this.name = name;
		this.drink = drink;
	}
		
	public OrderList(int number, String name, String drink) {
		this.number = number;
		this.name = name;
		this.drink = drink;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDrink() {
		return drink;
	}

	public void setDrink(String drink) {
		this.drink = drink;
	}
	
	public String returnCoffeeOrder() {
		return "Order #" + this.number + ", for " + this.name + ": " + this.drink;
	}
}
