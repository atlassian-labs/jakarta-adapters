package io.atlassian.util.adapter.javax.servlet.descriptor;

import javax.servlet.descriptor.JspConfigDescriptor;
import javax.servlet.descriptor.JspPropertyGroupDescriptor;
import javax.servlet.descriptor.TaglibDescriptor;
import java.util.Collection;

import static java.util.Objects.requireNonNull;
import static java.util.stream.Collectors.toList;

public class JavaXJspConfigDescriptorAdapter implements JspConfigDescriptor {

    private final jakarta.servlet.descriptor.JspConfigDescriptor delegate;

    public JavaXJspConfigDescriptorAdapter(jakarta.servlet.descriptor.JspConfigDescriptor delegate) {
        this.delegate = requireNonNull(delegate);
    }

    @Override
    public Collection<JspPropertyGroupDescriptor> getJspPropertyGroups() {
        var jspPropertyGroups = delegate.getJspPropertyGroups();
        if (jspPropertyGroups == null) {
            return null;
        }
        return jspPropertyGroups.stream().map(JavaXJspPropertyGroupDescriptorAdapter::new).collect(toList());
    }

    @Override
    public Collection<TaglibDescriptor> getTaglibs() {
        var taglibs = delegate.getTaglibs();
        return taglibs.stream().map(JavaXTaglibDescriptorAdapter::new).collect(toList());
    }
}
