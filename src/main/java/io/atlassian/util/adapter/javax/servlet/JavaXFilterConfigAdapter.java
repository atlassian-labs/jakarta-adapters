package io.atlassian.util.adapter.javax.servlet;

import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import java.util.Enumeration;

import static io.atlassian.util.adapter.util.WrapperUtil.applyIfNonNull;
import static java.util.Objects.requireNonNull;

public class JavaXFilterConfigAdapter implements FilterConfig {

    private final jakarta.servlet.FilterConfig delegate;

    public JavaXFilterConfigAdapter(jakarta.servlet.FilterConfig delegate) {
        this.delegate = requireNonNull(delegate);
    }

    @Override
    public String getFilterName() {
        return delegate.getFilterName();
    }

    @Override
    public ServletContext getServletContext() {
        return applyIfNonNull(delegate.getServletContext(), JavaXServletContextAdapter::new);
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
