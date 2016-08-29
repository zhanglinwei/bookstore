package com.zlw.bookstore.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * 鍙嶅皠鐨� Utils 鍑芥暟闆嗗悎
 * 鎻愪緵璁块棶绉佹湁鍙橀噺, 鑾峰彇娉涘瀷绫诲瀷 Class, 鎻愬彇闆嗗悎涓厓绱犲睘鎬х瓑 Utils 鍑芥暟
 * @author Administrator
 *
 */
public class ReflectionUtils {

	
	/**
	 * 閫氳繃鍙嶅皠, 鑾峰緱瀹氫箟 Class 鏃跺０鏄庣殑鐖剁被鐨勬硾鍨嬪弬鏁扮殑绫诲瀷
	 * 濡�: public EmployeeDao extends BaseDao<Employee, String>
	 * @param clazz
	 * @param index
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static Class getSuperClassGenricType(Class clazz, int index){
		Type genType = clazz.getGenericSuperclass();
		
		if(!(genType instanceof ParameterizedType)){
			return Object.class;
		}
		
		Type [] params = ((ParameterizedType)genType).getActualTypeArguments();
		
		if(index >= params.length || index < 0){
			return Object.class;
		}
		
		if(!(params[index] instanceof Class)){
			return Object.class;
		}
		
		return (Class) params[index];
	}
	
	/**
	 * 閫氳繃鍙嶅皠, 鑾峰緱 Class 瀹氫箟涓０鏄庣殑鐖剁被鐨勬硾鍨嬪弬鏁扮被鍨�
	 * 濡�: public EmployeeDao extends BaseDao<Employee, String>
	 * @param <T>
	 * @param clazz
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static<T> Class<T> getSuperGenericType(Class clazz){
		return getSuperClassGenricType(clazz, 0);
	}
	
	/**
	 * 寰幆鍚戜笂杞瀷, 鑾峰彇瀵硅薄鐨� DeclaredMethod
	 * @param object
	 * @param methodName
	 * @param parameterTypes
	 * @return
	 */
	public static Method getDeclaredMethod(Object object, String methodName, Class<?>[] parameterTypes){
		
		for(Class<?> superClass = object.getClass(); superClass != Object.class; superClass = superClass.getSuperclass()){
			try {
				//superClass.getMethod(methodName, parameterTypes);
				return superClass.getDeclaredMethod(methodName, parameterTypes);
			} catch (NoSuchMethodException e) {
				//Method 涓嶅湪褰撳墠绫诲畾涔�, 缁х画鍚戜笂杞瀷
			}
			//..
		}
		
		return null;
	}
	
	/**
	 * 浣� filed 鍙樹负鍙闂�
	 * @param field
	 */
	public static void makeAccessible(Field field){
		if(!Modifier.isPublic(field.getModifiers())){
			field.setAccessible(true);
		}
	}
	
	/**
	 * 寰幆鍚戜笂杞瀷, 鑾峰彇瀵硅薄鐨� DeclaredField
	 * @param object
	 * @param filedName
	 * @return
	 */
	public static Field getDeclaredField(Object object, String filedName){
		
		for(Class<?> superClass = object.getClass(); superClass != Object.class; superClass = superClass.getSuperclass()){
			try {
				return superClass.getDeclaredField(filedName);
			} catch (NoSuchFieldException e) {
				//Field 涓嶅湪褰撳墠绫诲畾涔�, 缁х画鍚戜笂杞瀷
			}
		}
		return null;
	}
	
	/**
	 * 鐩存帴璋冪敤瀵硅薄鏂规硶, 鑰屽拷鐣ヤ慨楗扮(private, protected)
	 * @param object
	 * @param methodName
	 * @param parameterTypes
	 * @param parameters
	 * @return
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 */
	public static Object invokeMethod(Object object, String methodName, Class<?> [] parameterTypes,
			Object [] parameters) throws InvocationTargetException{
		
		Method method = getDeclaredMethod(object, methodName, parameterTypes);
		
		if(method == null){
			throw new IllegalArgumentException("Could not find method [" + methodName + "] on target [" + object + "]");
		}
		
		method.setAccessible(true);
		
		try {
			return method.invoke(object, parameters);
		} catch(IllegalAccessException e) {
			System.out.println("涓嶅彲鑳芥姏鍑虹殑寮傚父");
		} 
		
		return null;
	}
	
	/**
	 * 鐩存帴璁剧疆瀵硅薄灞炴�у��, 蹇界暐 private/protected 淇グ绗�, 涔熶笉缁忚繃 setter
	 * @param object
	 * @param fieldName
	 * @param value
	 */
	public static void setFieldValue(Object object, String fieldName, Object value){
		Field field = getDeclaredField(object, fieldName);
		
		if (field == null)
			throw new IllegalArgumentException("Could not find field [" + fieldName + "] on target [" + object + "]");
		
		makeAccessible(field);
		
		try {
			field.set(object, value);
		} catch (IllegalAccessException e) {
			System.out.println("涓嶅彲鑳芥姏鍑虹殑寮傚父");
		}
	}
	
	/**
	 * 鐩存帴璇诲彇瀵硅薄鐨勫睘鎬у��, 蹇界暐 private/protected 淇グ绗�, 涔熶笉缁忚繃 getter
	 * @param object
	 * @param fieldName
	 * @return
	 */
	public static Object getFieldValue(Object object, String fieldName){
		Field field = getDeclaredField(object, fieldName);
		
		if (field == null)
			throw new IllegalArgumentException("Could not find field [" + fieldName + "] on target [" + object + "]");
		
		makeAccessible(field);
		
		Object result = null;
		
		try {
			result = field.get(object);
		} catch (IllegalAccessException e) {
			System.out.println("涓嶅彲鑳芥姏鍑虹殑寮傚父");
		}
		
		return result;
	}
}
