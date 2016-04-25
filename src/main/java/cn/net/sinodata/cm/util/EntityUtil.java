package cn.net.sinodata.cm.util;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.commons.beanutils.BeanUtils;

public class EntityUtil {
		
	public static String getEntityValue(Object entityObj, String name){
		String value = null;
		PropertyDescriptor pd;
		try {
			pd = new PropertyDescriptor(name, entityObj.getClass());
			Method method = pd.getReadMethod();
			value = (String) method.invoke(entityObj, new Object[]{});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return value;
	}
	
	/**
	 * 设置实体中的值
	 * @param entityObj 实体对象
	 * @param fields	值的集合
	 * @return
	 * @author manan
	 */
	public static Object setEntityValues(Object entityObj, Map<String, Object> fields){
		Set<String> nameSet = fields.keySet();
		for (String field : nameSet) {
			PropertyDescriptor pd;
			try {
				pd = new PropertyDescriptor(field, entityObj.getClass());
				Method method = pd.getWriteMethod();
				method.invoke(entityObj, fields.get(field));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			
		}
		return entityObj;
	}
	
	public static Object _setEntityValues(Object entityObj, Map<String, Object> fields){
		Set<String> nameSet = fields.keySet();
		for (String field : nameSet) {
			PropertyDescriptor pd;
			try {
				pd = new PropertyDescriptor(field, entityObj.getClass());
				Method method = pd.getWriteMethod();
				Class<?>[] classes = method.getParameterTypes();
//				System.out.println(clazs[0].getClass());
				System.out.println(classes[0].toString());
				Object obj = null;
				if(classes[0].toString().equals("double")){
					obj = Double.valueOf((String) fields.get(field));
				}else{
					obj = fields.get(field);
				}
				method.invoke(entityObj, obj);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			
		}
		return entityObj;
	}
	
	/**
	 * 设置实体中的值
	 * @param entityObj 实体对象
	 * @param name	
	 * @param value
	 * @return
	 * @author manan
	 */
	public static Object setEntityValues(Object entityObj,String name, Object value){
			PropertyDescriptor pd;
			try {
				pd = new PropertyDescriptor(name, entityObj.getClass());
				Method method = pd.getWriteMethod();
				method.invoke(entityObj, value);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		return entityObj;
	}

	
	public static Object copyProperties(Object po, Object destPo){
		
		Class<?> clazz = po.getClass();
		Field[] fields = clazz.getDeclaredFields();
		Object entityInstance = null;
		
		String fieldName = "";
		try {
			BeanUtils.copyProperties(destPo, po);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return destPo;
	}
	
	public static Object generatePersistencePO(Object po, Object destPo){
		
		Class<?> clazz = po.getClass();
		Field[] fields = clazz.getDeclaredFields();
		Object entityInstance = null;
		
		String fieldName = "";
		try {
			BeanUtils.copyProperties(destPo, po);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return destPo;
	}
	
	/**
	 * 创建实体对象的实例并根据VO对象的值赋值
	 * @param className
	 * @param fields
	 * @return
	 * @author manan
	 */
	public static Object createEntityInstance(String packageName, String className, Map<String, String> fields){
		Object entityInstance = null;
		className = packageName + "." + className;
		try {
			Class<?> clazz = Class.forName(className);
			entityInstance =clazz.newInstance();
			Set<String> nameSet = fields.keySet();
			for (String field : nameSet) {
				PropertyDescriptor pd = new PropertyDescriptor(field, clazz);
				Method method = pd.getWriteMethod();
				method.invoke(entityInstance, fields.get(field));
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IntrospectionException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return entityInstance;
	}
	
	/**
	  * VO 转换为实体对象
	  * @Title: VoToEntity 
	  * @Description: TODO(这里用一句话描述这个方法的作用) 
	  * @param @param vo
	  * @param @param EntityName
	  * @param @return    设定文件 
	  * @return Class<?>    返回类型 
	  * @throws
	 */
	/*public static Object VoToEntity(Object obj, String EntityName){
		Object entityInstance;
		String fieldName = "";
		Class<?> fieldType = null;
		try {
			Class<?> entityClazz = Class.forName(EntityName);
			entityInstance = entityClazz.newInstance();
			Field[] fields = entityClazz.getDeclaredFields();
			for (Field field : fields) {
				fieldName = field.getName();
				fieldType = field.getType();
				System.out.println(fieldName + " | " + fieldType);
				Object value = TypeConvert(fieldType, (String)obj.getObj(fieldName));
				BeanUtils.copyProperty(entityInstance, fieldName, value);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
		return entityInstance;
	}*/
	
	/**
	 * 实体对象转换为value为String类型的map对象
	 * @param entityObj
	 * @return
	 * @throws Exception
	 * @author manan
	 */
	public static Map<String, String> entityToStringMap(Object entityObj){
		Map<String, String> map = new HashMap<String, String>();
		Class<?> clazz = entityObj.getClass();
		Field[] fields = clazz.getDeclaredFields();
		try {
			String fieldName = "";
			for (Field field : fields) {
				fieldName = field.getName();
	//			if(!fieldName.equalsIgnoreCase("id") && !fieldName.equalsIgnoreCase("flowid")){
					PropertyDescriptor pd;
					
						pd = new PropertyDescriptor(field.getName(),clazz);
					
					Method getMethod = pd.getReadMethod();//获得get方法
					String value = (String)getMethod.invoke(entityObj);//执行get方法返回一个Object
					map.put(fieldName, value);
	//			}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return map;
	}
	
	/**
	 * 实体对象转换为value为Object类型的map对象
	 * @param entityObj
	 * @return
	 * @throws Exception
	 * @author manan
	 */
	public static Map<String, Object> entityToObjectMap(Object entityObj){
		Map<String, Object> map = new HashMap<String, Object>();
		Class<?> clazz = entityObj.getClass();
		Field[] fields = clazz.getDeclaredFields();
		try {
			String fieldName = "";
			for (Field field : fields) {
				fieldName = field.getName();
	//			if(!fieldName.equalsIgnoreCase("id") && !fieldName.equalsIgnoreCase("flowid")){
					PropertyDescriptor pd;
					
						pd = new PropertyDescriptor(field.getName(),clazz);
					
					Method getMethod = pd.getReadMethod();//获得get方法
//					String value = (String)getMethod.invoke(entityObj);//执行get方法返回一个Object          //changed by zeus.liu 2013-12-24 13:21:49
					map.put(fieldName, getMethod.invoke(entityObj));
	//			}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return map;
	}
	
	/**
	 * 按照数据类型转化
	 * @param cls
	 * @param obj
	 * @return
	 */
	public static Object TypeConvert(Class<?> cls, String obj) {
		Object convertedObj = null;
		if (obj == null)
			return null;
		if (!cls.isArray()) {
			if (cls.getName().endsWith("String")) {
				convertedObj = obj;
			} else if (cls.getName().endsWith("Integer")) {
				convertedObj = Integer.parseInt(obj);
			} else if (cls.getName().endsWith("Long")) {
				convertedObj = Long.parseLong(obj);
			} else if (cls.getName().endsWith("BigDecimal")) {
				convertedObj = obj;
			} else if (cls.getName().endsWith("Date")) {
				String tempDate = obj.trim();
				if(10 == obj.trim().length()){
					tempDate += " 00:00:00";
				}
				try {
					convertedObj = DateFormatTools.getTime(tempDate).getTime();
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
		} else {
			if (cls.getName().endsWith("String;")) {
				convertedObj = obj.split("\\,");
			} else if (cls.getName().endsWith("Integer;")) {
				String[] tmp = obj.split("\\,");
				int len = tmp.length;
				int[] tmpi = new int[len];
				for (int i = 0; i < tmp.length; i++)
					tmpi[i] = Integer.parseInt(tmp[i]);
				convertedObj = tmpi;
			} else if (cls.getName().endsWith("Long;")) {
				String[] tmp = obj.split("\\,");
				int len = tmp.length;
				Long[] tmpl = new Long[len];
				for (int i = 0; i < tmp.length; i++)
					tmpl[i] = Long.parseLong(tmp[i]);
				convertedObj = tmpl;
			}else if(cls.getName().endsWith("Date;")){
				String[] tmp = obj.split("\\,");
				int len = tmp.length;
				//$ANALYSIS-IGNORE
				Date[] tmpl = new Date[len];
				for (int i = 0; i < tmp.length; i++){
					String tempDate = obj.trim();
					if(10 == obj.trim().length()){
						tempDate += " 00:00:00";
					}
					try {
						tmpl[i] = DateFormatTools.getTime(tempDate).getTime();
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}
				convertedObj = tmpl;
			}
		}
		return convertedObj;
	}
	
	/*public static VO mapToVO(Map<String, Object> map){
		VO vo = new VO();
		Set<String> set = map.keySet();
		for (String key : set) {
			System.out.println(map.get(key).getClass());
			String[] values = (String[]) map.get(key);
			vo.setAttr(key, values[0]);
		}
		return vo;
	}
*/
	/**
	 * 表单enctype属性值为multipart/form-data时处理数据方法
	 * @param map
	 * @return
	 * @author manan
	 */
	/*public static VO multipartMapToVO(Map<String, Object> map){
		VO vo = new VO();
		Set<String> set = map.keySet();
		for (String key : set) {
			System.out.println(map.get(key).getClass());
			String[] values = (String[]) map.get(key);
			for (String value : values) {
				System.out.println(value);
			}
			vo.setAttr(key, (String)map.get(key));
		}
		return vo;
	}*/
	
	
	public static void main(String[] args) {
		TestBean bean = new TestBean();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("str", "abc");
		map.put("dbl", "111");
		_setEntityValues(bean, map);
		System.out.println(bean);
	}
	
	
}
