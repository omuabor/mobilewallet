package com.javaclass.mobilewalletmanagementapis.mobilewallet;

public class FetchAccountRequest {
    private long requestId;
    private String queryItemType;
    private String queryItem;

    public FetchAccountRequest() {
    }

    public FetchAccountRequest(long requestId, String queryItemType, String queryItem) {
        this.requestId = requestId;
        this.queryItemType = queryItemType;
        this.queryItem = queryItem;
    }

    public long getRequestId() {
        return requestId;
    }

    public void setRequestId(long requestId) {
        this.requestId = requestId;
    }

    public String getQueryItemType() {
        return queryItemType;
    }

    public void setQueryItemType(String queryItemType) {
        this.queryItemType = queryItemType;
    }

    public String getQueryItem() {
        return queryItem;
    }

    public void setQueryItem(String queryItem) {
        this.queryItem = queryItem;
    }

}
