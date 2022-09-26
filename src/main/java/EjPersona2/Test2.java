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
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;

public class Test2 {

    public static void main(String[] args) {
        Session session = HibernateUtil.getCurrentSession();

        /* Crear objeto persona y persistirlo*/
        Persona2Entity nuevaPersona = new Persona2Entity();
        nuevaPersona.setApellido("Ramirez");
        nuevaPersona.setNombre("Laura");
        nuevaPersona.setDni("42158456");
        nuevaPersona.setEdad(6);

        FechaEntity fecnacimiento = new FechaEntity(2, 4, 2015);
        nuevaPersona.setFecNac(fecnacimiento);

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
            Persona2Entity personaEncontrada = session.get(Persona2Entity.class, 1);
            System.out.println("Encontrada: " + personaEncontrada);
        } catch (Exception e) {
            System.out.println("Ocurrio un error");
            session.getSessionFactory().close();
        }

        /*Buscar el mayor del campo edad*/
        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Integer> query = builder.createQuery(Integer.class);
            Root<Persona2Entity> personaRoot = query.from(Persona2Entity.class);
            query.select(builder.max(personaRoot.get("edad")));
            Integer maxEdad = session.createQuery(query).getSingleResult();
            System.out.println("La edad maxima es: " + maxEdad);
        } catch (Exception e) {
            System.out.println("Ocurrio un error");
            session.getSessionFactory().close();
        }

        /*Buscar y mostrar los campos especificados*/
        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Object[]> query = builder.createQuery(Object[].class);
            Root<Persona2Entity> personaRoot = query.from(Persona2Entity.class);
            Path<Integer> idPath = personaRoot.get("idpersona");
            Path<String> dniPath = personaRoot.get("dni");
            //query.select(builder.array(idPath, dniPath));
            query.multiselect(idPath, dniPath);
            query.where(builder.equal(personaRoot.get("nombre"), "Brianna"));
            List<Object[]> listado = session.createQuery(query).getResultList();
            for (Object[] valores : listado) {
                System.out.println("Id: " + valores[0] + " DNI: " + valores[1]);
            }
        } catch (Exception e) {
            System.out.println("Ocurrio un error");
            session.getSessionFactory().close();
        }

        /*Buscar utilizando varios filtros*/
        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Persona2Entity> query = builder.createQuery(Persona2Entity.class);
            Root<Persona2Entity> personaRoot = query.from(Persona2Entity.class);
          //  Predicate pnombre = builder.equal(personaRoot.get("nombre"), "Brianna");
            Predicate pedad = builder.lt(personaRoot.get("edad"), 20);
            //Predicate pfinal = builder.and(pnombre, pedad);
            Predicate pfinal = builder.and(pedad);
            query.where(pfinal);
            List<Persona2Entity> listado = session.createQuery(query).getResultList();
            for (Persona2Entity p : listado) {
                System.out.println(p);
            }
        } catch (Exception e) {
            System.out.println("Ocurrio un error");
            session.getSessionFactory().close();
        }
        
        /*Agrupar resultados por edad*/
        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Persona2Entity> query = builder.createQuery(Persona2Entity.class);
            Root<Persona2Entity> personaRoot = query.from(Persona2Entity.class);
            query.select(personaRoot);
            query.groupBy(personaRoot.get("edad"));
		List<Persona2Entity> personaLista = session.createQuery(query).list();
            for (Persona2Entity per : personaLista) {
                System.out.println(per.getApellido() + per.getEdad());
            }
        } catch (Exception e) {
            System.out.println("Ocurrio un error");
            session.getSessionFactory().close();
        }

        session.close();
    }
}
