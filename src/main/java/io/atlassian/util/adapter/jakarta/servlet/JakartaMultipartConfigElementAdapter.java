package io.atlassian.util.adapter.jakarta.servlet;

import io.atlassian.util.adapter.Adapted;
import io.atlassian.util.adapter.javax.servlet.JavaXMultipartConfigElementAdapter;
import io.atlassian.util.adapter.util.WrapperUtil;

import jakarta.servlet.MultipartConfigElement;

import static io.atlassian.util.adapter.util.WrapperUtil.applyIfNonNull;
import static java.util.Objects.requireNonNull;

public class JakartaMultipartConfigElementAdapter extends MultipartConfigElement implements Adapted<javax.servlet.MultipartConfigElement> {

    private final javax.servlet.MultipartConfigElement delegate;

    public static MultipartConfigElement from(javax.servlet.MultipartConfigElement delegate) {
        if (delegate instanceof JavaXMultipartConfigElementAdapter castDelegate) {
            return castDelegate.getDelegate();
        }
        return applyIfNonNull(delegate, JakartaMultipartConfigElementAdapter::new);
    }

    protected JakartaMultipartConfigElementAdapter(javax.servlet.MultipartConfigElement delegate) {
        super((String) null);
        this.delegate = requireNonNull(delegate);
    }

    @Override
    public javax.servlet.MultipartConfigElement getDelegate() {
        return delegate;
    }

    @Override
    public String getLocation() {
        return delegate.getLocation();
    }

    @Override
    public long getMaxFileSize() {
        return delegate.getMaxFileSize();
    }

    @Override
    public long getMaxRequestSize() {
        return delegate.getMaxRequestSize();
    }

    @Override
    public int getFileSizeThreshold() {
        return delegate.getFileSizeThreshold();
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
