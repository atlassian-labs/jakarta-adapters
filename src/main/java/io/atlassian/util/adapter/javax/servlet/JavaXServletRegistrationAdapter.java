package io.atlassian.util.adapter.javax.servlet;

import javax.servlet.ServletRegistration;
import java.util.Collection;
import java.util.Set;

import static java.util.Objects.requireNonNull;

public class JavaXServletRegistrationAdapter extends JavaXRegistrationAdapter implements ServletRegistration {

    private final jakarta.servlet.ServletRegistration delegate;

    public JavaXServletRegistrationAdapter(jakarta.servlet.ServletRegistration delegate) {
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
