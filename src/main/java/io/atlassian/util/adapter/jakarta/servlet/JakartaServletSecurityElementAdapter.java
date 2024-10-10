package io.atlassian.util.adapter.jakarta.servlet;

import jakarta.servlet.HttpMethodConstraintElement;
import jakarta.servlet.ServletSecurityElement;
import jakarta.servlet.annotation.ServletSecurity;

import java.util.Collection;

import static io.atlassian.util.adapter.util.WrapperUtil.transformIfNonNull;
import static java.util.Objects.requireNonNull;

public class JakartaServletSecurityElementAdapter extends ServletSecurityElement {

    private final javax.servlet.ServletSecurityElement delegate;

    public JakartaServletSecurityElementAdapter(javax.servlet.ServletSecurityElement delegate) {
        this.delegate = requireNonNull(delegate);
    }

    @Override
    public Collection<HttpMethodConstraintElement> getHttpMethodConstraints() {
        return transformIfNonNull(delegate.getHttpMethodConstraints(), JakartaHttpMethodConstraintElementAdapter::new);
    }

    @Override
    public Collection<String> getMethodNames() {
        return delegate.getMethodNames();
    }

    @Override
    public ServletSecurity.EmptyRoleSemantic getEmptyRoleSemantic() {
        return ServletSecurity.EmptyRoleSemantic.valueOf(delegate.getEmptyRoleSemantic().name());
    }

    @Override
    public ServletSecurity.TransportGuarantee getTransportGuarantee() {
        return ServletSecurity.TransportGuarantee.valueOf(delegate.getTransportGuarantee().name());
    }

    @Override
    public String[] getRolesAllowed() {
        return delegate.getRolesAllowed();
    }
}
