package io.atlassian.util.adapter.jakarta.el;

import io.atlassian.util.adapter.javax.el.JavaXMethodInfoAdapter;
import jakarta.el.MethodInfo;

import static io.atlassian.util.adapter.util.WrapperUtil.applyIfNonNull;

public class JakartaMethodInfoAdapter extends MethodInfo {

    private final javax.el.MethodInfo delegate;

    public static MethodInfo from(javax.el.MethodInfo delegate) {
        if (delegate instanceof JavaXMethodInfoAdapter castDelegate) {
            return castDelegate.getDelegate();
        }
        return applyIfNonNull(delegate, JakartaMethodInfoAdapter::new);
    }

    JakartaMethodInfoAdapter(javax.el.MethodInfo delegate) {
        super(delegate.getName(), delegate.getReturnType(), delegate.getParamTypes());
        this.delegate = delegate;
    }

    public javax.el.MethodInfo getDelegate() {
        return delegate;
    }
}
