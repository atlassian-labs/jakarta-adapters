package io.atlassian.util.adapter.javax.servlet;

import io.atlassian.util.adapter.Adapted;
import io.atlassian.util.adapter.jakarta.servlet.JakartaFilterConfigAdapter;

import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import java.util.Enumeration;

import static io.atlassian.util.adapter.javax.JavaXAdapters.asJavaX;
import static io.atlassian.util.adapter.util.WrapperUtil.applyIfNonNull;
import static java.util.Objects.requireNonNull;

public class JavaXFilterConfigAdapter implements FilterConfig, Adapted<jakarta.servlet.FilterConfig> {

    private final jakarta.servlet.FilterConfig delegate;

    public static FilterConfig from(jakarta.servlet.FilterConfig delegate) {
        if (delegate instanceof JakartaFilterConfigAdapter castDelegate) {
            return castDelegate.getDelegate();
        }
        return applyIfNonNull(delegate, JavaXFilterConfigAdapter::new);
    }

    JavaXFilterConfigAdapter(jakarta.servlet.FilterConfig delegate) {
        this.delegate = requireNonNull(delegate);
    }

    @Override
    public jakarta.servlet.FilterConfig getDelegate() {
        return delegate;
    }

    @Override
    public String getFilterName() {
        return delegate.getFilterName();
    }

    @Override
    public ServletContext getServletContext() {
        return asJavaX(delegate.getServletContext());
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
