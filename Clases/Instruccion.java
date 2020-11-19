package Clases;

public class Instruccion{
    public Instruccion(String nom, int cic){
        nombre = nom;
        ciclos = cic; //wanna be quantums
    }

    @Override
    public int hashCode(){
        int p = 26;
        int ret = 0;
        for(int i = 0; i < nombre.length(); i++){
            ret += Math.pow(p,i) * nombre.charAt(i);
        }
        return ret;
    }

    @Override
    public boolean equals(Object in){
        Instruccion aux = (Instruccion)in;
        return aux.nombre == this.nombre;
    }

    public String nombre; // fork(), stackalloc(), 
    public int ciclos;
}