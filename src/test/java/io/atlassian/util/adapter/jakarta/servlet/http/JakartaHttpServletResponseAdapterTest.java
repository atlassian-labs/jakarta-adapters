package io.atlassian.util.adapter.jakarta.servlet.http;

import io.atlassian.util.adapter.javax.servlet.http.JavaXHttpServletResponseAdapter;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.PrintWriter;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class JakartaHttpServletResponseAdapterTest {

    @Mock
    private HttpServletResponse originalResponse;

    private HttpServletResponse biAdaptedResponse;

    @BeforeEach
    public void setUp() throws Exception {
        biAdaptedResponse = new JakartaHttpServletResponseAdapter(new JavaXHttpServletResponseAdapter(originalResponse));
    }

    @Test
    public void getCharacterEncoding() {
        when(originalResponse.getCharacterEncoding()).thenReturn("UTF-8");

        assertEquals("UTF-8", biAdaptedResponse.getCharacterEncoding());
    }

    @Test
    public void getContentType() {
        when(originalResponse.getContentType()).thenReturn("text/html");

        assertEquals("text/html", biAdaptedResponse.getContentType());
    }

    @Test
    public void getOutputStream() throws Exception {
        var retValue = mock(ServletOutputStream.class);
        when(retValue.isReady()).thenReturn(true);
        when(originalResponse.getOutputStream()).thenReturn(retValue);

        assertTrue(biAdaptedResponse.getOutputStream().isReady());
    }

    @Test
    public void getWriter() throws Exception {
        var retValue = mock(PrintWriter.class);
        when(originalResponse.getWriter()).thenReturn(retValue);

        assertEquals(retValue, biAdaptedResponse.getWriter());
    }

    @Test
    public void setCharacterEncoding() {
        biAdaptedResponse.setCharacterEncoding("UTF-16");

        verify(originalResponse).setCharacterEncoding("UTF-16");
    }

    @Test
    public void setContentLength() {
        biAdaptedResponse.setContentLength(100);

        verify(originalResponse).setContentLength(100);
    }

    @Test
    public void setContentLengthLong() {
        biAdaptedResponse.setContentLengthLong(100L);

        verify(originalResponse).setContentLengthLong(100L);
    }

    @Test
    public void setContentType() {
        biAdaptedResponse.setContentType("text/plain");

        verify(originalResponse).setContentType("text/plain");
    }

    @Test
    public void setBufferSize() {
        biAdaptedResponse.setBufferSize(100);

        verify(originalResponse).setBufferSize(100);
    }

    @Test
    public void getBufferSize() {
        when(originalResponse.getBufferSize()).thenReturn(100);

        assertEquals(100, biAdaptedResponse.getBufferSize());
    }

    @Test
    public void flushBuffer() throws Exception {
        biAdaptedResponse.flushBuffer();

        verify(originalResponse).flushBuffer();
    }

    @Test
    public void resetBuffer() {
        biAdaptedResponse.resetBuffer();

        verify(originalResponse).resetBuffer();
    }

    @Test
    public void isCommitted() {
        when(originalResponse.isCommitted()).thenReturn(true);

        assertTrue(biAdaptedResponse.isCommitted());
    }

    @Test
    public void reset() {
        biAdaptedResponse.reset();

        verify(originalResponse).reset();
    }

    @Test
    public void setLocale() {
        var locale = new Locale("en", "US");
        biAdaptedResponse.setLocale(locale);

        verify(originalResponse).setLocale(locale);
    }

    @Test
    public void getLocale() {
        var locale = new Locale("en", "US");
        when(originalResponse.getLocale()).thenReturn(locale);

        assertEquals(locale, biAdaptedResponse.getLocale());
    }

    @Test
    public void setTrailerFields() {
        Supplier<Map<String, String>> supplier = () -> Map.of("test", "value");
        biAdaptedResponse.setTrailerFields(supplier);

        verify(originalResponse).setTrailerFields(supplier);
    }

    @Test
    public void getTrailerFields() {
        when(originalResponse.getTrailerFields()).thenReturn(() -> Map.of("test", "value"));

        assertEquals("value", biAdaptedResponse.getTrailerFields().get().get("test"));
    }

    @Test
    public void addCookie() {
        var cookie = mock(Cookie.class);
        when(cookie.getName()).thenReturn("test");
        biAdaptedResponse.addCookie(cookie);

        verify(originalResponse).addCookie(argThat(c -> c.getName().equals("test")));
    }

    @Test
    public void containsHeader() {
        when(originalResponse.containsHeader("test")).thenReturn(true);

        assertTrue(biAdaptedResponse.containsHeader("test"));
    }

    @Test
    public void encodeURL() {
        when(originalResponse.encodeURL("test")).thenReturn("test");

        assertEquals("test", biAdaptedResponse.encodeURL("test"));
    }

    @Test
    public void encodeRedirectURL() {
        when(originalResponse.encodeRedirectURL("test")).thenReturn("test");

        assertEquals("test", biAdaptedResponse.encodeRedirectURL("test"));
    }

    @Test
    public void encodeUrl() {
        when(originalResponse.encodeUrl("test")).thenReturn("test");

        assertEquals("test", biAdaptedResponse.encodeUrl("test"));
    }

    @Test
    public void encodeRedirectUrl() {
        when(originalResponse.encodeRedirectUrl("test")).thenReturn("test");

        assertEquals("test", biAdaptedResponse.encodeRedirectUrl("test"));
    }

    @Test
    public void sendError() throws Exception {
        biAdaptedResponse.sendError(404, "Not Found");

        verify(originalResponse).sendError(404, "Not Found");
    }

    @Test
    public void testSendError() throws Exception {
        biAdaptedResponse.sendError(404);

        verify(originalResponse).sendError(404);
    }

    @Test
    public void sendRedirect() throws Exception {
        biAdaptedResponse.sendRedirect("http://localhost:8080");

        verify(originalResponse).sendRedirect("http://localhost:8080");
    }

    @Test
    public void setDateHeader() {
        biAdaptedResponse.setDateHeader("test", 100L);

        verify(originalResponse).setDateHeader("test", 100L);
    }

    @Test
    public void addDateHeader() {
        biAdaptedResponse.addDateHeader("test", 100L);

        verify(originalResponse).addDateHeader("test", 100L);
    }

    @Test
    public void setHeader() {
        biAdaptedResponse.setHeader("test", "value");

        verify(originalResponse).setHeader("test", "value");
    }

    @Test
    public void addHeader() {
        biAdaptedResponse.addHeader("test", "value");

        verify(originalResponse).addHeader("test", "value");
    }

    @Test
    public void setIntHeader() {
        biAdaptedResponse.setIntHeader("test", 100);

        verify(originalResponse).setIntHeader("test", 100);
    }

    @Test
    public void addIntHeader() {
        biAdaptedResponse.addIntHeader("test", 100);

        verify(originalResponse).addIntHeader("test", 100);
    }

    @Test
    public void setStatus() {
        biAdaptedResponse.setStatus(200);

        verify(originalResponse).setStatus(200);
    }

    @Test
    public void testSetStatus() {
        biAdaptedResponse.setStatus(200, "OK");

        verify(originalResponse).setStatus(200, "OK");
    }

    @Test
    public void getStatus() {
        when(originalResponse.getStatus()).thenReturn(200);

        assertEquals(200, biAdaptedResponse.getStatus());
    }

    @Test
    public void getHeader() {
        when(originalResponse.getHeader("test")).thenReturn("value");

        assertEquals("value", biAdaptedResponse.getHeader("test"));
    }

    @Test
    public void getHeaders() {
        when(originalResponse.getHeaders("test")).thenReturn(List.of("value"));

        assertEquals("value", biAdaptedResponse.getHeaders("test").iterator().next());
    }

    @Test
    public void getHeaderNames() {
        when(originalResponse.getHeaderNames()).thenReturn(List.of("test"));

        assertEquals("test", biAdaptedResponse.getHeaderNames().iterator().next());
    }

    @Test
    public void getDelegate() {
        var adaptedResponse = new JavaXHttpServletResponseAdapter(originalResponse);
        assertEquals(originalResponse, adaptedResponse.getDelegate());

        var biAdaptedResponse = new JakartaHttpServletResponseAdapter(adaptedResponse);
        assertEquals(adaptedResponse, biAdaptedResponse.getDelegate());
    }
}
