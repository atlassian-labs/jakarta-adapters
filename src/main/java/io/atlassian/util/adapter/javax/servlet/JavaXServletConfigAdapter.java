package io.atlassian.util.adapter.javax.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import java.util.Enumeration;

import static io.atlassian.util.adapter.util.WrapperUtil.applyIfNonNull;
import static java.util.Objects.requireNonNull;

public class JavaXServletConfigAdapter implements ServletConfig {

    private final jakarta.servlet.ServletConfig delegate;

    public JavaXServletConfigAdapter(jakarta.servlet.ServletConfig delegate) {
        this.delegate = requireNonNull(delegate);
    }

    @Override
    public String getServletName() {
        return delegate.getServletName();
    }

    @Override
    public ServletContext getServletContext() {
        return applyIfNonNull(delegate.getServletContext(), JavaXServletContextAdapter::new);
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
