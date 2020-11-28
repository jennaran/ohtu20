
package laskin;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Komento {
    TextField tuloskentta;
    TextField syotekentta;
    Button nollaa;
    Button undo;
    Sovelluslogiikka sovellus;
    String edellinen;
   
    public void suorita() {
        syotekentta.setText("");
        tuloskentta.setText("" + sovellus.tulos());
    }
    
    public void peru() {
        syotekentta.setText("");
        tuloskentta.setText("" + edellinen);
        sovellus.setTulos(Integer.parseInt(edellinen));
        edellinen = null;
        undo.disableProperty().set(true);
    }
}
