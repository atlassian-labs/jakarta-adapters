package io.atlassian.util.adapter.jakarta.servlet.descriptor;

import io.atlassian.util.adapter.Adapted;
import io.atlassian.util.adapter.javax.servlet.descriptor.JavaXTaglibDescriptorAdapter;
import io.atlassian.util.adapter.util.WrapperUtil;

import jakarta.servlet.descriptor.TaglibDescriptor;

import static io.atlassian.util.adapter.util.WrapperUtil.applyIfNonNull;
import static java.util.Objects.requireNonNull;

public class JakartaTaglibDescriptorAdapter implements TaglibDescriptor, Adapted<javax.servlet.descriptor.TaglibDescriptor> {

    private final javax.servlet.descriptor.TaglibDescriptor delegate;

    public static TaglibDescriptor from(javax.servlet.descriptor.TaglibDescriptor delegate) {
        if (delegate instanceof JavaXTaglibDescriptorAdapter castDelegate) {
            return castDelegate.getDelegate();
        }
        return applyIfNonNull(delegate, JakartaTaglibDescriptorAdapter::new);
    }

    protected JakartaTaglibDescriptorAdapter(javax.servlet.descriptor.TaglibDescriptor delegate) {
        this.delegate = requireNonNull(delegate);
    }

    @Override
    public javax.servlet.descriptor.TaglibDescriptor getDelegate() {
        return delegate;
    }

    @Override
    public String getTaglibURI() {
        return delegate.getTaglibURI();
    }

    @Override
    public String getTaglibLocation() {
        return delegate.getTaglibLocation();
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
