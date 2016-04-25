package cn.net.sinodata.cm.content.jcr.model;

import java.util.HashMap;
import java.util.Map;

public class JcrNode {

	protected Map<String, String> properties = new HashMap<String, String>();
	protected String name;
	protected String path;
	protected String id;
	protected String type=JcrNodeType.TYPE_DEFAULT;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public void setProperty(String key, String value){
		properties.put(key, value);
	}
	
	public String getProperty(String key){
		return properties.get(key);
	}
	
	public Map<String, String> getProperties(){
		return properties;
	}
	
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("[" + name + ":" + path + "] {");
		for(Map.Entry<String, String> property : properties.entrySet()){
			sb.append("<" + property.getKey() + ", " + property.getValue() + "> ");
		}
		sb.append("}");
		return sb.toString();
	}
}
