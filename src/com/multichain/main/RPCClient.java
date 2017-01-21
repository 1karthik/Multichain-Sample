package com.multichain.main;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.multichain.utils.Commands;
import com.multichain.utils.InputJsonConstruction;

public class RPCClient {

	public JSONObject invokeRPC(String id, Commands command, List<Object> params) {
		DefaultHttpClient httpclient = new DefaultHttpClient();

		JSONObject json = InputJsonConstruction.constructJson(id, command, params);
		JSONObject responseJsonObj = null;
		try {
			httpclient.getCredentialsProvider().setCredentials(new AuthScope("192.168.0.101", 9560),
					new UsernamePasswordCredentials("multichainrpc", "FEpL8qHsSDEAyBHJh6eGKaNsT75vJ3VAu6cRBc3XVsbU"));
			StringEntity myEntity = new StringEntity(json.toJSONString());
			System.out.println(json.toString());
			HttpPost httppost = new HttpPost(
					"http://multichainrpc:FEpL8qHsSDEAyBHJh6eGKaNsT75vJ3VAu6cRBc3XVsbU@192.168.0.101:9560");
			httppost.setEntity(myEntity);

			System.out.println("executing request" + httppost.getRequestLine());
			HttpResponse response = httpclient.execute(httppost);
			HttpEntity entity = response.getEntity();

			System.out.println("----------------------------------------");
			System.out.println(response.getStatusLine());
			if (entity != null) {
				System.out.println("Response content length: " + entity.getContentLength());
			}
			JSONParser parser = new JSONParser();
			responseJsonObj = (JSONObject) parser.parse(EntityUtils.toString(entity));
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (org.json.simple.parser.ParseException e) {
			e.printStackTrace();
		} finally {
			// When HttpClient instance is no longer needed,
			// shut down the connection manager to ensure
			// immediate deallocation of all system resources
			httpclient.getConnectionManager().shutdown();
		}
		return responseJsonObj;
	}

//	public Double getBalance(String account) {
//		String[] params = { account };
//		JSONObject json = invokeRPC(UUID.randomUUID().toString(), Commands.GET_BALANCE, Arrays.asList(params));
//		return (Double) json.get("result");
//	}

	public String getNewAddress() {
		JSONObject json = invokeRPC(UUID.randomUUID().toString(), Commands.GET_NEW_ADDRESS, null);
		return (String) json.get("result");
	}

	public JSONObject getInfo() {
		JSONObject json = invokeRPC(UUID.randomUUID().toString(), Commands.GET_INFO, null);
		return (JSONObject) json.get("result");
	}

	public JSONArray getAddresses() {
		JSONObject json = invokeRPC(UUID.randomUUID().toString(), Commands.GET_ADDRESSES, null);
		return (JSONArray) json.get("result");
	}

	public String issueAsset(List<Object> issueArray) {
		JSONObject json = invokeRPC(UUID.randomUUID().toString(), Commands.ISSUE, issueArray);
		return (String) json.get("result");

	}

	public JSONArray listAssets() {
		JSONObject json = invokeRPC(UUID.randomUUID().toString(), Commands.LIST_ASSETS, null);
		return (JSONArray) json.get("result");
	}

	public String sendAssetToAddress(List<Object> sendAsset) {
		JSONObject json = invokeRPC(UUID.randomUUID().toString(), Commands.SEND_ASSET_TO_ADDRESS, sendAsset);
		return (String) json.get("result");
	}

	public JSONArray getAddressBalances(List<Object> address) {
		JSONObject json = invokeRPC(UUID.randomUUID().toString(), Commands.GET_ADDRESS_BALANCES, address);
		return (JSONArray) json.get("result");
	}

}
