package io.atlassian.util.adapter.jakarta.el;

import jakarta.el.MethodInfo;

public class JakartaMethodInfoAdapter extends MethodInfo {

    public JakartaMethodInfoAdapter(javax.el.MethodInfo delegate) {
        super(delegate.getName(), delegate.getReturnType(), delegate.getParamTypes());
    }
}
