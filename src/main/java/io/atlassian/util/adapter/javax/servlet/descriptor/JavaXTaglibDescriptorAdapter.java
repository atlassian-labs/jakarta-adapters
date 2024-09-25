package io.atlassian.util.adapter.javax.servlet.descriptor;

import javax.servlet.descriptor.TaglibDescriptor;

import static java.util.Objects.requireNonNull;

public class JavaXTaglibDescriptorAdapter implements TaglibDescriptor {

    private final jakarta.servlet.descriptor.TaglibDescriptor delegate;

    public JavaXTaglibDescriptorAdapter(jakarta.servlet.descriptor.TaglibDescriptor delegate) {
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
