package com.es2.bridge;

import java.util.HashMap;
import java.util.UUID;

public class APIRequest {
    protected final HashMap<String, APIServiceInterface> services;

    public APIRequest() {
        services = new HashMap<>();
    }

    public String addService(APIServiceInterface service) {
        UUID uuid = UUID.randomUUID();
        this.services.put(uuid.toString(), service);
        return uuid.toString();
    }

    public String getContent(String serviceId, String contentId) throws ServiceNotFoundException {
        if (services.get(serviceId) == null)
            throw new ServiceNotFoundException();
        return services.get(serviceId).getContent(contentId);
    }

    public String setContent(String serviceId, String content) throws ServiceNotFoundException{
        if (services.get(serviceId) == null)
            throw new ServiceNotFoundException();
        return services.get(serviceId).setContent(content);
    }
}
