package coinpurse;

import java.util.Comparator;

public class ValueComparator implements Comparator<Valuable>{

	@Override
	public int compare(Valuable a, Valuable o) {
		if(a.getValue() < o.getValue()){
            return -1;
        }
        else if(a.getValue() > o.getValue()){
            return 1;
        }
        else{
            return 0;
        }
	}

}
