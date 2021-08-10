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
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Test {

    public static void main(String[] args) {
        SessionFactory sessionFactory;

        try {
            sessionFactory = new Configuration().configure().buildSessionFactory();

        } catch (Throwable ex) {
            System.err.println("Error al crear la conf de hibernate: " + ex.getMessage());
            throw new ExceptionInInitializerError();
        }


        /* Crear objeto persona y persistirlo*/
        Persona nuevaPersona = new Persona();
//        PersonaEntity nuevaPersona = new PersonaEntity();
        nuevaPersona.setApellido("Garcia");
        nuevaPersona.setNombre("Matias");
        nuevaPersona.setDni("28123456");
        nuevaPersona.setEdad(41);

        Session session = sessionFactory.openSession();

        try {
            session.beginTransaction();
            session.save(nuevaPersona);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Ocurrio un error");
            session.getSessionFactory().close();
        }

        /*Obtener una entidad por su clave primaria */
        try {
            Persona personaEncontrada = session.get(Persona.class, 1);
            System.out.println("Encontrada: " + personaEncontrada.getApellido() + " " + personaEncontrada.getNombre());
        } catch (Exception e) {
            System.out.println("Ocurrio un error");
            session.getSessionFactory().close();
        }

        /*Modificar y Actualizar una entidad */
        try {
            session.beginTransaction();
            Persona personaModificar = session.get(Persona.class, 1);
            personaModificar.setNombre("Brianna");
            session.saveOrUpdate(personaModificar);
            session.getTransaction().commit();
            System.out.println("Modificada: " + personaModificar.getApellido() + " " + personaModificar.getNombre());
        } catch (Exception e) {
            System.out.println("Ocurrio un error");
            session.getSessionFactory().close();
        }

        /*Eliminar una entidad */
//        try {
//            session.beginTransaction();
//            Persona personaEliminar = session.get(Persona.class, 1);
//            session.delete(personaEliminar);
//            session.getTransaction().commit();
//            System.out.println("Se elimino la persona");
//        } catch (Exception e) {
//            System.out.println("Ocurrio un error");
//            session.getSessionFactory().close();
//        }
        
        /*Cerra la conexion a la BD*/
        session.getSessionFactory().close();
    }
}
