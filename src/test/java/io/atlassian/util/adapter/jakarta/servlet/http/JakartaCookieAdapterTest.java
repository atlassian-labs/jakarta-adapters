package io.atlassian.util.adapter.jakarta.servlet.http;

import io.atlassian.util.adapter.javax.servlet.http.JavaXCookieAdapter;
import jakarta.servlet.http.Cookie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class JakartaCookieAdapterTest {

    @Mock
    private Cookie originalCookie;

    private Cookie biAdaptedCookie;

    @BeforeEach
    void setUp() throws Exception {
        biAdaptedCookie = new JakartaCookieAdapter(new JavaXCookieAdapter(originalCookie));
    }

    @Test
    void setComment() {
        biAdaptedCookie.setComment("comment");

        verify(originalCookie).setComment("comment");
    }

    @Test
    void getComment() {
        when(originalCookie.getComment()).thenReturn("comment");

        assertEquals("comment", biAdaptedCookie.getComment());
    }

    @Test
    void setDomain() {
        biAdaptedCookie.setDomain("domain");

        verify(originalCookie).setDomain("domain");
    }

    @Test
    void getDomain() {
        when(originalCookie.getDomain()).thenReturn("domain");

        assertEquals("domain", biAdaptedCookie.getDomain());
    }

    @Test
    void setMaxAge() {
        biAdaptedCookie.setMaxAge(1);

        verify(originalCookie).setMaxAge(1);
    }

    @Test
    void getMaxAge() {
        when(originalCookie.getMaxAge()).thenReturn(1);

        assertEquals(1, biAdaptedCookie.getMaxAge());
    }

    @Test
    void setPath() {
        biAdaptedCookie.setPath("path");

        verify(originalCookie).setPath("path");
    }

    @Test
    void getPath() {
        when(originalCookie.getPath()).thenReturn("path");

        assertEquals("path", biAdaptedCookie.getPath());
    }

    @Test
    void setSecure() {
        biAdaptedCookie.setSecure(true);

        verify(originalCookie).setSecure(true);
    }

    @Test
    void getSecure() {
        when(originalCookie.getSecure()).thenReturn(true);

        assertTrue(biAdaptedCookie.getSecure());
    }

    @Test
    void getName() {
        when(originalCookie.getName()).thenReturn("name");

        assertEquals("name", biAdaptedCookie.getName());
    }

    @Test
    void setValue() {
        biAdaptedCookie.setValue("value");

        verify(originalCookie).setValue("value");
    }

    @Test
    void getValue() {
        when(originalCookie.getValue()).thenReturn("value");

        assertEquals("value", biAdaptedCookie.getValue());
    }

    @Test
    void getVersion() {
        when(originalCookie.getVersion()).thenReturn(1);

        assertEquals(1, biAdaptedCookie.getVersion());
    }

    @Test
    void setVersion() {
        biAdaptedCookie.setVersion(1);

        verify(originalCookie).setVersion(1);
    }

    /**
     * Test that the clone method returns a new instance of the same class initialised to the same state. It is
     * imperative that the state of the clone can be changed without affecting the original object. That is, we want the
     * actual underlying delegate object to be cloned.
     */
    @Test
    void testClone() {
        var cookie = new Cookie("name", "value1");
        biAdaptedCookie = new JakartaCookieAdapter(new JavaXCookieAdapter(cookie));

        Cookie clonedBiAdaptedCookie = (Cookie) biAdaptedCookie.clone();

        assertEquals("value1", biAdaptedCookie.getValue());
        assertEquals("value1", clonedBiAdaptedCookie.getValue());

        biAdaptedCookie.setValue("value3");
        clonedBiAdaptedCookie.setValue("value4");

        assertEquals("value3", biAdaptedCookie.getValue());
        assertEquals("value4", clonedBiAdaptedCookie.getValue());
    }

    @Test
    void setHttpOnly() {
        biAdaptedCookie.setHttpOnly(true);

        verify(originalCookie).setHttpOnly(true);
    }

    @Test
    void isHttpOnly() {
        when(originalCookie.isHttpOnly()).thenReturn(true);

        assertTrue(biAdaptedCookie.isHttpOnly());
    }
}
