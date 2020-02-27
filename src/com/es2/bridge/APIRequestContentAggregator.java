package com.es2.bridge;

public class APIRequestContentAggregator extends APIRequest {


    public APIRequestContentAggregator(){}

    @Override
    public String getContent(String serviceId,String contentId) throws ServiceNotFoundException {
        if (services.get(serviceId) == null) throw  new ServiceNotFoundException();
        return services.get(serviceId).getContent(contentId);
    }
}
