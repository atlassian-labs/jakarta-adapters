package io.atlassian.util.adapter.jakarta.servlet.http;

import io.atlassian.util.adapter.Adapted;
import io.atlassian.util.adapter.javax.servlet.http.JavaXHttpServletMappingAdapter;
import io.atlassian.util.adapter.util.WrapperUtil;

import jakarta.servlet.http.HttpServletMapping;
import jakarta.servlet.http.MappingMatch;

import static io.atlassian.util.adapter.util.WrapperUtil.applyIfNonNull;
import static java.util.Objects.requireNonNull;

public class JakartaHttpServletMappingAdapter implements HttpServletMapping, Adapted<javax.servlet.http.HttpServletMapping> {

    private final javax.servlet.http.HttpServletMapping delegate;

    public static HttpServletMapping from(javax.servlet.http.HttpServletMapping delegate) {
        if (delegate instanceof JavaXHttpServletMappingAdapter castDelegate) {
            return castDelegate.getDelegate();
        }
        return applyIfNonNull(delegate, JakartaHttpServletMappingAdapter::new);
    }

    protected JakartaHttpServletMappingAdapter(javax.servlet.http.HttpServletMapping delegate) {
        this.delegate = requireNonNull(delegate);
    }

    @Override
    public javax.servlet.http.HttpServletMapping getDelegate() {
        return delegate;
    }

    @Override
    public String getMatchValue() {
        return delegate.getMatchValue();
    }

    @Override
    public String getPattern() {
        return delegate.getPattern();
    }

    @Override
    public String getServletName() {
        return delegate.getServletName();
    }

    @Override
    public MappingMatch getMappingMatch() {
        return MappingMatch.valueOf(delegate.getMappingMatch().name());
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
