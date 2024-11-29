package io.atlassian.util.adapter.jakarta.servlet;

import io.atlassian.util.adapter.javax.servlet.JavaXServletRequestAdapter;
import io.atlassian.util.adapter.javax.servlet.JavaXServletRequestWrapperAdapter;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletRequestWrapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class JakartaServletRequestWrapperAdapterTest {

    @Mock
    private ServletRequest servletRequest;

    private ServletRequestWrapper biAdapted;

    @Mock
    private javax.servlet.ServletRequest javaxServletRequest;

    @BeforeEach
    void setUp() throws Exception {
        var wrapped = new ServletRequestWrapper(servletRequest);
        biAdapted = new JakartaServletRequestWrapperAdapter(JavaXServletRequestWrapperAdapter.from(wrapped));
    }

    @Test
    void getLocalName() {
        when(servletRequest.getLocalName()).thenReturn("123");

        assertEquals("123", biAdapted.getLocalName());
    }

    @Test
    void setRequest() {
        var newRequest = mock(ServletRequest.class);
        when(newRequest.getLocalName()).thenReturn("456");
        biAdapted.setRequest(newRequest);

        assertEquals("456", biAdapted.getLocalName());
    }

    @Test
    void jakartaServletRequestWrapperCanBeUnwrapped() {
        ServletRequestWrapper original = new CustomJakartaServletRequestWrapper(servletRequest);
        ServletRequestWrapper req = wrapAndAdaptPrepare(original);

        assertEquals(original, unwrapCustomRequest(req));
        assertTrue(req.isWrapperFor(original));
        assertTrue(req.isWrapperFor(original.getClass()));
    }

    @Test
    void javaxServletRequestWrapperCanBeUnwrapped() {
        javax.servlet.ServletRequestWrapper original = new CustomJavaXServletRequestWrapper(javaxServletRequest);
        javax.servlet.ServletRequestWrapper req = wrapAndAdaptPrepare(original);

        assertEquals(original, unwrapCustomRequest(req));
        assertTrue(req.isWrapperFor(original));
        assertTrue(req.isWrapperFor(original.getClass()));
    }

    /**
     * Sequence of wrapping and adapting which mimics Jakarta servlet filters used in a JavaX servlet container.
     */
    private static ServletRequestWrapper wrapAndAdaptPrepare(ServletRequest request) {
        ServletRequest req = new ServletRequestWrapper(request);
        req = new ServletRequestWrapper(req);
        javax.servlet.ServletRequest javaxReq = JavaXServletRequestAdapter.from(req);
        javaxReq = new javax.servlet.ServletRequestWrapper(javaxReq);
        javaxReq = new javax.servlet.ServletRequestWrapper(javaxReq);
        req = JakartaServletRequestAdapter.from(javaxReq);
        req = new ServletRequestWrapper(req);
        req = new ServletRequestWrapper(req);
        return (ServletRequestWrapper) req;
    }

    /**
     * Sequence of wrapping and adapting which mimics JavaX servlet filters used in a Jakarta servlet container.
     */
    private static javax.servlet.ServletRequestWrapper wrapAndAdaptPrepare(javax.servlet.ServletRequest request) {
        javax.servlet.ServletRequest req = new javax.servlet.ServletRequestWrapper(request);
        req = new javax.servlet.ServletRequestWrapper(req);
        ServletRequest javaxReq = JakartaServletRequestAdapter.from(req);
        javaxReq = new ServletRequestWrapper(javaxReq);
        javaxReq = new ServletRequestWrapper(javaxReq);
        req = JavaXServletRequestAdapter.from(javaxReq);
        req = new javax.servlet.ServletRequestWrapper(req);
        req = new javax.servlet.ServletRequestWrapper(req);
        return (javax.servlet.ServletRequestWrapper) req;
    }

    /**
     * Mimic unwrapping logic used in certain utility methods in a Jakarta environment.
     */
    static CustomJakartaServletRequestWrapper unwrapCustomRequest(ServletRequest request) {
        if (request instanceof CustomJakartaServletRequestWrapper custom) {
            return custom;
        }
        if (request instanceof ServletRequestWrapper wrapped) {
            return unwrapCustomRequest(wrapped.getRequest());
        }
        return null;
    }

    /**
     * Mimic unwrapping logic used in certain utility methods in a JavaX environment.
     */
    static CustomJavaXServletRequestWrapper unwrapCustomRequest(javax.servlet.ServletRequest request) {
        if (request instanceof CustomJavaXServletRequestWrapper custom) {
            return custom;
        }
        if (request instanceof javax.servlet.ServletRequestWrapper wrapped) {
            return unwrapCustomRequest(wrapped.getRequest());
        }
        return null;
    }

    static class CustomJakartaServletRequestWrapper extends ServletRequestWrapper {
        public CustomJakartaServletRequestWrapper(ServletRequest request) {
            super(request);
        }
    }

    static class CustomJavaXServletRequestWrapper extends javax.servlet.ServletRequestWrapper {
        public CustomJavaXServletRequestWrapper(javax.servlet.ServletRequest request) {
            super(request);
        }
    }
}
