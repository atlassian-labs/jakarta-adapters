package io.atlassian.util.adapter.javax.servlet;

import io.atlassian.util.adapter.Adapted;
import io.atlassian.util.adapter.jakarta.servlet.JakartaHttpMethodConstraintElementAdapter;

import javax.servlet.HttpMethodConstraintElement;
import javax.servlet.annotation.ServletSecurity;

import static io.atlassian.util.adapter.util.WrapperUtil.applyIfNonNull;
import static java.util.Objects.requireNonNull;

public class JavaXHttpMethodConstraintElementAdapter extends HttpMethodConstraintElement implements Adapted<jakarta.servlet.HttpMethodConstraintElement> {

    private final jakarta.servlet.HttpMethodConstraintElement delegate;

    public static HttpMethodConstraintElement from(jakarta.servlet.HttpMethodConstraintElement delegate) {
        if (delegate instanceof JakartaHttpMethodConstraintElementAdapter castDelegate) {
            return castDelegate.getDelegate();
        }
        return applyIfNonNull(delegate, JavaXHttpMethodConstraintElementAdapter::new);
    }

    JavaXHttpMethodConstraintElementAdapter(jakarta.servlet.HttpMethodConstraintElement delegate) {
        super("null");
        this.delegate = requireNonNull(delegate);
    }

    @Override
    public jakarta.servlet.HttpMethodConstraintElement getDelegate() {
        return delegate;
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
