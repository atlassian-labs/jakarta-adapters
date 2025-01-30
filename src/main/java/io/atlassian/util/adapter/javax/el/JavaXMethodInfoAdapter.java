package io.atlassian.util.adapter.javax.el;

import io.atlassian.util.adapter.Adapted;
import io.atlassian.util.adapter.jakarta.el.JakartaMethodInfoAdapter;
import io.atlassian.util.adapter.util.WrapperUtil;

import javax.el.MethodInfo;

import static io.atlassian.util.adapter.util.WrapperUtil.applyIfNonNull;

public class JavaXMethodInfoAdapter extends MethodInfo implements Adapted<jakarta.el.MethodInfo> {

    private final jakarta.el.MethodInfo delegate;

    public static MethodInfo from(jakarta.el.MethodInfo delegate) {
        if (delegate instanceof JakartaMethodInfoAdapter castDelegate) {
            return castDelegate.getDelegate();
        }
        return applyIfNonNull(delegate, JavaXMethodInfoAdapter::new);
    }

    protected JavaXMethodInfoAdapter(jakarta.el.MethodInfo delegate) {
        super(delegate.getName(), delegate.getReturnType(), delegate.getParamTypes());
        this.delegate = delegate;
    }

    @Override
    public jakarta.el.MethodInfo getDelegate() {
        return delegate;
    }

    @Override
    public boolean equals(Object obj) {
        return WrapperUtil.equals(this, obj);
    }

    @Override
    public int hashCode() {
        return WrapperUtil.hashCode(this);
    }
}
