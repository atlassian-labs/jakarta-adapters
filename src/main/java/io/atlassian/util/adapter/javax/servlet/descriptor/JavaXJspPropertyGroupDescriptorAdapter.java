package io.atlassian.util.adapter.javax.servlet.descriptor;

import io.atlassian.util.adapter.Adapted;
import io.atlassian.util.adapter.jakarta.servlet.descriptor.JakartaJspPropertyGroupDescriptorAdapter;

import javax.servlet.descriptor.JspPropertyGroupDescriptor;
import java.util.Collection;

import static io.atlassian.util.adapter.util.WrapperUtil.applyIfNonNull;
import static java.util.Objects.requireNonNull;

public class JavaXJspPropertyGroupDescriptorAdapter implements JspPropertyGroupDescriptor, Adapted<jakarta.servlet.descriptor.JspPropertyGroupDescriptor> {

    private final jakarta.servlet.descriptor.JspPropertyGroupDescriptor delegate;

    public static JspPropertyGroupDescriptor from(jakarta.servlet.descriptor.JspPropertyGroupDescriptor delegate) {
        if (delegate instanceof JakartaJspPropertyGroupDescriptorAdapter castDelegate) {
            return castDelegate.getDelegate();
        }
        return applyIfNonNull(delegate, JavaXJspPropertyGroupDescriptorAdapter::new);
    }

    protected JavaXJspPropertyGroupDescriptorAdapter(jakarta.servlet.descriptor.JspPropertyGroupDescriptor delegate) {
        this.delegate = requireNonNull(delegate);
    }

    @Override
    public jakarta.servlet.descriptor.JspPropertyGroupDescriptor getDelegate() {
        return delegate;
    }

    @Override
    public Collection<String> getUrlPatterns() {
        return delegate.getUrlPatterns();
    }

    @Override
    public String getElIgnored() {
        return delegate.getElIgnored();
    }

    @Override
    public String getPageEncoding() {
        return delegate.getPageEncoding();
    }

    @Override
    public String getScriptingInvalid() {
        return delegate.getScriptingInvalid();
    }

    @Override
    public String getIsXml() {
        return delegate.getIsXml();
    }

    @Override
    public Collection<String> getIncludePreludes() {
        return delegate.getIncludePreludes();
    }

    @Override
    public Collection<String> getIncludeCodas() {
        return delegate.getIncludeCodas();
    }

    @Override
    public String getDeferredSyntaxAllowedAsLiteral() {
        return delegate.getDeferredSyntaxAllowedAsLiteral();
    }

    @Override
    public String getTrimDirectiveWhitespaces() {
        return delegate.getTrimDirectiveWhitespaces();
    }

    @Override
    public String getDefaultContentType() {
        return delegate.getDefaultContentType();
    }

    @Override
    public String getBuffer() {
        return delegate.getBuffer();
    }

    @Override
    public String getErrorOnUndeclaredNamespace() {
        return delegate.getErrorOnUndeclaredNamespace();
    }
}
