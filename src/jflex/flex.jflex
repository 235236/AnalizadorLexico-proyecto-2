import java.util.ArrayList;
import java.util.List;
import java.io.FileReader;
%%
%public
%class Lexer
%standalone

%{
	private List<String> tokens = new ArrayList<>();
%}

%{
	public List<String> analizar(String archivo) {
		FileReader in = null;
		try{
                    in = new FileReader(archivo);
                    Lexer lexer = new Lexer(in);
                    while(!lexer.zzAtEOF) {
                        lexer.yylex();
                    }
                }catch (Exception ex) {
                    System.out.println("Error al analizar el archivo.");
                }finally{
                    try {
                        in.close();
                    }catch(Exception ex) {
                        System.out.println("Error al cerrar el archivo.");
                    }
                }
            return tokens;
        }
%}

%%

[a-z_A-Aa-z][a-zA-Z]+   {tokens.add("atomo"); System.out.println("ATOMO");}
[A-Za-z]+  {tokens.add("var"); System.out.println("VARIABLE");}
[+.0-9][-.0-9][.0-9]    {tokens.add("pto_fijo"); System.out.println("PUNTO FIJO");}
":-"  {tokens.add("imp"); System.out.println("IMPLICA");}
[0-9]  {tokens.add("pto_flot"); System.out.println("PUNTO FLOTANTE");}
[0-9] {tokens.add("cade"); System.out.println("CADENA");}
[0-9]  {tokens.add("ent"); System.out.println("ENTERO");}
[0-9]  {tokens.add("list"); System.out.println("LISTA");}
[0-9]  {tokens.add("pred"); System.out.println("PREDICADO");}