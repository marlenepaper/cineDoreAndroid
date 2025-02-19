package com.binaryBuddies.cinedore.models;

public class RegisterRequest {
    private String nombre;
    private String apellidos;
    private String correoElectronico;
    private String contrasenia;
    private String telefono;
    private String identificacion;
    private String fechaNacimiento;

    public RegisterRequest(
            String nombre,
            String apellidos,
            String correoElectronico,
            String contrasenia,
            String telefono,
            String identificacion,
            String fechaNacimiento) {

        this.nombre = nombre;
        this.apellidos = apellidos;
        this.correoElectronico = correoElectronico;
        this.contrasenia = contrasenia;
        this.telefono = telefono;
        this.identificacion = identificacion;
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getNombre() { return nombre; }
    public String getApellidos() { return apellidos; }
    public String getCorreoElectronico() { return correoElectronico; }
    public String getContrasenia() { return contrasenia; }
    public String getTelefono() { return telefono; }
    public String getIdentificacion() { return identificacion; }
    public String getFechaNacimiento() { return fechaNacimiento; }
}
