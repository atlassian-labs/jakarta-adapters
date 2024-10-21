package io.atlassian.util.adapter.javax.servlet.http;

import io.atlassian.util.adapter.Adapted;
import io.atlassian.util.adapter.jakarta.servlet.http.JakartaHttpServletMappingAdapter;

import javax.servlet.http.HttpServletMapping;
import javax.servlet.http.MappingMatch;

import static io.atlassian.util.adapter.util.WrapperUtil.applyIfNonNull;
import static java.util.Objects.requireNonNull;

public class JavaXHttpServletMappingAdapter implements HttpServletMapping, Adapted<jakarta.servlet.http.HttpServletMapping> {

    private final jakarta.servlet.http.HttpServletMapping delegate;

    public static HttpServletMapping from(jakarta.servlet.http.HttpServletMapping delegate) {
        if (delegate instanceof JakartaHttpServletMappingAdapter castDelegate) {
            return castDelegate.getDelegate();
        }
        return applyIfNonNull(delegate, JavaXHttpServletMappingAdapter::new);
    }

    JavaXHttpServletMappingAdapter(jakarta.servlet.http.HttpServletMapping delegate) {
        this.delegate = requireNonNull(delegate);
    }

    @Override
    public jakarta.servlet.http.HttpServletMapping getDelegate() {
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
}
