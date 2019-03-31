package question3;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

import java.util.Observable;
import java.util.Observer;

/**
 * Décrivez votre classe Controleur ici.
 * 
 * @author Jalal Hassane
 * @version 31/3/2019
 */
public class Vue extends JPanel implements Observer{

    private JLabel etatPile;
    private PileModele<Integer> pile;

    public Vue(PileModele<Integer> pile) {
        super();
        this.pile = pile;
        pile.addObserver(this);
        this.etatPile = new JLabel("entrez des nombres entiers");
        setLayout(new FlowLayout(FlowLayout.LEFT));
        add(etatPile);
        setBackground(Color.green);
        // inscription auprès du modèle comme observateur
    }

    public void update(Observable obs, Object arg) {
        etatPile.setText(pile.toString()); // ou obs.toString()
    }

}
