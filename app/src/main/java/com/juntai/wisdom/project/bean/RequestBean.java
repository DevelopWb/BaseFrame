package com.juntai.wisdom.project.bean;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2022/6/13 23:24
 * @UpdateUser: 更新者
 * @UpdateDate: 2022/6/13 23:24
 */
public class RequestBean {

    private String HandlerName;
    private String QueryType;
    private String SessionID;
    private String Parameters;

    public RequestBean(String handlerName, String queryType, String sessionID, String parameters) {
        HandlerName = handlerName;
        QueryType = queryType;
        SessionID = sessionID;
        Parameters = parameters;
    }

    public String getHandlerName() {
        return HandlerName == null ? "" : HandlerName;
    }

    public void setHandlerName(String handlerName) {
        HandlerName = handlerName == null ? "" : handlerName;
    }

    public String getQueryType() {
        return QueryType == null ? "" : QueryType;
    }

    public void setQueryType(String queryType) {
        QueryType = queryType == null ? "" : queryType;
    }

    public String getSessionID() {
        return SessionID == null ? "" : SessionID;
    }

    public void setSessionID(String sessionID) {
        SessionID = sessionID == null ? "" : sessionID;
    }

    public String getParameters() {
        return Parameters == null ? "{}" : Parameters;
    }

    public void setParameters(String parameters) {
        Parameters = parameters == null ? "" : parameters;
    }
}
