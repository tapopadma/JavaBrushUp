package learn.designpattern;

public class SingletonPattern {

	private static class C{
		private static C instance = new C();
		private C(){
			
		}
		public static C getInstance() {
			return instance;
		}
		
		public void showMessage() {
			System.out.println("I'm a singleton class!!!");
		}
	}
	
	public static void main(String[] args) {
		C.getInstance().showMessage();
	}

}
