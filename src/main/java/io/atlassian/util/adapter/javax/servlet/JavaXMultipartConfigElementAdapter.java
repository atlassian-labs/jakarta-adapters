package io.atlassian.util.adapter.javax.servlet;

import javax.servlet.MultipartConfigElement;

import static java.util.Objects.requireNonNull;

public class JavaXMultipartConfigElementAdapter extends MultipartConfigElement {

    private final jakarta.servlet.MultipartConfigElement delegate;

    public JavaXMultipartConfigElementAdapter(jakarta.servlet.MultipartConfigElement delegate) {
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
