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

/**
 * Verwendet den Mechanismus von Camel, um das Programm zu starten.
 *
 * @author https://github.com/apache/camel/tree/master/examples/camel-example-etl
 * @author Erceg (serceg@student.tgm.ac.at) (Kommentare)
 * @author Kritzl (mkritzl@student.tgm.ac.at) (Kommentare)
 * @version 20150219
 */
public class Main extends org.apache.camel.spring.Main {
    /**
     * Verwendet den Mechanismus von Camel, um das Programm zu starten.
     *
     * @param args Argumente zum Start des Programms
     * @throws Exception Wenn das Programm nicht erfolgreich gestartet werden konnte,
     * wird eine Exception geworfen.
     */
    public static void main(String... args) throws Exception {
        new Main().run(args);
    }
}