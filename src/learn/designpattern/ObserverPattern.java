package learn.designpattern;

import java.util.ArrayList;
import java.util.List;

public class ObserverPattern {

	private static class Subject{
		private List<Observer> observers;
		private String message;
		
		public Subject() {
			observers = new ArrayList<>();
		}
		
		public String getMessage() {
			return this.message;
		}
		
		public void subscribe(Observer observer) {
			if(!this.observers.contains(observer)) {
				this.observers.add(observer);
			}
		}
		
		public void unsubscribe(Observer observer) {
			if(this.observers.contains(observer)) {
				this.observers.remove(observer);
			}
		}
		
		public void sendMessage(String message) {
			this.message = message;
			this.notifyObservers();
		}
		
		private void notifyObservers() {
			for(Observer observer : this.observers) {
				observer.receiveMessage();
			}
		}
		
	}
	
	private static abstract class Observer{
		protected Subject subject;
		public abstract void receiveMessage();
		public void sendMessage(String message) {
			this.subject.sendMessage(message);
		}
	}
	
	private static class User1 extends Observer{

		public User1(Subject subject) {
			this.subject = subject;
			this.subject.subscribe(this);
		}
		
		@Override
		public void receiveMessage() {
			System.out.println("Message received at User1: " + this.subject.getMessage());
		}
		
	}
	
	private static class User2 extends Observer{

		public User2(Subject subject) {
			this.subject = subject;
			this.subject.subscribe(this);
		}
		
		@Override
		public void receiveMessage() {
			System.out.println("Message received at User2: " + this.subject.getMessage());
		}
		
	}
	
	public static void main(String[] args) {
		Subject subject = new Subject();
		User1 user1 = new User1(subject);
		User2 user2 = new User2(subject);
		user1.sendMessage("I am user1!!!");
		subject.unsubscribe(user2);
		user1.sendMessage("I am user1 , user2 removed!!!");
	}

}
