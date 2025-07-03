package io.atlassian.util.adapter.jakarta.servlet;

import io.atlassian.util.adapter.Adapted;
import io.atlassian.util.adapter.jakarta.servlet.http.JakartaHttpFilterAdapter;
import io.atlassian.util.adapter.javax.servlet.JavaXGenericFilterAdapter;
import io.atlassian.util.adapter.util.WrapperUtil;

import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.GenericFilter;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import java.io.IOException;
import java.util.Enumeration;

import static io.atlassian.util.adapter.jakarta.JakartaAdapters.asJakarta;
import static io.atlassian.util.adapter.javax.JavaXAdapters.asJavaX;
import static io.atlassian.util.adapter.util.WrapperUtil.applyIfNonNull;
import static java.util.Objects.requireNonNull;

public class JakartaGenericFilterAdapter extends GenericFilter implements Adapted<javax.servlet.GenericFilter> {

    private final javax.servlet.GenericFilter delegate;

    public static GenericFilter from(javax.servlet.GenericFilter delegate) {
        if (delegate instanceof javax.servlet.http.HttpFilter) {
            javax.servlet.http.HttpFilter castDelegate = (javax.servlet.http.HttpFilter) delegate;
            return JakartaHttpFilterAdapter.from(castDelegate);
        }
        if (delegate instanceof JavaXGenericFilterAdapter) {
            JavaXGenericFilterAdapter castDelegate = (JavaXGenericFilterAdapter) delegate;
            return castDelegate.getDelegate();
        }
        return applyIfNonNull(delegate, JakartaGenericFilterAdapter::new);
    }

    protected JakartaGenericFilterAdapter(javax.servlet.GenericFilter delegate) {
        this.delegate = requireNonNull(delegate);
    }

    @Override
    public javax.servlet.GenericFilter getDelegate() {
        return delegate;
    }

    @Override
    public void doFilter(ServletRequest req,
                         ServletResponse res,
                         FilterChain chain) throws IOException, ServletException {
        try {
            delegate.doFilter(asJavaX(req), asJavaX(res), asJavaX(chain));
        } catch (javax.servlet.ServletException e) {
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
        return asJakarta(delegate.getFilterConfig());
    }

    @Override
    public ServletContext getServletContext() {
        return asJakarta(delegate.getServletContext());
    }

    @Override
    public void init(FilterConfig config) throws ServletException {
        try {
            delegate.init(asJavaX(config));
        } catch (javax.servlet.ServletException e) {
            throw new ServletException(e);
        }
    }

    @Override
    public void init() throws ServletException {
        try {
            delegate.init();
        } catch (javax.servlet.ServletException e) {
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

    @Override
    public boolean equals(Object obj) {
        return WrapperUtil.equals(this, obj);
    }

    @Override
    public int hashCode() {
        return WrapperUtil.hashCode(this);
    }
}
