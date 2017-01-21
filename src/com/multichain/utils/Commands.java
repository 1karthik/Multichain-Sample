package com.multichain.utils;

public enum Commands {
	
	GET_BALANCE("getbalance"),
	GET_INFO("getinfo"),
	GET_NEW_ADDRESS("getnewaddress"),
	GET_ADDRESSES("getaddresses"),
	ISSUE("issue"),
	LIST_ASSETS("listassets"),
	SEND_ASSET_TO_ADDRESS("sendassettoaddress"),
	GET_ADDRESS_BALANCES("getaddressbalances");
	
	private final String name;
	
	private Commands(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
