package EjPersona;
/**
 * @author Prof Matias Garcia.
 * <p> Copyright (C) 2017 para <a href = "https://www.profmatiasgarcia.com.ar/"> www.profmatiasgarcia.com.ar </a>
 * - con licencia GNU GPL3.
 * <p> Este programa es software libre. Puede redistribuirlo y/o modificarlo bajo los términos de la
 * Licencia Pública General de GNU según es publicada por la Free Software Foundation, 
 * bien con la versión 3 de dicha Licencia o bien (según su elección) con cualquier versión posterior. 
 * Este programa se distribuye con la esperanza de que sea útil, pero SIN NINGUNA GARANTÍA, 
 * incluso sin la garantía MERCANTIL implícita o sin garantizar la CONVENIENCIA PARA UN PROPÓSITO
 * PARTICULAR. Véase la Licencia Pública General de GNU para más detalles.
 * Debería haber recibido una copia de la Licencia Pública General junto con este programa. 
 * Si no ha sido así ingrese a <a href = "http://www.gnu.org/licenses/"> GNU org </a>
 */
import org.hibernate.HibernateException;
import org.hibernate.Session;

public class Test {

    public static void main(String[] args) {
        Session session = HibernateUtil.getCurrentSession();

        /* Crear objeto persona y persistirlo*/
        //Persona nuevaPersona = new Persona();
        PersonaEntity nuevaPersona = new PersonaEntity();
        nuevaPersona.setApellido("Garcia");
        nuevaPersona.setNombre("Matias");
        nuevaPersona.setDni("28561236");
        nuevaPersona.setEdad(41);

        try {
            session.beginTransaction();
            session.persist(nuevaPersona);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            System.out.println("Ocurrio un error");
            session.close();
        }

        /*Obtener una entidad por su clave primaria */
        try {
            //Persona personaEncontrada = session.get(Persona.class, 1);
            PersonaEntity personaEncontrada = session.get(PersonaEntity.class, 1);
            System.out.println("Encontrada: " + personaEncontrada.getApellido() + " " + personaEncontrada.getNombre());
        } catch (HibernateException e) {
            System.out.println("Ocurrio un error");
            session.getSessionFactory().close();
        }

        /*Modificar y Actualizar una entidad */
        try {
            session.beginTransaction();
            //Persona personaModificar = session.get(Persona.class, 1);
            PersonaEntity personaModificar = session.get(PersonaEntity.class, 1);
            personaModificar.setNombre("Brianna");
            session.merge(personaModificar);
            session.getTransaction().commit();
            System.out.println("Modificada: " + personaModificar.getApellido() + " " + personaModificar.getNombre());
        } catch (HibernateException e) {
            System.out.println("Ocurrio un error");
            session.close();
        }

        /*Eliminar una entidad */
        try {
            session.beginTransaction();
            //Persona personaEliminar = session.get(Persona.class, 1);
            PersonaEntity personaEliminar = session.get(PersonaEntity.class, 1);
            session.remove(personaEliminar);
            session.getTransaction().commit();
            System.out.println("Se elimino la persona");
        } catch (HibernateException e) {
            System.out.println("Ocurrio un error");
            session.close();
        }

        /*Cerra la conexion a la BD*/
        session.close();
    }
}
