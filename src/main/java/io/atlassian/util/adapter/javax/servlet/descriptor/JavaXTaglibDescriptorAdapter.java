package io.atlassian.util.adapter.javax.servlet.descriptor;

import io.atlassian.util.adapter.jakarta.servlet.descriptor.JakartaTaglibDescriptorAdapter;

import javax.servlet.descriptor.TaglibDescriptor;

import static io.atlassian.util.adapter.util.WrapperUtil.applyIfNonNull;
import static java.util.Objects.requireNonNull;

public class JavaXTaglibDescriptorAdapter implements TaglibDescriptor {

    private final jakarta.servlet.descriptor.TaglibDescriptor delegate;

    public static TaglibDescriptor from(jakarta.servlet.descriptor.TaglibDescriptor delegate) {
        if (delegate instanceof JakartaTaglibDescriptorAdapter castDelegate) {
            return castDelegate.getDelegate();
        }
        return applyIfNonNull(delegate, JavaXTaglibDescriptorAdapter::new);
    }

    JavaXTaglibDescriptorAdapter(jakarta.servlet.descriptor.TaglibDescriptor delegate) {
        this.delegate = requireNonNull(delegate);
    }

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
}
