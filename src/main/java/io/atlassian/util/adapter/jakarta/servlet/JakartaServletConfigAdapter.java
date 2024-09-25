package io.atlassian.util.adapter.jakarta.servlet;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;

import java.util.Enumeration;

import static io.atlassian.util.adapter.util.WrapperUtil.applyIfNonNull;
import static java.util.Objects.requireNonNull;

public class JakartaServletConfigAdapter implements ServletConfig {

    private final javax.servlet.ServletConfig delegate;

    public JakartaServletConfigAdapter(javax.servlet.ServletConfig delegate) {
        this.delegate = requireNonNull(delegate);
    }

    @Override
    public String getServletName() {
        return delegate.getServletName();
    }

    @Override
    public ServletContext getServletContext() {
        return applyIfNonNull(delegate.getServletContext(), JakartaServletContextAdapter::new);
    }

    @Override
    public String getInitParameter(String s) {
        return delegate.getInitParameter(s);
    }

    @Override
    public Enumeration<String> getInitParameterNames() {
        return delegate.getInitParameterNames();
    }
}
