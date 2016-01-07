package core;

import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 * Faz com que um campo texField apenas receba caracteres numéricos 
 * 
 * @author João Francisco Padilha Souza
 * @version Agosto/2012 - Centro Universitário Univates - Lajeado/RS
 * 
 */
public class SomenteNumeros extends PlainDocument
{

    @Override
    public void insertString(int offset, String str, javax.swing.text.AttributeSet attr)
            throws BadLocationException
    {
        super.insertString(offset, str.toUpperCase().replaceAll("[^0-9]", ""), attr);
    }

    public void replace(int offset, String str, javax.swing.text.AttributeSet attr)
            throws BadLocationException
    {
        super.insertString(offset, str.toUpperCase().replaceAll("[^1-9]", ""), attr);
    }
}
