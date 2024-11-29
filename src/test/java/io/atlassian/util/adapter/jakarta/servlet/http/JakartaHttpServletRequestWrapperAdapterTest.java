package io.atlassian.util.adapter.jakarta.servlet.http;

import io.atlassian.util.adapter.javax.servlet.http.JavaXHttpServletRequestAdapter;
import io.atlassian.util.adapter.javax.servlet.http.JavaXHttpServletRequestWrapperAdapter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
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
class JakartaHttpServletRequestWrapperAdapterTest {

    @Mock
    private HttpServletRequest servletRequest;

    private HttpServletRequestWrapper biAdapted;

    @Mock
    private javax.servlet.http.HttpServletRequest javaxServletRequest;

    @BeforeEach
    void setUp() throws Exception {
        var wrapped = new HttpServletRequestWrapper(servletRequest);
        biAdapted = new JakartaHttpServletRequestWrapperAdapter(JavaXHttpServletRequestWrapperAdapter.from(wrapped));
    }

    @Test
    void getLocalName() {
        when(servletRequest.getLocalName()).thenReturn("123");

        assertEquals("123", biAdapted.getLocalName());
    }

    @Test
    void setRequest() {
        var newRequest = mock(HttpServletRequest.class);
        when(newRequest.getLocalName()).thenReturn("456");
        biAdapted.setRequest(newRequest);

        assertEquals("456", biAdapted.getLocalName());
    }

    /**
     * Tests that utility functions of the nature of {@link #unwrapCustomRequest(HttpServletRequest)} work as expected
     * in the presence of adapters.
     */
    @Test
    void jakartaServletRequestWrapperCanBeUnwrapped() {
        HttpServletRequestWrapper original = new CustomJakartaServletRequestWrapper(servletRequest);
        HttpServletRequestWrapper req = wrapAndAdaptPrepare(original);

        assertEquals(original, unwrapCustomRequest(req));
        assertTrue(req.isWrapperFor(original));
        assertTrue(req.isWrapperFor(original.getClass()));
    }

    /**
     * Tests that utility functions of the nature of {@link #unwrapCustomRequest(javax.servlet.http.HttpServletRequest)}
     * work as expected in the presence of adapters.
     */
    @Test
    void javaxServletRequestWrapperCanBeUnwrapped() {
        javax.servlet.http.HttpServletRequestWrapper original = new CustomJavaXServletRequestWrapper(javaxServletRequest);
        javax.servlet.http.HttpServletRequestWrapper req = wrapAndAdaptPrepare(original);

        assertEquals(original, unwrapCustomRequest(req));
        assertTrue(req.isWrapperFor(original));
        assertTrue(req.isWrapperFor(original.getClass()));
    }

    /**
     * Sequence of wrapping and adapting which mimics Jakarta servlet filters used in a JavaX servlet container.
     */
    private static HttpServletRequestWrapper wrapAndAdaptPrepare(HttpServletRequest request) {
        HttpServletRequest req = new HttpServletRequestWrapper(request);
        req = new HttpServletRequestWrapper(req);
        javax.servlet.http.HttpServletRequest javaxReq = JavaXHttpServletRequestAdapter.from(req);
        javaxReq = new javax.servlet.http.HttpServletRequestWrapper(javaxReq);
        javaxReq = new javax.servlet.http.HttpServletRequestWrapper(javaxReq);
        req = JakartaHttpServletRequestAdapter.from(javaxReq);
        req = new HttpServletRequestWrapper(req);
        req = new HttpServletRequestWrapper(req);
        return (HttpServletRequestWrapper) req;
    }

    /**
     * Sequence of wrapping and adapting which mimics JavaX servlet filters used in a Jakarta servlet container.
     */
    private static javax.servlet.http.HttpServletRequestWrapper wrapAndAdaptPrepare(javax.servlet.http.HttpServletRequest request) {
        javax.servlet.http.HttpServletRequest req = new javax.servlet.http.HttpServletRequestWrapper(request);
        req = new javax.servlet.http.HttpServletRequestWrapper(req);
        HttpServletRequest javaxReq = JakartaHttpServletRequestAdapter.from(req);
        javaxReq = new HttpServletRequestWrapper(javaxReq);
        javaxReq = new HttpServletRequestWrapper(javaxReq);
        req = JavaXHttpServletRequestAdapter.from(javaxReq);
        req = new javax.servlet.http.HttpServletRequestWrapper(req);
        req = new javax.servlet.http.HttpServletRequestWrapper(req);
        return (javax.servlet.http.HttpServletRequestWrapper) req;
    }

    /**
     * Mimic unwrapping logic used in certain utility methods in a Jakarta environment.
     */
    static CustomJakartaServletRequestWrapper unwrapCustomRequest(HttpServletRequest request) {
        if (request instanceof CustomJakartaServletRequestWrapper custom) {
            return custom;
        }
        if (request instanceof HttpServletRequestWrapper wrapped) {
            return unwrapCustomRequest((HttpServletRequest) wrapped.getRequest());
        }
        return null;
    }

    /**
     * Mimic unwrapping logic used in certain utility methods in a JavaX environment.
     */
    static CustomJavaXServletRequestWrapper unwrapCustomRequest(javax.servlet.http.HttpServletRequest request) {
        if (request instanceof CustomJavaXServletRequestWrapper custom) {
            return custom;
        }
        if (request instanceof javax.servlet.http.HttpServletRequestWrapper wrapped) {
            return unwrapCustomRequest((javax.servlet.http.HttpServletRequest) wrapped.getRequest());
        }
        return null;
    }

    static class CustomJakartaServletRequestWrapper extends HttpServletRequestWrapper {
        public CustomJakartaServletRequestWrapper(HttpServletRequest request) {
            super(request);
        }
    }

    static class CustomJavaXServletRequestWrapper extends javax.servlet.http.HttpServletRequestWrapper {
        public CustomJavaXServletRequestWrapper(javax.servlet.http.HttpServletRequest request) {
            super(request);
        }
    }
}
