package io.atlassian.util.adapter.jakarta.servlet;

import jakarta.servlet.MultipartConfigElement;

import static java.util.Objects.requireNonNull;

public class JakartaMultipartConfigElementAdapter extends MultipartConfigElement {

    private final javax.servlet.MultipartConfigElement delegate;

    public JakartaMultipartConfigElementAdapter(javax.servlet.MultipartConfigElement delegate) {
        super((String) null);
        this.delegate = requireNonNull(delegate);
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
