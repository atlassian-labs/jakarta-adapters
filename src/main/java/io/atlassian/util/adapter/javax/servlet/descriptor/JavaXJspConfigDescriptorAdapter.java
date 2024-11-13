package io.atlassian.util.adapter.javax.servlet.descriptor;

import io.atlassian.util.adapter.Adapted;
import io.atlassian.util.adapter.jakarta.servlet.descriptor.JakartaJspConfigDescriptorAdapter;

import javax.servlet.descriptor.JspConfigDescriptor;
import javax.servlet.descriptor.JspPropertyGroupDescriptor;
import javax.servlet.descriptor.TaglibDescriptor;
import java.util.Collection;

import static io.atlassian.util.adapter.util.WrapperUtil.applyIfNonNull;
import static io.atlassian.util.adapter.util.WrapperUtil.transformIfNonNull;
import static java.util.Objects.requireNonNull;

public class JavaXJspConfigDescriptorAdapter implements JspConfigDescriptor, Adapted<jakarta.servlet.descriptor.JspConfigDescriptor> {

    private final jakarta.servlet.descriptor.JspConfigDescriptor delegate;

    public static JspConfigDescriptor from(jakarta.servlet.descriptor.JspConfigDescriptor delegate) {
        if (delegate instanceof JakartaJspConfigDescriptorAdapter castDelegate) {
            return castDelegate.getDelegate();
        }
        return applyIfNonNull(delegate, JavaXJspConfigDescriptorAdapter::new);
    }

    protected JavaXJspConfigDescriptorAdapter(jakarta.servlet.descriptor.JspConfigDescriptor delegate) {
        this.delegate = requireNonNull(delegate);
    }

    @Override
    public jakarta.servlet.descriptor.JspConfigDescriptor getDelegate() {
        return delegate;
    }

    @Override
    public Collection<JspPropertyGroupDescriptor> getJspPropertyGroups() {
        return transformIfNonNull(delegate.getJspPropertyGroups(), JavaXJspPropertyGroupDescriptorAdapter::from);
    }

    @Override
    public Collection<TaglibDescriptor> getTaglibs() {
        return transformIfNonNull(delegate.getTaglibs(), JavaXTaglibDescriptorAdapter::from);
    }
}
