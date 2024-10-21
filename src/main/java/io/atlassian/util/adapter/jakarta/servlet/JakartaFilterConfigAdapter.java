package io.atlassian.util.adapter.jakarta.servlet;

import io.atlassian.util.adapter.Adapted;
import io.atlassian.util.adapter.javax.servlet.JavaXFilterConfigAdapter;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletContext;

import java.util.Enumeration;

import static io.atlassian.util.adapter.jakarta.JakartaAdapters.asJakarta;
import static io.atlassian.util.adapter.util.WrapperUtil.applyIfNonNull;
import static java.util.Objects.requireNonNull;

public class JakartaFilterConfigAdapter implements FilterConfig, Adapted<javax.servlet.FilterConfig> {

    private final javax.servlet.FilterConfig delegate;

    public static FilterConfig from(javax.servlet.FilterConfig delegate) {
        if (delegate instanceof JavaXFilterConfigAdapter castDelegate) {
            return castDelegate.getDelegate();
        }
        return applyIfNonNull(delegate, JakartaFilterConfigAdapter::new);
    }

    JakartaFilterConfigAdapter(javax.servlet.FilterConfig delegate) {
        this.delegate = requireNonNull(delegate);
    }

    @Override
    public javax.servlet.FilterConfig getDelegate() {
        return delegate;
    }

    @Override
    public String getFilterName() {
        return delegate.getFilterName();
    }

    @Override
    public ServletContext getServletContext() {
        return asJakarta(delegate.getServletContext());
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
