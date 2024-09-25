package io.atlassian.util.adapter.javax.servlet;

import javax.servlet.FilterRegistration;

import static java.util.Objects.requireNonNull;

public class JavaXDynamicFilterRegistrationAdapter extends JavaXFilterRegistrationAdapter implements FilterRegistration.Dynamic {

    private final jakarta.servlet.FilterRegistration.Dynamic delegate;

    public JavaXDynamicFilterRegistrationAdapter(jakarta.servlet.FilterRegistration.Dynamic delegate) {
        super(delegate);
        this.delegate = requireNonNull(delegate);
    }

    @Override
    public void setAsyncSupported(boolean isAsyncSupported) {
        delegate.setAsyncSupported(isAsyncSupported);
    }
}
