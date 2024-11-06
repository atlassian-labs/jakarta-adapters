package io.atlassian.util.adapter.jakarta.servlet;

import io.atlassian.util.adapter.Adapted;
import io.atlassian.util.adapter.javax.servlet.JavaXFilterChainAdapter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

import java.io.IOException;

import static io.atlassian.util.adapter.javax.JavaXAdapters.asJavaX;
import static io.atlassian.util.adapter.util.WrapperUtil.applyIfNonNull;
import static java.util.Objects.requireNonNull;

public class JakartaFilterChainAdapter implements FilterChain, Adapted<javax.servlet.FilterChain> {

    private final javax.servlet.FilterChain delegate;

    public static FilterChain from(javax.servlet.FilterChain delegate) {
        if (delegate instanceof JavaXFilterChainAdapter castDelegate) {
            return castDelegate.getDelegate();
        }
        return applyIfNonNull(delegate, JakartaFilterChainAdapter::new);
    }

    JakartaFilterChainAdapter(javax.servlet.FilterChain delegate) {
        this.delegate = requireNonNull(delegate);
    }

    @Override
    public javax.servlet.FilterChain getDelegate() {
        return delegate;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response) throws IOException, ServletException {
        try {
            delegate.doFilter(asJavaX(request), asJavaX(response));
        } catch (javax.servlet.ServletException e) {
            throw new ServletException(e);
        }
    }
}
