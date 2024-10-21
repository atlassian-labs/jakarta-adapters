package io.atlassian.util.adapter.jakarta.servlet;

import io.atlassian.util.adapter.javax.servlet.JavaXServletConfigAdapter;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;

import java.util.Enumeration;

import static io.atlassian.util.adapter.jakarta.JakartaAdapters.asJakarta;
import static io.atlassian.util.adapter.util.WrapperUtil.applyIfNonNull;
import static java.util.Objects.requireNonNull;

public class JakartaServletConfigAdapter implements ServletConfig {

    private final javax.servlet.ServletConfig delegate;

    public static ServletConfig from(javax.servlet.ServletConfig delegate) {
        if (delegate instanceof JavaXServletConfigAdapter castDelegate) {
            return castDelegate.getDelegate();
        }
        return applyIfNonNull(delegate, JakartaServletConfigAdapter::new);
    }

    JakartaServletConfigAdapter(javax.servlet.ServletConfig delegate) {
        this.delegate = requireNonNull(delegate);
    }

    public javax.servlet.ServletConfig getDelegate() {
        return delegate;
    }

    @Override
    public String getServletName() {
        return delegate.getServletName();
    }

    @Override
    public ServletContext getServletContext() {
        return asJakarta(delegate.getServletContext());
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
