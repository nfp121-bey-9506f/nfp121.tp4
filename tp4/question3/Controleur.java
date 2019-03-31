package question3;

import question3.tp3.PileI;
import question3.tp3.PilePleineException;
import question3.tp3.PileVideException;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

/**
 * Décrivez votre classe Controleur ici.
 * 
 * @author Jalal Hassane
 * @version 31/3/2019
 */
public class Controleur extends JPanel implements ActionListener{

    private JButton push, add, sub, mul, div, clear;
    private PileModele<Integer> pile;
    private JTextField donnee;

    public Controleur(PileModele<Integer> pile) {
        super();
        this.pile = pile;
        this.donnee = new JTextField(8);
        this.push = new JButton("push");
        this.add = new JButton("+");add.setEnabled(false);
        this.sub = new JButton("-");sub.setEnabled(false);
        this.mul = new JButton("*");mul.setEnabled(false);
        this.div = new JButton("/");div.setEnabled(false);
        this.clear = new JButton("[]");clear.setEnabled(false);

        setLayout(new GridLayout(2, 1));
        add(donnee);
        donnee.addActionListener(this);
        JPanel boutons = new JPanel();
        boutons.setLayout(new FlowLayout());
        boutons.add(push);  push.addActionListener(this);push.setActionCommand("empiler");
        boutons.add(add);   add.addActionListener(this);add.setActionCommand("ajouter");
        boutons.add(sub);   sub.addActionListener(this);sub.setActionCommand("soustraire");
        boutons.add(mul);   mul.addActionListener(this);mul.setActionCommand("multiplier");
        boutons.add(div);   div.addActionListener(this);div.setActionCommand("diviser");
        boutons.add(clear); clear.addActionListener(this);clear.setActionCommand("clear");
        add(boutons);
        boutons.setBackground(Color.red);
        //actualiserInterface();
    }
    
    public void actionPerformed(ActionEvent ae){
        String action = ae.getActionCommand();
        
        switch(action){
            case "empiler":int operande;
                           try{
                                operande = operande();
                           }
                            catch(NumberFormatException e){
                                
                                break;
                           }
                           try{
                               pile.empiler(operande);
                           }catch(PilePleineException e){
                                System.out.println("La pile est pleine!!");
                           }
                           clear.setEnabled(true);
                           if(pile.estPleine()) push.setEnabled(false);
                           if(pile.taille()>1){add.setEnabled(true);sub.setEnabled(true);mul.setEnabled(true);div.setEnabled(true);}
                           break;
            case "ajouter":
            try{int resultat = pile.depiler()+pile.depiler();
                pile.empiler(resultat);
            }catch(PileVideException e){}
            catch(PilePleineException e){}
            if(pile.taille()==1) {add.setEnabled(false);sub.setEnabled(false);mul.setEnabled(false);div.setEnabled(false);}
            break;
            case "soustraire":
            try{int resultat = pile.depiler()-pile.depiler();
                pile.empiler(resultat);
            }catch(PileVideException e){}
            catch(PilePleineException e){}
            if(pile.taille()==1) {add.setEnabled(false);sub.setEnabled(false);mul.setEnabled(false);div.setEnabled(false);}
            break;
            case "multiplier":
            try{int resultat = pile.depiler()*pile.depiler();
                pile.empiler(resultat);
            }catch(PileVideException e){}
            catch(PilePleineException e){}
            if(pile.taille()==1) {add.setEnabled(false);sub.setEnabled(false);mul.setEnabled(false);div.setEnabled(false);}
            break;
            case "diviser":
            try{
                int op1 = pile.depiler();int op2 = pile.depiler();
                if(op2==0)throw new DivisionParZeroException();
                else {int resultat = op1/op2;pile.empiler(resultat);}
            }catch(PileVideException e){}
            catch(DivisionParZeroException d){
                break;
            }
            catch(PilePleineException e){}
            if(pile.taille()==1) {add.setEnabled(false);sub.setEnabled(false);mul.setEnabled(false);div.setEnabled(false);}
            break;
            case "clear":
            try{
                pile.viderPile();
                add.setEnabled(false);
                sub.setEnabled(false);
                mul.setEnabled(false);
                div.setEnabled(false);
                clear.setEnabled(false);
                push.setEnabled(true);
            }catch(PileVideException e){System.out.println("Exception");}
            break;
            default:break;
        }
    }
    
    public void actualiserInterface() {
        // à compléter
    }

    private Integer operande() throws NumberFormatException {
        return Integer.parseInt(donnee.getText());
    }

    
    // en cas d'exception comme division par zéro, 
    // mauvais format de nombre suite à l'appel de la méthode operande
    // la pile reste en l'état (intacte)
    class DivisionParZeroException extends Exception{}
}
