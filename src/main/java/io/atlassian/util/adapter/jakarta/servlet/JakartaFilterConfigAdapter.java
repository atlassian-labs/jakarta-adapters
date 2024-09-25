package io.atlassian.util.adapter.jakarta.servlet;

import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletContext;

import java.util.Enumeration;

import static io.atlassian.util.adapter.util.WrapperUtil.applyIfNonNull;
import static java.util.Objects.requireNonNull;

public class JakartaFilterConfigAdapter implements FilterConfig {

    private final javax.servlet.FilterConfig delegate;

    public JakartaFilterConfigAdapter(javax.servlet.FilterConfig delegate) {
        this.delegate = requireNonNull(delegate);
    }

    @Override
    public String getFilterName() {
        return delegate.getFilterName();
    }

    @Override
    public ServletContext getServletContext() {
        return applyIfNonNull(delegate.getServletContext(), JakartaServletContextAdapter::new);
    }

    @Override
    public String getInitParameter(String name) {
        return delegate.getInitParameter(name);
    }

    @Override
    public Enumeration<String> getInitParameterNames() {
        return delegate.getInitParameterNames();
    }
}
