package io.atlassian.util.adapter.javax.servlet;

import io.atlassian.util.adapter.Adapted;
import io.atlassian.util.adapter.jakarta.servlet.JakartaServletConfigAdapter;
import io.atlassian.util.adapter.util.WrapperUtil;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import java.util.Enumeration;

import static io.atlassian.util.adapter.javax.JavaXAdapters.asJavaX;
import static io.atlassian.util.adapter.util.WrapperUtil.applyIfNonNull;
import static java.util.Objects.requireNonNull;

public class JavaXServletConfigAdapter implements ServletConfig, Adapted<jakarta.servlet.ServletConfig> {

    private final jakarta.servlet.ServletConfig delegate;

    public static ServletConfig from(jakarta.servlet.ServletConfig delegate) {
        if (delegate instanceof JakartaServletConfigAdapter castDelegate) {
            return castDelegate.getDelegate();
        }
        return applyIfNonNull(delegate, JavaXServletConfigAdapter::new);
    }

    protected JavaXServletConfigAdapter(jakarta.servlet.ServletConfig delegate) {
        this.delegate = requireNonNull(delegate);
    }

    @Override
    public jakarta.servlet.ServletConfig getDelegate() {
        return delegate;
    }

    @Override
    public String getServletName() {
        return delegate.getServletName();
    }

    @Override
    public ServletContext getServletContext() {
        return asJavaX(delegate.getServletContext());
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
