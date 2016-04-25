package dominio;

public class Recurso
{
  protected int id;
  protected String nombre;
  protected String descripcion;
  protected String ubicacion;
  protected int idResponsable;
  
  public Recurso(int id, String nombre, String descripcion,String ubicacion, int idResponsable)
  {
    this.ubicacion=ubicacion;
    this.nombre=nombre;
    this.descripcion=descripcion;
    this.id=id;
    this.idResponsable = idResponsable;
  }
  public Recurso (Recurso r){
	  this.id = r.id;
	  this.nombre = r.nombre;
	  this.descripcion = r.descripcion;
	  this.ubicacion = r.ubicacion;
	  this.idResponsable = r.idResponsable;
  }
  public String getNombre(){return nombre;}
  public String getDescripcion(){return descripcion;}
  public int getId(){return id;}
  public String getUbicacion(){return ubicacion;}
  public int getIdResponsable(){return idResponsable;}
}