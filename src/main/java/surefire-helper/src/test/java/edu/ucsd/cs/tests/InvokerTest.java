package edu.ucsd.cs.tests;

import static org.junit.Assert.*;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import org.junit.Test;

public class InvokerTest {

//	@Test
//	public void testInvoker() {
//		edu.ucsd.cs.Invoker.main(new String[]{
//			"true", "--daikon", "edu.ucsd.cs.tests.DataStructures.StackArTester"
//		});
////		try {
////			Thread.sleep(5000);
////		} catch (InterruptedException e) {
////			// TODO Auto-generated catch block
////			e.printStackTrace();
////		}
//		System.out.println("still alive.");
//	}
	
	@Test
	public void testPreDaikon() {
		edu.ucsd.cs.PreDaikon.outputDaikonCmd(new String[]{
			"edu.ucsd.cs.tests.DataStructures.StackArTester"});
	}
	
//	@Test
//	public void testReflection() {
//		Method[] methods = this.getClass().getMethods();
//		for(Method m : methods) {
//			System.out.println(m.toString());
//			String methodName = m.getName();
//			String name = m.getName();
//			Annotation[] annotations = m.getAnnotations();
//			for (Annotation a : annotations){
//				System.out.println("name: "+name + " and in short: "+ methodName +"; annotation: "+a);
//				String a_s = a.toString();
//				if (a_s.equals("@org.junit.Test(expected=class org.junit.Test$None, timeout=0)"))
//					System.out.println("that's it!");
//			}
//		}
//	}

}
