package com.example.java_learning.geektime.week1;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author rongsimin
 * @date 2020/10/26 23:21
 */
public class MyClassLoader extends ClassLoader {

	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		byte[] bytes = readData(name);
		return defineClass(null, bytes, 0, bytes.length);
	}

	private byte[] readData(String name) {
		try (InputStream inputStream = new FileInputStream(name)) {
			ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
			int i;
			while ((i = inputStream.read()) != -1) {
				byteArray.write(255 - i);
			}
			return byteArray.toByteArray();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
		String classPath = "C:\\Users\\rongsimin\\git-clone-project\\java_learning_road\\src\\main\\resources\\Hello.xlass";
		Class<?> clazz = new MyClassLoader().loadClass(classPath);
		Method method = clazz.getDeclaredMethod("hello");
		method.setAccessible(true);
		method.invoke(clazz.newInstance());
	}


}
