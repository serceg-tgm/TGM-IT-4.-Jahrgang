/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.camel.example.etl;

import java.util.List;

import javax.persistence.EntityManager;

import org.apache.camel.Converter;
import org.apache.camel.Exchange;
import org.apache.camel.component.jpa.JpaConstants;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * Die Informationen zu einer Person werden einem neu angelegten oder bestehendem Kunden aus der Datenbank zugewiesen.
 *
 * @author https://github.com/apache/camel/tree/master/examples/camel-example-etl
 * @author Erceg (serceg@student.tgm.ac.at) (Kommentare)
 * @author Kritzl (mkritzl@student.tgm.ac.at) (Kommentare)
 * @version 20150219
 */
@Converter
public final class CustomerTransformer {

    private static final Logger LOG = LoggerFactory.getLogger(CustomerTransformer.class);

    private CustomerTransformer() {
    }

    /**
     * Die Informationen zu einer Person werden einem neu angelegten oder bestehendem Kunden aus der Datenbank zugewiesen.
     * Dadurch ist eine Transformationen einer Person zu einem Kunden vollzogen worden.
     *
     * @param doc Beinhaltet Informationen zu einer bestimmten Person
     * @param exchange Beinhaltet alle Metadaten zur Verbindung der Datenbank
     * @return Den passenden Kunden zu der angegebenen Person
     * @throws Exception
     */
    @Converter
    public static CustomerEntity toCustomer(PersonDocument doc, Exchange exchange) throws Exception {
        //Aus dem "exchange" wird die Property entityManager geholt
        EntityManager entityManager = exchange.getProperty(JpaConstants.ENTITY_MANAGER, EntityManager.class);
        //Aus dem "exchange" wird das TransactionTemplate geholt.
        TransactionTemplate transactionTemplate = exchange.getContext().getRegistry().lookupByNameAndType("transactionTemplate", TransactionTemplate.class);

        String user = doc.getUser();
        //Suchen des Kunden in der Datenbank
        CustomerEntity customer = findCustomerByName(transactionTemplate, entityManager, user);

        //Transferieren der Daten der Person zu dem Kunden.
        customer.setUserName(user);
        customer.setFirstName(doc.getFirstName());
        customer.setSurname(doc.getLastName());
        customer.setCity(doc.getCity());

        LOG.info("Created object customer: {}", customer);
        return customer;
    }

    /**
     * Findet anhand eines Usernamens aus der Datenbank den entsprechenden Eintrag. Sollte dieser nicht vorhanden sein,
     * wird ein neuer Kunde mit diesem Namen angelegt.
     *
     * @param transactionTemplate Verbindung fuer Transaktionen mit der Datenbank
     * @param entityManager Direkte Kommunikation mit der Datenbank
     * @param userName Gesuchter Kundeneintrag mit diesem Namen
     * @return Den gesuchten Kunden
     * @throws Exception
     */
    private static CustomerEntity findCustomerByName(TransactionTemplate transactionTemplate, final EntityManager entityManager, final String userName) throws Exception {
        //Fuehrt die Transaktion mit der Datenbank aus
        return transactionTemplate.execute(new TransactionCallback<CustomerEntity>() {
            public CustomerEntity doInTransaction(TransactionStatus status) {
                entityManager.joinTransaction();
                //Sucht aufgrund des im "CostumerEntity" angelegten SQL-Statements mit dem gegebenen Namen nach dem richtigen Eintrag in der Datenbank
                List<CustomerEntity> list = entityManager.createNamedQuery("findCustomerByUsername", CustomerEntity.class).setParameter("userName", userName).getResultList();
                CustomerEntity answer;
                //Sollte ein angefragter Kunde nicht vorhanden sein, wird ein neuer Kunde mit diesem Namen erstellt.
                if (list.isEmpty()) {
                    answer = new CustomerEntity();
                    answer.setUserName(userName);
                    LOG.info("Created a new CustomerEntity {} as no matching persisted entity found.", answer);
                //Ansonsten wird der erste Eintrag zuruekgegeben.
                } else {
                    answer = list.get(0);
                    LOG.info("Found a matching CustomerEntity {} having the userName {}.", answer, userName);
                }

                return answer;
            }
        });
    }

}