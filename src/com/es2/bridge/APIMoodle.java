package com.es2.bridge;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

@SuppressWarnings("SpellCheckingInspection")
public class APIMoodle implements APIServiceInterface {

    protected final LinkedHashMap<String, String> content;

    public APIMoodle() {
        content = new LinkedHashMap<>();
    }

    @Override
    public String getContent(String contentId) {
        if (contentId.equals("0")) {
            StringBuilder concatenatedHax = new StringBuilder();
            for (Map.Entry<String, String> entry : content.entrySet()) {
                concatenatedHax.append(entry.getValue());
            }
            return concatenatedHax.toString();
        }
        return content.get(contentId);
    }

    @Override
    public String setContent(String content) {
        UUID uuid = UUID.randomUUID();
        this.content.put(uuid.toString(), content);
        return uuid.toString();
    }
}