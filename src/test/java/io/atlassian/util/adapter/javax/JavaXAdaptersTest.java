package io.atlassian.util.adapter.javax;

import jakarta.servlet.Filter;
import jakarta.servlet.Servlet;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class JavaXAdaptersTest {

    @Test
    void servletContext() {
        var delegate = mock(ServletContext.class);
        when(delegate.getContextPath()).thenReturn("foo");

        var proxy = JavaXAdapters.asJavaX(delegate);

        assertThat(proxy.getContextPath()).isEqualTo("foo");
        verify(delegate).getContextPath();
    }

    @Test
    void servletContext_null() {
        var delegate = mock(ServletContext.class);
        doReturn(null).when(delegate).getContext("test");

        var proxy = JavaXAdapters.asJavaX(delegate);

        assertThat(proxy.getContext("test")).isNull();
    }

    /**
     * It is imperative that when we pass in a {@link ServletRequest} which is actually a {@link HttpServletRequest},
     * that the returned proxy is also a {@link javax.servlet.http.HttpServletRequest} and not a
     * {@link javax.servlet.ServletRequest}.
     */
    @Test
    void servletRequest() {
        var delegate = mock(HttpServletRequest.class);
        when(delegate.getAuthType()).thenReturn("foo");

        var proxy = JavaXAdapters.asJavaX((ServletRequest) delegate);

        assertThat(((javax.servlet.http.HttpServletRequest) proxy).getAuthType()).isEqualTo("foo");
        verify(delegate).getAuthType();
    }

    @Test
    void httpServletRequest() {
        var delegate = mock(HttpServletRequest.class);
        when(delegate.getAuthType()).thenReturn("foo");

        var proxy = JavaXAdapters.asJavaX(delegate);

        assertThat(proxy.getAuthType()).isEqualTo("foo");
        verify(delegate).getAuthType();
    }

    /**
     * It is imperative that when we pass in a {@link ServletResponse} which is actually a {@link HttpServletResponse},
     * that the returned proxy is also a {@link javax.servlet.http.HttpServletResponse} and not a
     * {@link javax.servlet.ServletResponse}.
     */
    @Test
    void servletResponse() {
        var delegate = mock(HttpServletResponse.class);
        when(delegate.getStatus()).thenReturn(200);

        var proxy = JavaXAdapters.asJavaX((ServletResponse) delegate);

        assertThat(((javax.servlet.http.HttpServletResponse) proxy).getStatus()).isEqualTo(200);
        verify(delegate).getStatus();
    }

    @Test
    void httpServletResponse() {
        var delegate = mock(HttpServletResponse.class);
        when(delegate.getStatus()).thenReturn(200);

        var proxy = JavaXAdapters.asJavaX(delegate);

        assertThat(proxy.getStatus()).isEqualTo(200);
        verify(delegate).getStatus();
    }

    @Test
    void servletFilter() throws Exception {
        var delegate = mock(Filter.class);
        var filterConfig = mock(javax.servlet.FilterConfig.class);
        when(filterConfig.getFilterName()).thenReturn("foo");

        var proxy = JavaXAdapters.asJavaX(delegate);
        proxy.init(filterConfig);

        verify(delegate).init(argThat(fC -> fC.getFilterName().equals("foo")));
    }

    @Test
    void servlet() {
        var delegate = mock(Servlet.class);
        when(delegate.getServletInfo()).thenReturn("foo");

        var proxy = JavaXAdapters.asJavaX(delegate);

        assertThat(proxy.getServletInfo()).isEqualTo("foo");
        verify(delegate).getServletInfo();
    }

    @Test
    void dynamicAdapter() {
        assertThat(JavaXAdapters.asJavaXIfJakarta(mock(Servlet.class))).isInstanceOf(javax.servlet.Servlet.class);
        assertThat(JavaXAdapters.asJavaXIfJakarta(mock(ServletRequest.class))).isInstanceOf(javax.servlet.ServletRequest.class);
        assertThat(JavaXAdapters.asJavaXIfJakarta(mock(HttpServletRequest.class))).isInstanceOf(javax.servlet.http.HttpServletRequest.class);
        assertThat(JavaXAdapters.asJavaXIfJakarta(mock(ServletResponse.class))).isInstanceOf(javax.servlet.ServletResponse.class);
        assertThat(JavaXAdapters.asJavaXIfJakarta(mock(HttpServletResponse.class))).isInstanceOf(javax.servlet.http.HttpServletResponse.class);
        assertThat(JavaXAdapters.asJavaXIfJakarta(mock(ServletContext.class))).isInstanceOf(javax.servlet.ServletContext.class);
        assertThat(JavaXAdapters.asJavaXIfJakarta(mock(Filter.class))).isInstanceOf(javax.servlet.Filter.class);
    }
}
