package org.apache.camel.example.etl;

import org.junit.Test;

/**
 * Die Klasse ist zum Testen der Umsetzung einer Person in einen Kunden zuständig
 *
 * @author Kritzl (mkritzl@student.tgm.ac.at)
 * @version 20150301
 */
public class CustomerTransformerTest {

    /**
     * Leider konnte die folgende Methode aufgrund von Mockito-Problemen nicht fertiggestellt werden.
     * Hierbei sollte die Transformation einer Person in einen Kunden erfolgen.
     *
     * @throws Exception
     */
    @Test
    public void testToCostumer() throws Exception {

//        PersonDocument doc = Mockito.mock(PersonDocument.class);
//        DefaultCamelContext context = Mockito.mock(DefaultCamelContext.class);
//        DefaultExchange exchange = new DefaultExchange(context);
//        Registry registry = Mockito.mock(Registry.class);
//        TransactionTemplate transactionTemplate = new TransactionTemplate();
//        CustomerEntity customer = Mockito.mock(CustomerEntity.class);
//        EntityManager entityManager = Mockito.mock(EntityManager.class);
//
//        Mockito.when(exchange.getProperty("CamelEntityManager", EntityManager.class)).thenReturn(entityManager);
//        Mockito.when(exchange.getContext()).thenReturn(context);
//        Mockito.when(context.getRegistry()).thenReturn(registry);
//        Mockito.when(registry.lookupByNameAndType("transactionTemplate", TransactionTemplate.class));
//
//        Mockito.when(doc.getUser()).thenReturn("mkritzl");
//        Mockito.when(doc.getFirstName()).thenReturn("Martin");
//        Mockito.when(doc.getLastName()).thenReturn("Kritzl");
//        Mockito.when(doc.getCity()).thenReturn("DW");
//
//        ArrayList<CustomerEntity> result = new ArrayList<CustomerEntity>();
//        result.add(customer);
//        Mockito.when(entityManager.createNamedQuery("findCustomerByUsername", CustomerEntity.class).setParameter("userName", "mkritzl").getResultList()).thenReturn(result);
//
//
//        CustomerEntity mkritzl = CustomerTransformer.toCustomer(doc, exchange);
//        assertEquals("Martin", mkritzl.getFirstName());
    }
}
