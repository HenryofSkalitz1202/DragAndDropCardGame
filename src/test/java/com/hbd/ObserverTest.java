package com.hbd;

import com.hbd.Kartu.FactoryKartu;
import com.hbd.Kartu.Kartu;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

public class ObserverTest {

    private Observer observer;
    private Kartu kartu1;
    private Kartu kartu2;

    @BeforeEach
    public void setUp() {
        observer = new Observer();
        kartu1 = FactoryKartu.getKartu("Ayam");
        kartu2 = FactoryKartu.getKartu("Biji Labu");
    }

    @Test
    public void testSubscribe() {
        observer.subscribe(kartu1);
        observer.subscribe(kartu2);

        ArrayList<Kartu> subscribers = observer.getObserver();
        assertEquals(2, subscribers.size());
        assertTrue(subscribers.contains(kartu1));
        assertTrue(subscribers.contains(kartu2));
    }

    @Test
    public void testUnsubscribe() {
        observer.subscribe(kartu1);
        observer.subscribe(kartu2);

        observer.unsubscribe(kartu1);

        ArrayList<Kartu> subscribers = observer.getObserver();
        assertEquals(1, subscribers.size());
        assertFalse(subscribers.contains(kartu1));
        assertTrue(subscribers.contains(kartu2));
    }

    @Test
    public void testGetObserver() {
        ArrayList<Kartu> subscribers = observer.getObserver();
        assertNotNull(subscribers);
        assertTrue(subscribers.isEmpty());
    }
}