package io.atlassian.util.adapter.jakarta.servlet;

import io.atlassian.util.adapter.javax.servlet.JavaXServletAdapter;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class JakartaServletAdapterTest {

    @Mock
    private jakarta.servlet.Servlet originalServlet;

    private jakarta.servlet.Servlet biAdaptedServlet;

    @BeforeEach
    void setUp() throws Exception {
        biAdaptedServlet = new JakartaServletAdapter(JavaXServletAdapter.from(originalServlet));
    }

    @Test
    void init() throws Exception {
        var servletConfig = mock(ServletConfig.class);
        when(servletConfig.getServletName()).thenReturn("foo");

        biAdaptedServlet.init(servletConfig);

        verify(originalServlet).init(argThat(sC -> sC.getServletName().equals("foo")));
    }

    @Test
    void getServletConfig() {
        var servletConfig = mock(ServletConfig.class);
        when(servletConfig.getServletName()).thenReturn("foo");
        when(originalServlet.getServletConfig()).thenReturn(servletConfig);

        assertEquals("foo", biAdaptedServlet.getServletConfig().getServletName());
    }

    @Test
    void service() throws Exception {
        var servletRequest = mock(ServletRequest.class);
        when(servletRequest.getRemoteAddr()).thenReturn("foo");

        biAdaptedServlet.service(servletRequest, mock(ServletResponse.class));

        verify(originalServlet).service(argThat(sR -> sR.getRemoteAddr().equals("foo")), any());
    }

    @Test
    void getServletInfo() {
        when(originalServlet.getServletInfo()).thenReturn("foo");

        assertEquals("foo", biAdaptedServlet.getServletInfo());
    }

    @Test
    void destroy() {
        biAdaptedServlet.destroy();

        verify(originalServlet).destroy();
    }

    @Test
    void getDelegate() {
        var adaptedServlet = JavaXServletAdapter.from(originalServlet);
        assertEquals(originalServlet, ((JavaXServletAdapter) adaptedServlet).getDelegate());

        var javaXOriginal = mock(javax.servlet.Servlet.class);
        var adaptedServlet2 = JakartaServletAdapter.from(javaXOriginal);
        assertEquals(javaXOriginal, ((JakartaServletAdapter) adaptedServlet2).getDelegate());
    }

    @Test
    void doubleAdaptShortCircuit() {
        var adaptedServlet = JavaXServletAdapter.from(originalServlet);
        var biAdaptedServlet = JakartaServletAdapter.from(adaptedServlet);
        assertEquals(originalServlet, biAdaptedServlet);
    }
}
