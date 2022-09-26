package EjPersona2;
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
import java.util.List;
import org.hibernate.Session;

public class Test3 {

    public static void main(String[] args) {
        Session session = HibernateUtil.getCurrentSession();

        /* Crear objeto persona y persistirlo*/
        Persona2 nuevaPersona = new Persona2();
        nuevaPersona.setApellido("Garcia");
        nuevaPersona.setNombre("Matias");
        nuevaPersona.setDni("28123456");
        nuevaPersona.setEdad(41);

        Fecha fecnacimiento = new Fecha(4, 8, 1980);
        nuevaPersona.setFecNac(fecnacimiento);

        Persona2 nuevaPersona2 = new Persona2();
        nuevaPersona2.setApellido("Garcia");
        nuevaPersona2.setNombre("Brianna");
        nuevaPersona2.setDni("54123456");
        nuevaPersona2.setEdad(6);

        Fecha fecnacimiento2 = new Fecha(2, 4, 2015);
        nuevaPersona2.setFecNac(fecnacimiento2);

        try {
            session.beginTransaction();
            session.persist(nuevaPersona);
            session.persist(nuevaPersona2);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Ocurrio un error");
            session.getSessionFactory().close();
        }

        /*Obtener una entidad por su clave primaria */
        try {
            int id = 1;

            Persona2 personaEncontrada = session.createQuery("FROM Persona2 where idpersona = :id", Persona2.class).setParameter("id", id).getSingleResult();

            System.out.println("Persona Encontrada: " + personaEncontrada.getApellido() + " " + personaEncontrada.getNombre());

        } catch (Exception e) {
            System.out.println("Algo salio mal al Buscar a la persona");
            session.getSessionFactory().close();
        }

        /*Buscar todas las entidades registradas en la BD*/
        try {
            List<Persona2> listado = session.createQuery("FROM Persona2", Persona2.class).getResultList();

            for (Persona2 per : listado) {
                System.out.println(per);
            }

        } catch (Exception e) {
            System.out.println("Algo salio mal al Buscar a la persona");
            session.getSessionFactory().close();
        }

        /*Buscar todas las entidades WHERE edad igual a 41 registradas en la BD*/
        try {
            int edadB = 41;

            List<Persona2> listado = session.createQuery("FROM Persona2 where edad = :edadB", Persona2.class)
                    .setParameter("edadB", edadB).getResultList();

            for (Persona2 per : listado) {
                System.out.println(per);
            }

        } catch (Exception e) {
            System.out.println("Algo salio mal al Buscar a la persona");
            session.getSessionFactory().close();
        }

        /*Buscar y mostrar los campos especificados*/
        try {
            List<Object[]> listado = session.createQuery("SELECT apellido, dni FROM Persona2", Object[].class).getResultList();

            for (Object[] valores : listado) {
                System.out.println("apellido: " + valores[0] + " DNI: " + valores[1]);
            }
        } catch (Exception e) {
            System.out.println("Ocurrio un error");
            session.getSessionFactory().close();
        }

        /*Buscar las personas mayores de edad*/
        try {
            List<Object[]> listado = session.createQuery("SELECT apellido, nombre, edad FROM Persona2 where edad >= 21", Object[].class).getResultList();

            for (Object[] valores : listado) {
                System.out.println(valores[0] + " " + valores[1] + " - " + valores[2]);
            }
        } catch (Exception e) {
            System.out.println("Ocurrio un error");
            session.getSessionFactory().close();
        }

        /*Ordenar el listado por nombre*/
        try {
            List<Persona2> listado = session.createQuery("FROM Persona2 ORDER BY nombre asc", Persona2.class).getResultList();

            for (Persona2 per : listado) {
                System.out.println(per);
            }

        } catch (Exception e) {
            System.out.println("Algo salio mal al Buscar a la persona");
            session.getSessionFactory().close();
        }

        /*Agrupar resultados por edad*/
        try {
            List<Persona2> listado = session.createQuery("FROM Persona2 GROUP BY edad", Persona2.class).getResultList();

            for (Persona2 per : listado) {
                System.out.println(per.getApellido() + " - " + per.getEdad());
            }

        } catch (Exception e) {
            System.out.println("Algo salio mal al Buscar a la persona");
            session.getSessionFactory().close();
        }

        session.close();
        session.getSessionFactory().close();
    }
}
