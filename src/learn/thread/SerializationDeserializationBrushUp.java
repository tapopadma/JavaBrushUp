package learn.thread;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class SerializationDeserializationBrushUp {

	private static class C implements Serializable{
		private int c1;
		private String c2;
		public int getC1() {
			return c1;
		}
		public void setC1(int c1) {
			this.c1 = c1;
		}
		public String getC2() {
			return c2;
		}
		public void setC2(String c2) {
			this.c2 = c2;
		}
		public C(int c1, String c2) {
			this.c1 = c1;
			this.c2 = c2;
		}
		public C() {
			// TODO Auto-generated constructor stub
		}
		
	}
	
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		C c = new C();
		c.setC1(2);
		c.setC2("34c");
		
		FileOutputStream fileOutputStream = new FileOutputStream("/home/tapopadma/Documents/C");
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
		objectOutputStream.writeObject(c);
		
		FileInputStream fileInputStream = new FileInputStream("/home/tapopadma/Documents/C");
		ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
		C c1 = (C)objectInputStream.readObject();
		System.out.println(c1.getC1()+"  " + c1.getC2());
		
	}

}
