package io.atlassian.util.adapter.javax.servlet;

import io.atlassian.util.adapter.jakarta.servlet.JakartaMultipartConfigElementAdapter;

import javax.servlet.MultipartConfigElement;

import static io.atlassian.util.adapter.util.WrapperUtil.applyIfNonNull;
import static java.util.Objects.requireNonNull;

public class JavaXMultipartConfigElementAdapter extends MultipartConfigElement {

    private final jakarta.servlet.MultipartConfigElement delegate;

    public static MultipartConfigElement from(jakarta.servlet.MultipartConfigElement delegate) {
        if (delegate instanceof JakartaMultipartConfigElementAdapter castDelegate) {
            return castDelegate.getDelegate();
        }
        return applyIfNonNull(delegate, JavaXMultipartConfigElementAdapter::new);
    }

    JavaXMultipartConfigElementAdapter(jakarta.servlet.MultipartConfigElement delegate) {
        super((String) null);
        this.delegate = requireNonNull(delegate);
    }

    public jakarta.servlet.MultipartConfigElement getDelegate() {
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
}
