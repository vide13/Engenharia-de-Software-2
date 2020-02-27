package com.es2.bridge;

import java.util.HashMap;
import java.util.UUID;

public class APIRequest {
    protected final HashMap<java.lang.String, APIServiceInterface> services;

    public APIRequest() {
        services = new HashMap<>();
    }

    public String addService(APIServiceInterface service) {
        UUID uuid = UUID.randomUUID();
        this.services.put(uuid.toString(), service);
        return uuid.toString();
    }

    public String getContent(java.lang.String serviceId, java.lang.String contentId) throws ServiceNotFoundException {
        if (services.get(serviceId) == null) throw new ServiceNotFoundException();
        return services.get(serviceId).getContent(contentId);
    }

    public String setContent(java.lang.String serviceId, java.lang.String content) {
        return services.get(serviceId).setContent(content);
    }
}
