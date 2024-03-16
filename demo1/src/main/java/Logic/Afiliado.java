package Logic;

import org.bson.types.ObjectId;

public class Afiliado {
    private String id;
    private String nombre;
    private String apellido;

    private String documento;
    private int edad;
    private String genero;

    private String direccion;

    private String telefono;

    private String correo;

    private ObjectId Disciplina;

    public Afiliado() {
    }

    public Afiliado(String id, String nombre, String apellido, String documento, int edad, String genero, String direccion, String telefono, String correo, ObjectId disciplinaDeportiva) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.documento = documento;
        this.edad = edad;
        this.genero = genero;
        this.direccion = direccion;
        this.telefono = telefono;
        this.correo = correo;
        this.Disciplina = disciplinaDeportiva;
    }

}

