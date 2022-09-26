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
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;

public class Test {

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

        try {
            session.beginTransaction();
            session.persist(nuevaPersona);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Ocurrio un error");
            session.getSessionFactory().close();
        }

        /*Obtener una entidad por su clave primaria */
        try {
            Persona2 personaEncontrada = session.get(Persona2.class, 1);
            System.out.println("Encontrada: " + personaEncontrada);
        } catch (Exception e) {
            System.out.println("Ocurrio un error");
            session.getSessionFactory().close();
        }

        /*Buscar todas las entidades registradas en la BD*/
        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Persona2> query = builder.createQuery(Persona2.class);
            query.select(query.from(Persona2.class));
            List<Persona2> personas = session.createQuery(query).list();
            System.out.println("Cantidad de personas: " + personas.size());

        } catch (Exception e) {
            System.out.println("Ocurrio un error");
            session.getSessionFactory().close();
        }

        /*Buscar todas las entidades WHERE edad igual a 41 registradas en la BD*/
        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Persona2> query = builder.createQuery(Persona2.class);
            Root<Persona2> root = query.from(Persona2.class);
            query.select(root);
            query.where(builder.equal(root.get("apellido"), "Gar"));
            List<Persona2> personaLista = session.createQuery(query).list();
            for (Persona2 per : personaLista) {
                System.out.println(per.getApellido() + " " + per.getNombre());
            }
        } catch (Exception e) {
            System.out.println("Ocurrio un error");
            session.getSessionFactory().close();
        }
        session.getSessionFactory().close();
    }
}
