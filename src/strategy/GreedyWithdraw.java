package strategy;

import java.util.ArrayList;
import java.util.List;

import coinpurse.Valuable;

public class GreedyWithdraw implements WithdrawStrategy {

	public GreedyWithdraw() {}

	@Override
	public List<Valuable> withdraw(Valuable amountin,List<Valuable> list) {
		double amount = amountin.getValue();
		ArrayList<Valuable> templist = new ArrayList<Valuable>();
		for(int i = list.size() -1 ; i >= 0 ; i--){
			if(amount - list.get(i).getValue() >= 0){
				amount = amount - list.get(i).getValue();
				templist.add(list.get(i));
			}
		}
		return templist;
	}
}
