package io.atlassian.util.adapter.jakarta.servlet;

import io.atlassian.util.adapter.javax.servlet.JavaXServletRegistrationAdapter;
import io.atlassian.util.adapter.util.WrapperUtil;

import jakarta.servlet.ServletRegistration;
import java.util.Collection;
import java.util.Set;

import static io.atlassian.util.adapter.util.WrapperUtil.applyIfNonNull;
import static java.util.Objects.requireNonNull;

public class JakartaServletRegistrationAdapter extends JakartaRegistrationAdapter implements ServletRegistration {

    private final javax.servlet.ServletRegistration delegate;

    public static ServletRegistration from(javax.servlet.ServletRegistration delegate) {
        if (delegate instanceof javax.servlet.ServletRegistration.Dynamic castDelegate) {
            return JakartaDynamicServletRegistrationAdapter.from(castDelegate);
        }
        if (delegate instanceof JavaXServletRegistrationAdapter castDelegate) {
            return castDelegate.getDelegate();
        }
        return applyIfNonNull(delegate, JakartaServletRegistrationAdapter::new);
    }

    protected JakartaServletRegistrationAdapter(javax.servlet.ServletRegistration delegate) {
        super(delegate);
        this.delegate = requireNonNull(delegate);
    }

    @Override
    public javax.servlet.ServletRegistration getDelegate() {
        return delegate;
    }

    @Override
    public Set<String> addMapping(String... urlPatterns) {
        return delegate.addMapping(urlPatterns);
    }

    @Override
    public Collection<String> getMappings() {
        return delegate.getMappings();
    }

    @Override
    public String getRunAsRole() {
        return delegate.getRunAsRole();
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
