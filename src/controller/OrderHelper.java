/**
 * @author SSene - Suzette Senephansiri
 * CIS175 - Spring 2022
 * Feb 2, 2022
 */

package controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.OrderList;

public class OrderHelper {
	static EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("CoffeeOrder");
	
	public void insertItem(OrderList ol) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.persist(ol);
		em.getTransaction().commit();
		em.close();
	}

	@SuppressWarnings("unchecked")
	public List<OrderList> showAllItems(){
		EntityManager em = emfactory.createEntityManager();
		List<OrderList> allItems = em.createQuery("select i from OrderList i").getResultList();
		return allItems;
	}
	
	public void deleteItem(OrderList toDelete) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<OrderList> typedQuery = em.createQuery("select ol from OrderList ol where ol.name = :selectedName and ol.drink = :selectedDrink", OrderList.class);
		
		typedQuery.setParameter("selectedName", toDelete.getName());
		typedQuery.setParameter("selectedDrink", toDelete.getDrink());
		
		typedQuery.setMaxResults(1);
		
		OrderList result = typedQuery.getSingleResult();
		
		em.remove(result);
		em.getTransaction().commit();
		em.close();
	}
	
	public OrderList searchDrinkByNum(int numToEdit){
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		OrderList found = em.find(OrderList.class, numToEdit);
		em.close();
		return found;
	}
	
	public void updateItem(OrderList toEdit) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.merge(toEdit);
		em.getTransaction().commit();
		em.close();
	}
	
	public List<OrderList> searchDrinkByName(String customerName) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<OrderList> typedQuery = em.createQuery("select ol from OrderList ol where ol.name = :selectedName", OrderList.class);
		typedQuery.setParameter("selectedName", customerName);
		
		List<OrderList> foundItems = typedQuery.getResultList();
		em.close();
		
		return foundItems;
	}
	
	public List<OrderList> searchDrinkByDrink(String drinkName) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<OrderList> typedQuery = em.createQuery("select ol from OrderList ol where ol.drink = :selectedDrink", OrderList.class);
		typedQuery.setParameter("selectedDrink", drinkName);
		
		List<OrderList> foundItems = typedQuery.getResultList();
		em.close();
		
		return foundItems;
	}
	
	public void cleanUp() {
		emfactory.close();
	}
}
