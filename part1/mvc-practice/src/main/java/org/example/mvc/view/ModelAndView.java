package org.example.mvc.view;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ModelAndView {

    private final Object view;
    private Map<String, Object> model = new HashMap<>();

    public ModelAndView(String viewName) {
        this.view = viewName;
    }

    public String getView() {
        return (this.view instanceof String ? (String) this.view : null);
    }

    public Map<String, ?> getModel() {
        return Collections.unmodifiableMap(model);
    }
}
