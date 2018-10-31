package learn.designpattern;

import java.util.HashMap;
import java.util.Map;

public class ChainOfResponsibilityATMMachineDemo {

	static enum Denomination {
		TEN("TEN", 10),
		TWENTY("TWENTY", 20),
		FIFTY("FIFTY", 50),
		HUNDRED("HUNDRED", 100),
		TWOHUNDRED("TWOHUNDRED", 200),
		FIVEHUNDRED("FIVEHUNDRED", 500),
		THOUSAND("THOUSAND", 1000);
		
		String name;
		int value;
		
		Denomination(String name, int value) {
			this.name = name;
			this.value = value;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getValue() {
			return value;
		}

		public void setValue(int value) {
			this.value = value;
		}
		
	}
	
	static class ATMMachine {
		Map<Denomination, Integer> moneyCounter;
		
		public ATMMachine() {
			moneyCounter = new HashMap<>();
		}
		
		public void addNotes(Denomination denomination, int amount) {
			int counter = 0;
			if(moneyCounter.containsKey(denomination)) {
				counter = moneyCounter.get(denomination);
			}
			counter += amount;
			moneyCounter.put(denomination, counter);
		}

		public void removeNotes(Denomination denomination, int amount) {
			if(!moneyCounter.containsKey(denomination)
					|| amount > moneyCounter.get(denomination)) {
				System.err.println("Unable to withdraw the amount !!!");
			} else {
				System.out.println("Withdrawing " + amount + " no of " +
						denomination.getName() + " notes...");
				moneyCounter.put(denomination, moneyCounter.get(denomination) - amount);
			}
		}
		
		public int countNotes(Denomination denomination) {
			return  moneyCounter.containsKey(denomination) ?
					moneyCounter.get(denomination) : 0;
		}
		
	}
	
	static interface DispenseChain {
		public void setATMMachine(ATMMachine atmMachine);
		public void setNextDispenseChain(DispenseChain nextChain);
		public void dispense(int amount);
	}
	
	static class DollarTenDispenser implements DispenseChain {

		ATMMachine atmMachine;
		DispenseChain nextChain;

		@Override
		public void setNextDispenseChain(DispenseChain nextChain) {
			this.nextChain = nextChain;
		}

		public void dispense(int amount) {
			Denomination d = Denomination.TEN;
			if (amount >= d.getValue()) {
				int counter = Math.min(amount / d.getValue(),
						atmMachine.countNotes(d));
				if(counter > 0) {
					atmMachine.removeNotes(d, counter);
				}
				amount -= counter * d.getValue();
				if(nextChain != null) {
					nextChain.dispense(amount);
				} else {
					System.err.println("Unable to dispense the amount !!!");
				}
			}
		}

		@Override
		public void setATMMachine(ATMMachine atmMachine) {
			this.atmMachine = atmMachine;
		}
		
	}
	
	static class DollarTwentyDispenser implements DispenseChain {

		ATMMachine atmMachine;
		DispenseChain nextChain;

		@Override
		public void setNextDispenseChain(DispenseChain nextChain) {
			this.nextChain = nextChain;
		}

		public void dispense(int amount) {
			Denomination d = Denomination.TWENTY;
			if (amount >= d.getValue()) {
				int counter = Math.min(amount / d.getValue(),
						atmMachine.countNotes(d));
				if(counter > 0) {
					atmMachine.removeNotes(d, counter);
				}
				amount -= counter * d.getValue();
				if(nextChain != null) {
					nextChain.dispense(amount);
				} else {
					System.err.println("Unable to dispense the amount !!!");
				}
			}
		}

		@Override
		public void setATMMachine(ATMMachine atmMachine) {
			this.atmMachine = atmMachine;
		}
		
	}
	
	static class DollarHundredDispenser implements DispenseChain {

		ATMMachine atmMachine;
		DispenseChain nextChain;

		@Override
		public void setNextDispenseChain(DispenseChain nextChain) {
			this.nextChain = nextChain;
		}

		public void dispense(int amount) {
			Denomination d = Denomination.HUNDRED;
			if (amount >= d.getValue()) {
				int counter = Math.min(amount / d.getValue(),
						atmMachine.countNotes(d));
				if(counter > 0) {
					atmMachine.removeNotes(d, counter);
				}
				amount -= counter * d.getValue();
				if(nextChain != null) {
					nextChain.dispense(amount);
				} else {
					System.err.println("Unable to dispense the amount !!!");
				}
			}
		}

		@Override
		public void setATMMachine(ATMMachine atmMachine) {
			this.atmMachine = atmMachine;
		}
		
	}
	
	static class ATM {
		ATMMachine atmMachine;
		DispenseChain c1, c2, c3;
		
		public ATM() {
			atmMachine = new ATMMachine();
			c1 = new DollarTenDispenser();c1.setATMMachine(atmMachine);
			c2 = new DollarTwentyDispenser();c2.setATMMachine(atmMachine);
			c3 = new DollarHundredDispenser();c3.setATMMachine(atmMachine);
			c1.setNextDispenseChain(c2);c2.setNextDispenseChain(c3);
		}
		
		public void addMoney(Denomination d, int amount) {
			atmMachine.addNotes(d, amount);
		}
		
		public void withDraw(int amount) {
			c1.dispense(amount);
		}
		
	}
	
	public static void main(String[] args) {
		ATM atm = new ATM();
		atm.addMoney(Denomination.HUNDRED, 20);
		atm.addMoney(Denomination.TEN, 23);
		atm.addMoney(Denomination.TWENTY, 35);
		atm.withDraw(4700);
	}
}
