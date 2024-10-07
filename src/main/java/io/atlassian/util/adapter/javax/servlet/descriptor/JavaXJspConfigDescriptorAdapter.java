package io.atlassian.util.adapter.javax.servlet.descriptor;

import javax.servlet.descriptor.JspConfigDescriptor;
import javax.servlet.descriptor.JspPropertyGroupDescriptor;
import javax.servlet.descriptor.TaglibDescriptor;
import java.util.Collection;

import static io.atlassian.util.adapter.util.WrapperUtil.transformIfNonNull;
import static java.util.Objects.requireNonNull;

public class JavaXJspConfigDescriptorAdapter implements JspConfigDescriptor {

    private final jakarta.servlet.descriptor.JspConfigDescriptor delegate;

    public JavaXJspConfigDescriptorAdapter(jakarta.servlet.descriptor.JspConfigDescriptor delegate) {
        this.delegate = requireNonNull(delegate);
    }

    @Override
    public Collection<JspPropertyGroupDescriptor> getJspPropertyGroups() {
        return transformIfNonNull(delegate.getJspPropertyGroups(), JavaXJspPropertyGroupDescriptorAdapter::new);
    }

    @Override
    public Collection<TaglibDescriptor> getTaglibs() {
        return transformIfNonNull(delegate.getTaglibs(), JavaXTaglibDescriptorAdapter::new);
    }
}
