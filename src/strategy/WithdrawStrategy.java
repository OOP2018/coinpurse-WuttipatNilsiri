package strategy;

import java.util.List;

import coinpurse.Valuable;

public interface WithdrawStrategy {
	public List<Valuable> withdraw(Valuable amountin,List<Valuable> list);
}
