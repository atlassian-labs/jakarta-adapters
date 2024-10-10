package io.atlassian.util.adapter.javax.el;

import javax.el.MethodInfo;

public class JavaXMethodInfoAdapter extends MethodInfo {

    public JavaXMethodInfoAdapter(jakarta.el.MethodInfo delegate) {
        super(delegate.getName(), delegate.getReturnType(), delegate.getParamTypes());
    }
}
