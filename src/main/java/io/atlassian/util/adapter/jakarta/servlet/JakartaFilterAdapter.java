package io.atlassian.util.adapter.jakarta.servlet;

import io.atlassian.util.adapter.javax.servlet.JavaXFilterChainAdapter;
import io.atlassian.util.adapter.javax.servlet.JavaXFilterConfigAdapter;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

import java.io.IOException;

import static io.atlassian.util.adapter.javax.JavaXAdapters.asJavaX;
import static io.atlassian.util.adapter.util.WrapperUtil.applyIfNonNull;
import static java.util.Objects.requireNonNull;

public class JakartaFilterAdapter implements Filter {

    private final javax.servlet.Filter delegate;

    public JakartaFilterAdapter(javax.servlet.Filter delegate) {
        this.delegate = requireNonNull(delegate);
    }

    public javax.servlet.Filter getDelegate() {
        return delegate;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        try {
            delegate.init(applyIfNonNull(filterConfig, JavaXFilterConfigAdapter::new));
        } catch (javax.servlet.ServletException e) {
            throw new ServletException(e);
        }
    }

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        try {
            delegate.doFilter(asJavaX(servletRequest), asJavaX(servletResponse), applyIfNonNull(filterChain, JavaXFilterChainAdapter::new));
        } catch (javax.servlet.ServletException e) {
            throw new ServletException(e);
        }
    }

    @Override
    public void destroy() {
        delegate.destroy();
    }
}
