package io.atlassian.util.adapter.jakarta.servlet;

import io.atlassian.util.adapter.javax.servlet.JavaXServletSecurityElementAdapter;
import jakarta.servlet.HttpMethodConstraintElement;
import jakarta.servlet.ServletSecurityElement;
import jakarta.servlet.annotation.ServletSecurity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class JakartaServletSecurityElementAdapterTest {

    @Mock
    private ServletSecurityElement original;

    private ServletSecurityElement biAdapted;

    @BeforeEach
    public void setUp() throws Exception {
        biAdapted = new JakartaServletSecurityElementAdapter((new JavaXServletSecurityElementAdapter(original)));
    }

    @Test
    public void getHttpMethodConstraints() {
        var retValue = mock(HttpMethodConstraintElement.class);
        when(retValue.getMethodName()).thenReturn("GET");
        when(original.getHttpMethodConstraints()).thenReturn(List.of(retValue));

        assertEquals("GET", biAdapted.getHttpMethodConstraints().iterator().next().getMethodName());
    }

    @Test
    public void getMethodNames() {
        when(original.getMethodNames()).thenReturn(List.of("GET"));

        assertEquals("GET", biAdapted.getMethodNames().iterator().next());
    }

    @Test
    public void getEmptyRoleSemantic() {
        when(original.getEmptyRoleSemantic()).thenReturn(ServletSecurity.EmptyRoleSemantic.DENY);

        assertEquals(ServletSecurity.EmptyRoleSemantic.DENY, biAdapted.getEmptyRoleSemantic());
    }

    @Test
    public void getTransportGuarantee() {
        when(original.getTransportGuarantee()).thenReturn(ServletSecurity.TransportGuarantee.NONE);

        assertEquals(ServletSecurity.TransportGuarantee.NONE, biAdapted.getTransportGuarantee());
    }

    @Test
    public void getRolesAllowed() {
        when(original.getRolesAllowed()).thenReturn(new String[]{"role"});

        assertEquals("role", biAdapted.getRolesAllowed()[0]);
    }
}
