package mx.uach.fing.proyecto2;

import java.io.IOException;

/**
 * Clase principal del analizador sintactico predictivo.
 *
 * <ul>
 * <li>sent → expr + term ;</li>
 * <li>expr → expr - term | term</li>
 * <li>term → 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9</li>
 * </ul>
 *
 * @author David Alberto Torres Real
 * @version 1.0
 * @since 27/02/2015
 */
public class AnalizadorSintactico {

    private static final int FIN_SENT = ';';
    private static final int SUMA = '+';
    private static final int RESTA = '-';

    private int token;
    private String notacion;

    /**
     * Constructor principal de la clase.
     */
    public AnalizadorSintactico() {
        this.token = -1;
        this.notacion = "";
    }

    public void iniciarAnalisis() throws IOException {
        sent();
    }

    public static void main(String[] args) {
        try {
            AnalizadorSintactico analizador = new AnalizadorSintactico();
            analizador.iniciarAnalisis();
        } catch (IOException ex) {
            System.out.println("Ocurrio una anomalia: " + ex.getMessage());
        }
    }

/**
 * Hace referencia a una producción gramatical, verifica que se compongan los
 * elementos de las demas producciones mas el final de sentencia.
 * 
 * @throws IOException cuando no existe una entrada.
 */
private void sent() throws IOException {
        //TODO código que se genera
        this.siguiente(this.token);
        this.expr();
        if (this.token == FIN_SENT) {
            System.out.println(this.notacion);
        } else {
            throw new Error(String.format(
                    "Error de Sintaxis, se esperaba (%s)", (char) FIN_SENT));
        }
    }

    /**
     * Verifica que la secuencia de esta producción sea valida (+ ó -).
     *
     * @throws IOException cuando no existe una entrada valida.
     */
    private void expr() throws IOException {
        //TODO código que se genera
        this.term();
        while (true) {
            if (this.token == SUMA) {
                this.siguiente(SUMA);
                term();
                this.notacion = String.format("%s%s", this.notacion, (char) SUMA);
            } else if (this.token == RESTA) {
                this.siguiente(RESTA);
                term();
                this.notacion = String.format("%s%s", this.notacion, (char) RESTA);
            } else {
                return;
            }
        }

    }

    /**
     * Verifica que todas las terminales sean digitos.
     *
     * @throws IOException cuando no existe una entrada valida.
     */
    private void term() throws IOException {
        //TODO código que se genera
        if (Character.isDigit((char) this.token)) {
            //TODO implementar Happy path
            this.notacion = String.format("%s%s", this.notacion, (char) this.token);
            this.siguiente(this.token);
        } else {
            throw new Error(String.format(
                    "Error de Sintaxis, token no valido: %s", (char) token));
        }

    }

    /**
     * Verifica que la secuencia de tokens sea valida y no sea corrupta.
     *
     * @param token caracter numerico.
     *
     * @throws IOException cuando no existe una entrada valida.
     */
    private void siguiente(int token) throws IOException {
        //código de llamada a siguiente token
        if (this.token == token) {
            this.token = System.in.read();
        } else {
            throw new Error(String.format(
                    "Error de sintaxis, token NO valido: %s", (char) this.token));
        }
    }

}