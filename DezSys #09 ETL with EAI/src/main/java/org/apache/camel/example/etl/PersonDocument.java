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

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Ein Datensatz, der in ein XML geschrieben oder ausgelesen werden kann
 *
 * @author https://github.com/apache/camel/tree/master/examples/camel-example-etl
 * @author Erceg (serceg@student.tgm.ac.at) (Kommentare)
 * @author Kritzl (mkritzl@student.tgm.ac.at) (Kommentare)
 * @version 20150219
 */

//Root-Element in dem XML-File
@XmlRootElement(name = "person")
//Spezifiziert, dass Felder serialisiert werden sollen
@XmlAccessorType(XmlAccessType.FIELD)
public class PersonDocument {
    //Definiert ein Attribut des XML-Files
    @XmlAttribute
    private String user;

    //Elemente des XML-Files
    @XmlElement
    private String firstName;
    @XmlElement
    private String lastName;
    @XmlElement
    private String city;

    @Override
    public String toString() {
        return "Person[user: " + user + "]";
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}