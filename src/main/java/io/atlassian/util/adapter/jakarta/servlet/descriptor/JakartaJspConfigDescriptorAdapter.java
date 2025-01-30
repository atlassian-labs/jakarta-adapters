package io.atlassian.util.adapter.jakarta.servlet.descriptor;

import io.atlassian.util.adapter.Adapted;
import io.atlassian.util.adapter.javax.servlet.descriptor.JavaXJspConfigDescriptorAdapter;
import io.atlassian.util.adapter.util.WrapperUtil;

import jakarta.servlet.descriptor.JspConfigDescriptor;
import jakarta.servlet.descriptor.JspPropertyGroupDescriptor;
import jakarta.servlet.descriptor.TaglibDescriptor;
import java.util.Collection;

import static io.atlassian.util.adapter.util.WrapperUtil.applyIfNonNull;
import static io.atlassian.util.adapter.util.WrapperUtil.transformIfNonNull;
import static java.util.Objects.requireNonNull;

public class JakartaJspConfigDescriptorAdapter implements JspConfigDescriptor, Adapted<javax.servlet.descriptor.JspConfigDescriptor> {

    private final javax.servlet.descriptor.JspConfigDescriptor delegate;

    public static JspConfigDescriptor from(javax.servlet.descriptor.JspConfigDescriptor delegate) {
        if (delegate instanceof JavaXJspConfigDescriptorAdapter castDelegate) {
            return castDelegate.getDelegate();
        }
        return applyIfNonNull(delegate, JakartaJspConfigDescriptorAdapter::new);
    }

    protected JakartaJspConfigDescriptorAdapter(javax.servlet.descriptor.JspConfigDescriptor delegate) {
        this.delegate = requireNonNull(delegate);
    }

    @Override
    public javax.servlet.descriptor.JspConfigDescriptor getDelegate() {
        return delegate;
    }

    @Override
    public Collection<JspPropertyGroupDescriptor> getJspPropertyGroups() {
        return transformIfNonNull(delegate.getJspPropertyGroups(), JakartaJspPropertyGroupDescriptorAdapter::from);
    }

    @Override
    public Collection<TaglibDescriptor> getTaglibs() {
        return transformIfNonNull(delegate.getTaglibs(), JakartaTaglibDescriptorAdapter::from);
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
