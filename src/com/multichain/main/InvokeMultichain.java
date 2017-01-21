package com.multichain.main;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;

public class InvokeMultichain {

	public static void main(String[] args) {
		RPCClient client = new RPCClient();
		JSONArray myAddress = client.getAddresses();
		/* creating a issue */
		// List<Object> issueArray = new ArrayList<Object>();
		// issueArray.add(myAddress.get(0).toString());
		// issueArray.add("OS"); //specify here asset name
		// issueArray.add(1000); //total asset quantities
		// issueArray.add(1); //each asset value
		// issueArray.add(0);
		// System.out.println(client.issueAsset(issueArray));
		System.out.println(client.listAssets());
		String newAddress = client.getNewAddress();
		/* send asset to address */
		List<Object> sendAsset = new ArrayList<Object>();
		sendAsset.add(newAddress); //generated address
		sendAsset.add("laptops"); //our asset to transfer
		sendAsset.add(10); //quantity to transfer
		System.out.println(client.sendAssetToAddress(sendAsset));
		List<Object> address = new ArrayList<Object>();
		address.add(myAddress.get(0).toString());
		System.out.println(client.getAddressBalances(address));
	}

}
