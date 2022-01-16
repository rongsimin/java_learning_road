package com.example.java_learning.geektime.week1;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author rongsimin
 * 2.（必做）自定义一个 Classloader，加载一个 Hello.xlass 文件，执行 hello 方法，此文件内容是一个 Hello.class 文件所有字节（x=255-x）处理后的文件。文件群里提供。
 */
public class HelloClassLoader extends ClassLoader {

//	@Override
//	protected Class<?> findClass(String name) throws ClassNotFoundException {
////		byte[] data = readData(name);
//		byte[] data = readData2(name);
//		return defineClass(null, data, 0, data.length);
//	}

	/**
	 * 文件输入流 FileInputStream 每次读取一个字节，用 ByteArrayOutputStream 来接收
	 */
	private byte[] readData(String name) throws ClassNotFoundException {
		final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		try (FileInputStream inputStream = new FileInputStream(name)) {
			int i = -1;
			while ((i = inputStream.read()) != -1) {
				outputStream.write(255 - i);
			}
		} catch (FileNotFoundException e) {
			throw new ClassNotFoundException("class not found");
		} catch (IOException e) {
			throw new ClassNotFoundException("class not found");
		}
		return outputStream.toByteArray();
	}

	/**
	 * 文件输入流 FileInputStream 一次读取1024个字节，用 ByteArrayOutputStream 来接收
	 */
	private byte[] readData2(String name) throws ClassNotFoundException {
		final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		final byte[] bytes = new byte[1024];
		try (FileInputStream inputStream = new FileInputStream(name)) {
			int i = -1;
			while ((i = inputStream.read(bytes)) != -1) {
				outputStream.write(bytes, 0, i);
			}
			final byte[] byteArray = outputStream.toByteArray();
			// 对原字节数组进行解码
			for (int j = 0; j < byteArray.length; j++) {
				byteArray[j] = (byte) (255 - byteArray[j]);
			}
			return byteArray;
		} catch (FileNotFoundException e) {
			throw new ClassNotFoundException("class not found");
		} catch (IOException e) {
			throw new ClassNotFoundException("class not found");
		}
	}

	/**
	 * 按照老师的写法再写一遍，感觉更好，对处理小文件来说
	 */
	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		String suffix = ".xlass";
		try (InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(name + suffix)) {
			final int available = inputStream.available();
			final byte[] bytes = new byte[available];
			final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			int i = -1;
			inputStream.read(bytes);
			outputStream.write(decode(bytes), 0, bytes.length);
			final byte[] byteArray = outputStream.toByteArray();
			return defineClass(null, byteArray, 0, byteArray.length);
		} catch (IOException e) {
			throw new ClassNotFoundException("class not found");
		}
	}

	private byte[] decode(byte[] bytes) {
		final byte[] target = new byte[bytes.length];
		for (int i = 0; i < bytes.length; i++) {
			target[i] = (byte)(255 - bytes[i]);
		}
		return target;
	}

	public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
//		Class<?> helloClass = new HelloClassLoader().loadClass("C:\\Users\\rongsimin\\git-clone-project\\java_learning_road\\src\\main\\resources\\Hello.xlass");
		Class<?> helloClass = new HelloClassLoader().loadClass("Hello");
		final Method[] methods = helloClass.getDeclaredMethods();
		for (Method method : methods) {
			System.out.println(helloClass.getSimpleName() + "." + method.getName());
		}
		Object instance = helloClass.newInstance();
		final Method hello = helloClass.getDeclaredMethod("hello");
		hello.setAccessible(true);
		hello.invoke(instance);
	}
}
