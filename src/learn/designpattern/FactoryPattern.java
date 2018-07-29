package learn.designpattern;

public class FactoryPattern {
	
	private static interface I{
		void foo();
	}
	
	private static class C1 implements I{

		@Override
		public void foo() {
			System.out.println("C1.foo()!!!");
		}
		
	}
	
	private static class C2 implements I{

		@Override
		public void foo() {
			System.out.println("C2.foo()!!!");
		}
		
	}

	private static class IFactory{
		public I getImplForI(int type) {
			if(type == 1) {
				return new C1();
			}
			else
				return new C2();
		}
	}
	
	public static void main(String[] args) {
		IFactory factory = new IFactory();
		factory.getImplForI(1).foo();
		factory.getImplForI(2).foo();
	}

}
