package io.atlassian.util.adapter.jakarta.servlet;

import jakarta.servlet.FilterRegistration;

import static java.util.Objects.requireNonNull;

public class JakartaDynamicFilterRegistrationAdapter extends JakartaFilterRegistrationAdapter implements FilterRegistration.Dynamic {

    private final javax.servlet.FilterRegistration.Dynamic delegate;

    public JakartaDynamicFilterRegistrationAdapter(javax.servlet.FilterRegistration.Dynamic delegate) {
        super(delegate);
        this.delegate = requireNonNull(delegate);
    }

    @Override
    public void setAsyncSupported(boolean isAsyncSupported) {
        delegate.setAsyncSupported(isAsyncSupported);
    }
}
