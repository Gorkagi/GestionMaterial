package dominio;

public class Recurso
{
  protected int id;
  protected String nombre;
  protected String descripcion;
  protected String ubicacion;
  protected int idResponsable;
  protected String tipo;
  
  public Recurso(int id, String nombre, String descripcion,String ubicacion, int idResponsable, String tipo)
  {
    this.ubicacion=ubicacion;
    this.nombre=nombre;
    this.descripcion=descripcion;
    this.id=id;
    this.idResponsable = idResponsable;
    this.tipo = tipo;
  }
  public Recurso (Recurso r){
	  this.id = r.id;
	  this.nombre = r.nombre;
	  this.descripcion = r.descripcion;
	  this.ubicacion = r.ubicacion;
	  this.idResponsable = r.idResponsable;
	  this.tipo = r.tipo;
  }
  public String getNombre(){return nombre;}
  public String getDescripcion(){return descripcion;}
  public int getId(){return id;}
  public String getUbicacion(){return ubicacion;}
  public String getTipo() {return tipo;}
  public int getIdResponsable(){return idResponsable;}
}	