package io.atlassian.util.adapter.jakarta.servlet;

import io.atlassian.util.adapter.javax.servlet.JavaXMultipartConfigElementAdapter;
import io.atlassian.util.adapter.javax.servlet.JavaXServletSecurityElementAdapter;
import jakarta.servlet.MultipartConfigElement;
import jakarta.servlet.ServletRegistration;
import jakarta.servlet.ServletSecurityElement;

import java.util.Set;

import static io.atlassian.util.adapter.util.WrapperUtil.applyIfNonNull;
import static java.util.Objects.requireNonNull;

public class JakartaDynamicServletRegistrationAdapter extends JakartaServletRegistrationAdapter implements ServletRegistration.Dynamic {

    private final javax.servlet.ServletRegistration.Dynamic delegate;

    public JakartaDynamicServletRegistrationAdapter(javax.servlet.ServletRegistration.Dynamic delegate) {
        super(delegate);
        this.delegate = requireNonNull(delegate);
    }

    @Override
    public void setLoadOnStartup(int loadOnStartup) {
        delegate.setLoadOnStartup(loadOnStartup);
    }

    @Override
    public Set<String> setServletSecurity(ServletSecurityElement constraint) {
        return delegate.setServletSecurity(applyIfNonNull(constraint, JavaXServletSecurityElementAdapter::new));
    }

    @Override
    public void setMultipartConfig(MultipartConfigElement multipartConfig) {
        delegate.setMultipartConfig(applyIfNonNull(multipartConfig, JavaXMultipartConfigElementAdapter::new));
    }

    @Override
    public void setRunAsRole(String roleName) {
        delegate.setRunAsRole(roleName);
    }

    @Override
    public void setAsyncSupported(boolean isAsyncSupported) {
        delegate.setAsyncSupported(isAsyncSupported);
    }
}
