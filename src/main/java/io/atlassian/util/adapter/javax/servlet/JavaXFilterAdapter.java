package io.atlassian.util.adapter.javax.servlet;

import io.atlassian.util.adapter.jakarta.servlet.JakartaFilterChainAdapter;
import io.atlassian.util.adapter.jakarta.servlet.JakartaFilterConfigAdapter;

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

public class JavaXFilterAdapter implements Filter {

    private final jakarta.servlet.Filter delegate;

    public JavaXFilterAdapter(jakarta.servlet.Filter delegate) {
        this.delegate = requireNonNull(delegate);
    }

    public jakarta.servlet.Filter getDelegate() {
        return delegate;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        try {
            delegate.init(applyIfNonNull(filterConfig, JakartaFilterConfigAdapter::new));
        } catch (jakarta.servlet.ServletException e) {
            throw new ServletException(e);
        }
    }

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        try {
            delegate.doFilter(asJakarta(servletRequest), asJakarta(servletResponse), applyIfNonNull(filterChain, JakartaFilterChainAdapter::new));
        } catch (jakarta.servlet.ServletException e) {
            throw new ServletException(e);
        }
    }

    @Override
    public void destroy() {
        delegate.destroy();
    }
}
