package io.atlassian.util.adapter.jakarta.servlet.descriptor;

import jakarta.servlet.descriptor.TaglibDescriptor;

import static java.util.Objects.requireNonNull;

public class JakartaTaglibDescriptorAdapter implements TaglibDescriptor {

    private final javax.servlet.descriptor.TaglibDescriptor delegate;

    public JakartaTaglibDescriptorAdapter(javax.servlet.descriptor.TaglibDescriptor delegate) {
        this.delegate = requireNonNull(delegate);
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
