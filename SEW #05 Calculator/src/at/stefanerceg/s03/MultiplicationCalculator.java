package at.stefanerceg.s03;

import java.util.ArrayList;
import java.util.List;

/**
* 
* Die Werte aus der Liste werden multipliziert. <br>
* 
* @author Stefan Erceg
* @since 06.11.2014
* @version 20141112
*
*/

public class MultiplicationCalculator implements Calculateable {

	public List<Double> execute(List<Double> l, double mod) {
		
		/* eine neue ArrayList wird erstellt, in die alle neuen veränderten Werte hinzugefügt werden */
		ArrayList<Double> temp = new ArrayList<Double>();
		
		/* alle Werte aus der Liste werden durchgegangen und addiert */
		
		for (int i = 0; i < l.size(); i++) {
			/* die Werte werden in ein double-Datentyp gecastet */
			temp.add(i, ((double) l.get(i)) * mod);
        }
        
        return temp;
	
	}

}
