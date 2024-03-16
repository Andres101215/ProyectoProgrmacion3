package Logic;

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

    private DisciplinaDeportiva disciplinaDeportiva;

    public Afiliado() {
    }

    public Afiliado(String id, String nombre, String apellido, String documento, int edad, String genero, String direccion, String telefono, String correo, DisciplinaDeportiva disciplinaDeportiva) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.documento = documento;
        this.edad = edad;
        this.genero = genero;
        this.direccion = direccion;
        this.telefono = telefono;
        this.correo = correo;
        this.disciplinaDeportiva = disciplinaDeportiva;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public DisciplinaDeportiva getDisciplinaDeportiva() {
        return disciplinaDeportiva;
    }

    public void setDisciplinaDeportiva(DisciplinaDeportiva disciplinaDeportiva) {
        this.disciplinaDeportiva = disciplinaDeportiva;
    }

    @Override
    public String toString() {
        return "Afiliado{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", documento='" + documento + '\'' +
                ", edad=" + edad +
                ", genero='" + genero + '\'' +
                ", direccion='" + direccion + '\'' +
                ", telefono='" + telefono + '\'' +
                ", correo='" + correo + '\'' +
                ", disciplinaDeportiva=" + disciplinaDeportiva +
                '}';
    }
}

