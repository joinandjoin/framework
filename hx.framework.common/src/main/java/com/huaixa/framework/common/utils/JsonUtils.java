package com.huaixa.framework.common.utils;

import java.io.Reader;
import java.io.Writer;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;

@Component
public class JsonUtils {
	
	@Autowired
	private ObjectMapper i_tool;
	
	private static JsonUtils instance;
	
	@PostConstruct
	public void init() {
		instance = this;
		instance.i_tool = this.i_tool;
	}
	
	public static String toJson(Object entity) throws Exception {
		try {
			return instance.i_tool.writeValueAsString(entity);
		} catch (JsonProcessingException e) {
			throw new Exception("对象转换为Json时出错！！",e);
		}
	}
	
	public static void toJson(Writer writer,Object entity) throws Exception {
		try {
			instance.i_tool.writeValue(writer, entity);
		} catch (Exception e) {
			throw new Exception("对象转换为Json形式的Writer流时出错！！",e);
		}
	}
	
	public static <T> T toObject(String json,Class<T> T) throws Exception {
		try {
			return instance.i_tool.readValue(json,T);
		} catch (Exception e) {
			throw new Exception("Json字符串转换为对象时出错！！",e);
		}
	}
	
	public static <T> T toObject(Reader reader,Class<T> T) throws Exception {
		try {
			return instance.i_tool.readValue(reader,T);
		} catch (Exception e) {
			throw new Exception("通过Reader流将Json转换为对象的时出错！！",e);
		}
	}
	
	public static <T> List<T> toList(Reader reader,Class<T> T) throws Exception {
		try {
			TypeReference<List<T>> typeRef = new TypeReference<List<T>>() {};
			return instance.i_tool.readValue(reader,typeRef);
		} catch (Exception e) {
			throw new Exception("通过Reader流将Json转换为对象的时出错！！",e);
		}
	}
	
	public static JsonNode toNode(String json) throws Exception  {
		try {
			return instance.i_tool.readTree(json);
		} catch (Exception e) {
			throw new Exception("通过json字符串将Json转换为JsonNode的时出错！！",e);
		}
	}
	
	public static JsonNode toArrayNode(String jsonRoot,String path)  throws Exception  {
		try {
			JsonNode root = toNode(jsonRoot);
			return root.findPath(path);
		} catch (Exception e) {
			throw new Exception("通过json字符串将Json转换为JsonNode的时出错！！",e);
		}
	}
	
	public static JsonNode toArrayNode(JsonNode root,String path)  throws Exception  {
		try {
			return root.findPath(path);
		} catch (Exception e) {
			throw new Exception("通过json字符串将Json转换为JsonNode的时出错！！",e);
		}
	}
	
	public static JsonNode appendStringNode(JsonNode nodeToAppend, String propName,String propValue) throws Exception  {
		try {
			ObjectNode objNode = (ObjectNode)nodeToAppend;
			TextNode node = objNode.textNode(propValue);
			return objNode.set(propName,node);
		} catch (Exception e) {
			throw new Exception("通过json字符串将Json转换为JsonNode的时出错！！",e);
		}
	}
	
	public static ObjectNode createNewObjectNode() throws Exception  {
		try {
			return instance.i_tool.createObjectNode();
		} catch (Exception e) {
			throw new Exception("通过json字符串将Json转换为JsonNode的时出错！！",e);
		}
	}
	
	public static ArrayNode createArrayNode() throws Exception  {
		try {
			return instance.i_tool.createArrayNode();
		} catch (Exception e) {
			throw new Exception("通过json字符串将Json转换为JsonNode的时出错！！",e);
		}
	}
	
	public static JsonNode removeNode(JsonNode node, String propName) throws Exception  {
		try {
			ObjectNode objNode = (ObjectNode)node;
			return objNode.remove(propName);
			
		} catch (Exception e) {
			throw new Exception("通过json字符串将Json转换为JsonNode的时出错！！",e);
		}
	}
}
