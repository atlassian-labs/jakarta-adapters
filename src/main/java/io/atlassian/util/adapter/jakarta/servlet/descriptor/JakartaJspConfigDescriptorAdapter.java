package io.atlassian.util.adapter.jakarta.servlet.descriptor;

import jakarta.servlet.descriptor.JspConfigDescriptor;
import jakarta.servlet.descriptor.JspPropertyGroupDescriptor;
import jakarta.servlet.descriptor.TaglibDescriptor;

import java.util.Collection;

import static io.atlassian.util.adapter.util.WrapperUtil.transformIfNonNull;
import static java.util.Objects.requireNonNull;

public class JakartaJspConfigDescriptorAdapter implements JspConfigDescriptor {

    private final javax.servlet.descriptor.JspConfigDescriptor delegate;

    public JakartaJspConfigDescriptorAdapter(javax.servlet.descriptor.JspConfigDescriptor delegate) {
        this.delegate = requireNonNull(delegate);
    }

    @Override
    public Collection<JspPropertyGroupDescriptor> getJspPropertyGroups() {
        return transformIfNonNull(delegate.getJspPropertyGroups(), JakartaJspPropertyGroupDescriptorAdapter::new);
    }

    @Override
    public Collection<TaglibDescriptor> getTaglibs() {
        return transformIfNonNull(delegate.getTaglibs(), JakartaTaglibDescriptorAdapter::new);
    }
}
