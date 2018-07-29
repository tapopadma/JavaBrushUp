package learn.designpattern;

public class AbstractFactoryPattern {

	private static interface I1{
		void foo1();
	}
	
	private static class C11 implements I1{

		@Override
		public void foo1() {
			System.out.println("C11.foo1()");
		}
		
	}

	private static class C12 implements I1{

		@Override
		public void foo1() {
			System.out.println("C12.foo1()");
		}
		
	}
	
	private static interface I2{
		void foo2();
	}
	
	private static class C21 implements I2{

		@Override
		public void foo2() {
			System.out.println("C21.foo2()");
		}
		
	}
	
	private static class C22 implements I2{

		@Override
		public void foo2() {
			System.out.println("C22.foo2()");
		}
		
	}
	
	private static class IFactory1 extends AbstractFactory{
		@Override
		public I1 getImplForI1(int type) {
			if(type == 1) {
				return new C11();
			}
			else
				return new C12();
		}

		@Override
		public I2 getImplForI2(int type) {
			return null;
		}
	}
	
	private static class IFactory2 extends AbstractFactory{
		public I2 getImplForI2(int type) {
			if(type == 1) {
				return new C21();
			}
			else
				return new C22();
		}

		@Override
		public I1 getImplForI1(int type) {
			return null;
		}
	}
	
	private static abstract class AbstractFactory{
		public abstract I1 getImplForI1(int type);
		public abstract I2 getImplForI2(int type);
	}
	
	private static class FactoryProducer{
		public AbstractFactory getFactory(int type) {
			if(type == 1)
				return new IFactory1();
			return new IFactory2();
		}
	}
	
	public static void main(String[] args) {
		FactoryProducer producer = new FactoryProducer();
		producer.getFactory(1).getImplForI1(1).foo1();
		producer.getFactory(1).getImplForI1(2).foo1();
		producer.getFactory(2).getImplForI2(1).foo2();
		producer.getFactory(2).getImplForI2(2).foo2();
	}

}
