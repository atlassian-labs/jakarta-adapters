package io.atlassian.util.adapter.jakarta.servlet;

import jakarta.servlet.ServletRegistration;

import java.util.Collection;
import java.util.Set;

import static java.util.Objects.requireNonNull;

public class JakartaServletRegistrationAdapter extends JakartaRegistrationAdapter implements ServletRegistration {

    private final javax.servlet.ServletRegistration delegate;

    public JakartaServletRegistrationAdapter(javax.servlet.ServletRegistration delegate) {
        super(delegate);
        this.delegate = requireNonNull(delegate);
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
}
