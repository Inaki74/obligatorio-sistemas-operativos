package Clases;
import java.util.*;

public class Sistema{
    Sistema(){
        System.out.println("Esto es un sistema");
        _current = this;
    }

    private static Sistema _current;
    public static Sistema Current(){
        if(_current == null){
            System.out.println("Sistema es vacio.");
        }

        return _current;
    }
}