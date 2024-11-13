package io.atlassian.util.adapter.jakarta.servlet;

import io.atlassian.util.adapter.javax.servlet.JavaXDynamicFilterRegistrationAdapter;
import jakarta.servlet.FilterRegistration;

import static io.atlassian.util.adapter.util.WrapperUtil.applyIfNonNull;
import static java.util.Objects.requireNonNull;

public class JakartaDynamicFilterRegistrationAdapter extends JakartaFilterRegistrationAdapter implements FilterRegistration.Dynamic {

    private final javax.servlet.FilterRegistration.Dynamic delegate;

    public static FilterRegistration.Dynamic from(javax.servlet.FilterRegistration.Dynamic delegate) {
        if (delegate instanceof JavaXDynamicFilterRegistrationAdapter castDelegate) {
            return castDelegate.getDelegate();
        }
        return applyIfNonNull(delegate, JakartaDynamicFilterRegistrationAdapter::new);
    }

    protected JakartaDynamicFilterRegistrationAdapter(javax.servlet.FilterRegistration.Dynamic delegate) {
        super(delegate);
        this.delegate = requireNonNull(delegate);
    }

    @Override
    public javax.servlet.FilterRegistration.Dynamic getDelegate() {
        return delegate;
    }

    @Override
    public void setAsyncSupported(boolean isAsyncSupported) {
        delegate.setAsyncSupported(isAsyncSupported);
    }
}
