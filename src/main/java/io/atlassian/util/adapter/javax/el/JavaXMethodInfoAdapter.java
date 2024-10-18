package io.atlassian.util.adapter.javax.el;

import io.atlassian.util.adapter.jakarta.el.JakartaMethodInfoAdapter;

import javax.el.MethodInfo;

import static io.atlassian.util.adapter.util.WrapperUtil.applyIfNonNull;

public class JavaXMethodInfoAdapter extends MethodInfo {

    private final jakarta.el.MethodInfo delegate;

    public static MethodInfo from(jakarta.el.MethodInfo delegate) {
        if (delegate instanceof JakartaMethodInfoAdapter castDelegate) {
            return castDelegate.getDelegate();
        }
        return applyIfNonNull(delegate, JavaXMethodInfoAdapter::new);
    }

    JavaXMethodInfoAdapter(jakarta.el.MethodInfo delegate) {
        super(delegate.getName(), delegate.getReturnType(), delegate.getParamTypes());
        this.delegate = delegate;
    }

    public jakarta.el.MethodInfo getDelegate() {
        return delegate;
    }
}
