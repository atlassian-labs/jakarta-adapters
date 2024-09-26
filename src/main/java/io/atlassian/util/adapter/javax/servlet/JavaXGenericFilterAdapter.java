package io.atlassian.util.adapter.javax.servlet;

import io.atlassian.util.adapter.javax.servlet.http.JavaXHttpFilterAdapter;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.GenericFilter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.util.Enumeration;

import static io.atlassian.util.adapter.jakarta.JakartaAdapters.asJakarta;
import static io.atlassian.util.adapter.javax.JavaXAdapters.asJavaX;
import static io.atlassian.util.adapter.util.WrapperUtil.applyIfNonNull;
import static java.util.Objects.requireNonNull;

public class JavaXGenericFilterAdapter extends GenericFilter {

    private final jakarta.servlet.GenericFilter delegate;

    public static javax.servlet.GenericFilter from(jakarta.servlet.GenericFilter delegate) {
        if (delegate instanceof jakarta.servlet.http.HttpFilter castDelegate) {
            return new JavaXHttpFilterAdapter(castDelegate);
        }
        return applyIfNonNull(delegate, JavaXGenericFilterAdapter::new);
    }

    private JavaXGenericFilterAdapter(jakarta.servlet.GenericFilter delegate) {
        this.delegate = requireNonNull(delegate);
    }

    public jakarta.servlet.GenericFilter getDelegate() {
        return delegate;
    }

    @Override
    public void doFilter(ServletRequest req,
                         ServletResponse res,
                         FilterChain chain) throws IOException, ServletException {
        try {
            delegate.doFilter(asJakarta(req), asJakarta(res), asJakarta(chain));
        } catch (jakarta.servlet.ServletException e) {
            throw new ServletException(e);
        }
    }

    @Override
    public String getInitParameter(String name) {
        return delegate.getInitParameter(name);
    }

    @Override
    public Enumeration<String> getInitParameterNames() {
        return delegate.getInitParameterNames();
    }

    @Override
    public FilterConfig getFilterConfig() {
        return asJavaX(delegate.getFilterConfig());
    }

    @Override
    public ServletContext getServletContext() {
        return asJavaX(delegate.getServletContext());
    }

    @Override
    public void init(FilterConfig config) throws ServletException {
        try {
            delegate.init(asJakarta(config));
        } catch (jakarta.servlet.ServletException e) {
            throw new ServletException(e);
        }
    }

    @Override
    public void init() throws ServletException {
        try {
            delegate.init();
        } catch (jakarta.servlet.ServletException e) {
            throw new ServletException(e);
        }
    }

    @Override
    public String getFilterName() {
        return delegate.getFilterName();
    }

    @Override
    public void destroy() {
        delegate.destroy();
    }
}
