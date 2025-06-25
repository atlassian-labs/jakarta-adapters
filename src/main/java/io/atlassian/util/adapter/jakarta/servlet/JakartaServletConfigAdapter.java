package io.atlassian.util.adapter.jakarta.servlet;

import io.atlassian.util.adapter.Adapted;
import io.atlassian.util.adapter.javax.servlet.JavaXServletConfigAdapter;
import io.atlassian.util.adapter.util.WrapperUtil;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import java.util.Enumeration;

import static io.atlassian.util.adapter.jakarta.JakartaAdapters.asJakarta;
import static io.atlassian.util.adapter.util.WrapperUtil.applyIfNonNull;
import static java.util.Objects.requireNonNull;

public class JakartaServletConfigAdapter implements ServletConfig, Adapted<javax.servlet.ServletConfig> {

    private final javax.servlet.ServletConfig delegate;

    public static ServletConfig from(javax.servlet.ServletConfig delegate) {
        if (delegate instanceof JavaXServletConfigAdapter) {
            JavaXServletConfigAdapter castDelegate = (JavaXServletConfigAdapter) delegate;
            return castDelegate.getDelegate();
        }
        return applyIfNonNull(delegate, JakartaServletConfigAdapter::new);
    }

    protected JakartaServletConfigAdapter(javax.servlet.ServletConfig delegate) {
        this.delegate = requireNonNull(delegate);
    }

    @Override
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

    @Override
    public boolean equals(Object obj) {
        return WrapperUtil.equals(this, obj);
    }

    @Override
    public int hashCode() {
        return WrapperUtil.hashCode(this);
    }
}
