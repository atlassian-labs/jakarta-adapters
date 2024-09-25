package io.atlassian.util.adapter.jakarta.servlet;

import jakarta.servlet.HttpMethodConstraintElement;
import jakarta.servlet.annotation.ServletSecurity;

import static java.util.Objects.requireNonNull;

public class JakartaHttpMethodConstraintElementAdapter extends HttpMethodConstraintElement {

    private final javax.servlet.HttpMethodConstraintElement delegate;

    public JakartaHttpMethodConstraintElementAdapter(javax.servlet.HttpMethodConstraintElement delegate) {
        super("null");
        this.delegate = requireNonNull(delegate);
    }

    @Override
    public String getMethodName() {
        return delegate.getMethodName();
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
