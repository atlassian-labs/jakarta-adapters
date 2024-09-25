package io.atlassian.util.adapter.jakarta.servlet.descriptor;

import jakarta.servlet.descriptor.JspConfigDescriptor;
import jakarta.servlet.descriptor.JspPropertyGroupDescriptor;
import jakarta.servlet.descriptor.TaglibDescriptor;

import java.util.Collection;

import static java.util.Objects.requireNonNull;
import static java.util.stream.Collectors.toList;

public class JakartaJspConfigDescriptorAdapter implements JspConfigDescriptor {

    private final javax.servlet.descriptor.JspConfigDescriptor delegate;

    public JakartaJspConfigDescriptorAdapter(javax.servlet.descriptor.JspConfigDescriptor delegate) {
        this.delegate = requireNonNull(delegate);
    }

    @Override
    public Collection<JspPropertyGroupDescriptor> getJspPropertyGroups() {
        var jspPropertyGroups = delegate.getJspPropertyGroups();
        if (jspPropertyGroups == null) {
            return null;
        }
        return jspPropertyGroups.stream().map(JakartaJspPropertyGroupDescriptorAdapter::new).collect(toList());
    }

    @Override
    public Collection<TaglibDescriptor> getTaglibs() {
        var taglibs = delegate.getTaglibs();
        if (taglibs == null) {
            return null;
        }
        return taglibs.stream().map(JakartaTaglibDescriptorAdapter::new).collect(toList());
    }
}
