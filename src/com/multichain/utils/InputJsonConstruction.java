package com.multichain.utils;

import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class InputJsonConstruction {

	public static JSONObject constructJson(String id, Commands command, List<Object> params) {
		JSONObject json = new JSONObject();
		json.put("id", id);
		json.put("method", command.getName());
		if (null != params) {
			JSONArray array = new JSONArray();
			array.addAll(params);
			json.put("params", params);
		}
		return json;
	}

}
