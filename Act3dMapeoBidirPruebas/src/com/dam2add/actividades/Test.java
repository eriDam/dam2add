package com.dam2add.actividades;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.RollbackException;
 

public class Test {
		
	public static void main(String[] args) throws RollbackException, Exception {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("UnitPersonas");
		EntityManager em = emf.createEntityManager();	
		
				
		
		try {
			//creo persona
			System.out.println("Creando persona...");
			Persona p=new Persona ("29204528w",32,"Eri", null, null );	
			em.getTransaction().begin();//se activa la transaccion mediante el m�todo begin 
			em.persist(p);//va a almacenar la informacion de p en la bd, el persist se utiliza para persistir nuevas entidades en la bd
			em.getTransaction().commit();//y finaliza cuando hay un commit
			System.out.println("***Persona creada***"); 
			p.print();
			 
			
			//Creo Empresa
			System.out.println("Creando Empresa...");
			em.getTransaction().begin();
			Query q = em.createQuery("SELECT p FROM Persona p");
			p = (Persona) q.getResultList().get(0);	
			Empresa e = new Empresa("El corte", p);
			Nomina nom= new Nomina("Mensual",p);
			em.persist(e);
			em.persist(nom);
			em.getTransaction().commit();
			System.out.println("***Empresa creada ok***");
			e.print();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			em.close();
		}	     

	}
	 
}
