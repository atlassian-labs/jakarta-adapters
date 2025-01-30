package io.atlassian.util.adapter.javax.servlet;

import io.atlassian.util.adapter.Adapted;
import io.atlassian.util.adapter.jakarta.servlet.JakartaFilterAdapter;
import io.atlassian.util.adapter.jakarta.servlet.JakartaFilterChainAdapter;
import io.atlassian.util.adapter.jakarta.servlet.JakartaFilterConfigAdapter;
import io.atlassian.util.adapter.util.WrapperUtil;

import jakarta.servlet.GenericFilter;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

import static io.atlassian.util.adapter.jakarta.JakartaAdapters.asJakarta;
import static io.atlassian.util.adapter.util.WrapperUtil.applyIfNonNull;
import static java.util.Objects.requireNonNull;

public class JavaXFilterAdapter implements Filter, Adapted<jakarta.servlet.Filter> {

    private final jakarta.servlet.Filter delegate;

    public static Filter from(jakarta.servlet.Filter delegate) {
        if (delegate instanceof GenericFilter castDelegate) {
            return JavaXGenericFilterAdapter.from(castDelegate);
        }
        if (delegate instanceof JakartaFilterAdapter castDelegate) {
            return castDelegate.getDelegate();
        }
        return applyIfNonNull(delegate, JavaXFilterAdapter::new);
    }

    protected JavaXFilterAdapter(jakarta.servlet.Filter delegate) {
        this.delegate = requireNonNull(delegate);
    }

    @Override
    public jakarta.servlet.Filter getDelegate() {
        return delegate;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        try {
            delegate.init(JakartaFilterConfigAdapter.from(filterConfig));
        } catch (jakarta.servlet.ServletException e) {
            throw new ServletException(e);
        }
    }

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        try {
            delegate.doFilter(asJakarta(servletRequest), asJakarta(servletResponse), JakartaFilterChainAdapter.from(filterChain));
        } catch (jakarta.servlet.ServletException e) {
            throw new ServletException(e);
        }
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
