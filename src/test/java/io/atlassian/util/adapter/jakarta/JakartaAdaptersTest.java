package io.atlassian.util.adapter.jakarta;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.servlet.Filter;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class JakartaAdaptersTest {

    @Test
    void servletContext() {
        var delegate = mock(ServletContext.class);
        when(delegate.getContextPath()).thenReturn("foo");

        var proxy = JakartaAdapters.asJakarta(delegate);

        assertThat(proxy.getContextPath()).isEqualTo("foo");
        verify(delegate).getContextPath();
    }

    @Test
    void servletContext_null() {
        var delegate = mock(ServletContext.class);
        doReturn(null).when(delegate).getContext("test");

        var proxy = JakartaAdapters.asJakarta(delegate);

        assertThat(proxy.getContext("test")).isNull();
    }

    /**
     * It is imperative that when we pass in a {@link ServletRequest} which is actually a {@link HttpServletRequest},
     * that the returned proxy is also a {@link jakarta.servlet.http.HttpServletRequest} and not a
     * {@link jakarta.servlet.ServletRequest}.
     */
    @Test
    void servletRequest() {
        var delegate = mock(HttpServletRequest.class);
        when(delegate.getAuthType()).thenReturn("foo");

        var proxy = JakartaAdapters.asJakarta((ServletRequest) delegate);

        assertThat(((jakarta.servlet.http.HttpServletRequest) proxy).getAuthType()).isEqualTo("foo");
        verify(delegate).getAuthType();
    }

    @Test
    void httpServletRequest() {
        var delegate = mock(HttpServletRequest.class);
        when(delegate.getAuthType()).thenReturn("foo");

        var proxy = JakartaAdapters.asJakarta(delegate);

        assertThat(proxy.getAuthType()).isEqualTo("foo");
        verify(delegate).getAuthType();
    }

    /**
     * It is imperative that when we pass in a {@link ServletResponse} which is actually a {@link HttpServletResponse},
     * that the returned proxy is also a {@link jakarta.servlet.http.HttpServletResponse} and not a
     * {@link jakarta.servlet.ServletResponse}.
     */
    @Test
    void servletResponse() {
        var delegate = mock(HttpServletResponse.class);
        when(delegate.getStatus()).thenReturn(200);

        var proxy = JakartaAdapters.asJakarta((ServletResponse) delegate);

        assertThat(((jakarta.servlet.http.HttpServletResponse) proxy).getStatus()).isEqualTo(200);
        verify(delegate).getStatus();
    }

    @Test
    void httpServletResponse() {
        var delegate = mock(HttpServletResponse.class);
        when(delegate.getStatus()).thenReturn(200);

        var proxy = JakartaAdapters.asJakarta(delegate);

        assertThat(proxy.getStatus()).isEqualTo(200);
        verify(delegate).getStatus();
    }

    @Test
    void servletFilter() throws Exception {
        var delegate = mock(Filter.class);
        var filterConfig = mock(jakarta.servlet.FilterConfig.class);
        when(filterConfig.getFilterName()).thenReturn("foo");

        var proxy = JakartaAdapters.asJakarta(delegate);
        proxy.init(filterConfig);

        verify(delegate).init(argThat(fC -> fC.getFilterName().equals("foo")));
    }

    @Test
    void servlet() {
        var delegate = mock(Servlet.class);
        when(delegate.getServletInfo()).thenReturn("foo");

        var proxy = JakartaAdapters.asJakarta(delegate);

        assertThat(proxy.getServletInfo()).isEqualTo("foo");
        verify(delegate).getServletInfo();
    }

    @Test
    void servletConfig() {
        var delegate = mock(ServletConfig.class);
        when(delegate.getServletName()).thenReturn("foo");

        var proxy = JakartaAdapters.asJakarta(delegate);

        assertThat(proxy.getServletName()).isEqualTo("foo");
        verify(delegate).getServletName();
    }

    @Test
    void dynamicAdapter() {
        assertThat(JakartaAdapters.asJakartaIfJavaX(mock(Servlet.class))).isInstanceOf(jakarta.servlet.Servlet.class);
        assertThat(JakartaAdapters.asJakartaIfJavaX(mock(ServletRequest.class))).isInstanceOf(jakarta.servlet.ServletRequest.class);
        assertThat(JakartaAdapters.asJakartaIfJavaX(mock(HttpServletRequest.class))).isInstanceOf(jakarta.servlet.http.HttpServletRequest.class);
        assertThat(JakartaAdapters.asJakartaIfJavaX(mock(ServletResponse.class))).isInstanceOf(jakarta.servlet.ServletResponse.class);
        assertThat(JakartaAdapters.asJakartaIfJavaX(mock(HttpServletResponse.class))).isInstanceOf(jakarta.servlet.http.HttpServletResponse.class);
        assertThat(JakartaAdapters.asJakartaIfJavaX(mock(ServletContext.class))).isInstanceOf(jakarta.servlet.ServletContext.class);
        assertThat(JakartaAdapters.asJakartaIfJavaX(mock(Filter.class))).isInstanceOf(jakarta.servlet.Filter.class);
    }
}
