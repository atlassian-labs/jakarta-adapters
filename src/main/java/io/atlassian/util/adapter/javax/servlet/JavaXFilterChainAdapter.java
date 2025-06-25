package io.atlassian.util.adapter.javax.servlet;

import io.atlassian.util.adapter.Adapted;
import io.atlassian.util.adapter.jakarta.servlet.JakartaFilterChainAdapter;
import io.atlassian.util.adapter.util.WrapperUtil;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

import static io.atlassian.util.adapter.jakarta.JakartaAdapters.asJakarta;
import static io.atlassian.util.adapter.util.WrapperUtil.applyIfNonNull;
import static java.util.Objects.requireNonNull;

public class JavaXFilterChainAdapter implements FilterChain, Adapted<jakarta.servlet.FilterChain> {

    private final jakarta.servlet.FilterChain delegate;

    public static FilterChain from(jakarta.servlet.FilterChain delegate) {
        if (delegate instanceof JakartaFilterChainAdapter) {
            JakartaFilterChainAdapter castDelegate = (JakartaFilterChainAdapter) delegate;
            return castDelegate.getDelegate();
        }
        return applyIfNonNull(delegate, JavaXFilterChainAdapter::new);
    }

    protected JavaXFilterChainAdapter(jakarta.servlet.FilterChain delegate) {
        this.delegate = requireNonNull(delegate);
    }

    @Override
    public jakarta.servlet.FilterChain getDelegate() {
        return delegate;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response) throws IOException, ServletException {
        try {
            delegate.doFilter(asJakarta(request), asJakarta(response));
        } catch (jakarta.servlet.ServletException e) {
            throw new ServletException(e);
        }
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
