
package laskin;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Erotus extends Komento {
    
    public Erotus(TextField tuloskentta, TextField syotekentta, Button nollaa, Button undo, Sovelluslogiikka sovellus) {
        this.tuloskentta = tuloskentta;
        this.syotekentta = syotekentta;
        this.nollaa = nollaa;
        this.undo = undo;
        this.sovellus = sovellus;
    }
   
    @Override
    public void suorita() {
        edellinen = tuloskentta.getText();
        undo.disableProperty().set(false);
        nollaa.disableProperty().set(false);
        
        sovellus.miinus(Integer.parseInt(syotekentta.getText()));
        syotekentta.setText("");
        
        int laskunTulos = sovellus.tulos();
        if ( laskunTulos==0) {
            nollaa.disableProperty().set(true);
        }
        tuloskentta.setText("" + laskunTulos);
    } 
}
