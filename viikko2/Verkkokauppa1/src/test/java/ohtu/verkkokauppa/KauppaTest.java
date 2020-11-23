
package ohtu.verkkokauppa;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class KauppaTest {
    Pankki pankki;
    Viitegeneraattori viite;
    Varasto varasto;
    Kauppa k;
    String nimi = "pekka";
    String tilinro = "12345";
    
    @Before
    public void setUp() {
        // luodaan ensin mock-oliot
        pankki = mock(Pankki.class);

        viite = mock(Viitegeneraattori.class);
        // määritellään että viitegeneraattori palauttaa viitten 42
        when(viite.uusi()).thenReturn(42).thenReturn(43);
        
        varasto = mock(Varasto.class);
        // määritellään että tuote numero 1 on maito jonka hinta on 5 ja saldo 10
        when(varasto.saldo(1)).thenReturn(10); 
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        //huom! palauttaa aina 1, eli ei vähene
        when(varasto.saldo(2)).thenReturn(1);
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "juusto", 11));
        
        when(varasto.saldo(3)).thenReturn(0);
        when(varasto.haeTuote(3)).thenReturn(new Tuote(3, "joulukalenteri", 45));

        // sitten testattava kauppa 
        k = new Kauppa(varasto, pankki, viite);
    }
    
    @Test
    public void ostoksenPaaytyttyaPankinMetodiaTilisiirtoKutsutaan() {
        // tehdään ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        k.tilimaksu(nimi, tilinro);

        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
        //asiakkaalla, tilinumeroilla ja summalla
        verify(pankki).tilisiirto(eq(nimi), anyInt(), eq(tilinro), anyString(),eq(5));   
    }
    
    @Test
    public void kahdenOstoksenKohdallaPankinMetodiaTilisiirtoKutsutaan() {
        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.lisaaKoriin(2);
        k.tilimaksu(nimi, tilinro);
        
        verify(pankki).tilisiirto(eq(nimi), anyInt(), eq(tilinro), anyString(), eq(16));
    }
    
    @Test
    public void kahdenSamanOstoksenKohdallaPankinMetodiaTilisiirtoKutsutaan() {
        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.lisaaKoriin(1);
        k.tilimaksu(nimi, tilinro);
        
        verify(pankki).tilisiirto(eq(nimi), anyInt(), eq(tilinro), anyString(), eq(10));
    }
    
    @Test
    public void loppuneenTuotteenKohdallaPankinMetodiaTilisiirtoKutsutaan() {
        k.aloitaAsiointi();
        k.lisaaKoriin(2);
        k.lisaaKoriin(3);
        k.tilimaksu(nimi, tilinro);
        
        verify(pankki).tilisiirto(eq(nimi), anyInt(), eq(tilinro), anyString(), eq(11));
    }
    
    @Test
    public void kaupanMetodiAloitaAsiontiNollaaOstoskorin() {
        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        
        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.tilimaksu(nimi, tilinro);
        verify(pankki).tilisiirto(eq(nimi), anyInt(), eq(tilinro), anyString(),eq(5));
    }
    
    @Test
    public void kaytetaanOikeaaViitetta() {
        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.tilimaksu(nimi, tilinro);
        verify(pankki).tilisiirto(anyString(), eq(42), anyString(), anyString(),anyInt());
        
        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.tilimaksu(nimi, tilinro);
        verify(pankki).tilisiirto(anyString(), eq(43), anyString(), anyString(),anyInt());
    }
    
    @Test
    public void poistettaessaKutsutaanVarastonMetodiaPalautaVarastoon() {
        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.poistaKorista(1);
        verify(varasto).palautaVarastoon(any(Tuote.class));
    }
}
