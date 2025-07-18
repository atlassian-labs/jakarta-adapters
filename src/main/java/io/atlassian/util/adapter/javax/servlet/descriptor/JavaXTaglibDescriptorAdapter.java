package io.atlassian.util.adapter.javax.servlet.descriptor;

import io.atlassian.util.adapter.Adapted;
import io.atlassian.util.adapter.jakarta.servlet.descriptor.JakartaTaglibDescriptorAdapter;
import io.atlassian.util.adapter.util.WrapperUtil;

import javax.servlet.descriptor.TaglibDescriptor;

import static io.atlassian.util.adapter.util.WrapperUtil.applyIfNonNull;
import static java.util.Objects.requireNonNull;

public class JavaXTaglibDescriptorAdapter implements TaglibDescriptor, Adapted<jakarta.servlet.descriptor.TaglibDescriptor> {

    private final jakarta.servlet.descriptor.TaglibDescriptor delegate;

    public static TaglibDescriptor from(jakarta.servlet.descriptor.TaglibDescriptor delegate) {
        if (delegate instanceof JakartaTaglibDescriptorAdapter) {
            JakartaTaglibDescriptorAdapter castDelegate = (JakartaTaglibDescriptorAdapter) delegate;
            return castDelegate.getDelegate();
        }
        return applyIfNonNull(delegate, JavaXTaglibDescriptorAdapter::new);
    }

    protected JavaXTaglibDescriptorAdapter(jakarta.servlet.descriptor.TaglibDescriptor delegate) {
        this.delegate = requireNonNull(delegate);
    }

    @Override
    public jakarta.servlet.descriptor.TaglibDescriptor getDelegate() {
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
