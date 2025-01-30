package io.atlassian.util.adapter.javax.servlet;

import io.atlassian.util.adapter.jakarta.servlet.JakartaDynamicFilterRegistrationAdapter;
import io.atlassian.util.adapter.util.WrapperUtil;

import javax.servlet.FilterRegistration;

import static io.atlassian.util.adapter.util.WrapperUtil.applyIfNonNull;
import static java.util.Objects.requireNonNull;

public class JavaXDynamicFilterRegistrationAdapter extends JavaXFilterRegistrationAdapter implements FilterRegistration.Dynamic {

    private final jakarta.servlet.FilterRegistration.Dynamic delegate;

    public static FilterRegistration.Dynamic from(jakarta.servlet.FilterRegistration.Dynamic delegate) {
        if (delegate instanceof JakartaDynamicFilterRegistrationAdapter castDelegate) {
            return castDelegate.getDelegate();
        }
        return applyIfNonNull(delegate, JavaXDynamicFilterRegistrationAdapter::new);
    }

    protected JavaXDynamicFilterRegistrationAdapter(jakarta.servlet.FilterRegistration.Dynamic delegate) {
        super(delegate);
        this.delegate = requireNonNull(delegate);
    }

    @Override
    public jakarta.servlet.FilterRegistration.Dynamic getDelegate() {
        return delegate;
    }

    @Override
    public void setAsyncSupported(boolean isAsyncSupported) {
        delegate.setAsyncSupported(isAsyncSupported);
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
