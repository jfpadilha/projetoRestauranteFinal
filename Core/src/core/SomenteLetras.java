package core;

import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 * Faz com que um campo texField apenas receba apenas letras com ou sem acento
 * e torna o texto todo em MAIÚSCULO
 * 
 * @author João Francisco Padilha Souza
 * @version Agosto/2012 - Centro Universitário Univates - Lajeado/RS
 * 
 */
public class SomenteLetras extends PlainDocument

{
    @Override
    public void insertString(int offset, String str, javax.swing.text.AttributeSet attr)
            throws BadLocationException
    {
        super.insertString(offset, str.toUpperCase().replaceAll("[^a-z|^A-Z|^ |^Ã|^Á|^Â|^É|^Í|^Ó|^Ú]",""), attr);
    }
    
        public void replace(int offset, String str, javax.swing.text.AttributeSet attr)
            throws BadLocationException
    {
        super.insertString(offset, str.toUpperCase().replaceAll("[^a-z|^A-Z|^ |^Ã|^Á|^Â|^É|^Í|^Ó|^Ú]",""), attr);
    }
}
