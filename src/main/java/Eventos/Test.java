package Eventos;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import org.hibernate.Session;

public class Test {

    public static void main(String[] args) {

        Scanner lectura = new Scanner(System.in);
        Scanner lecturaS = new Scanner(System.in);

        crearEvento("FLISoL", new Date(121, 3, 25));
        crearEvento("Nerdear.la", new Date(121, 9, 15));
        crearEvento("Eko Party", new Date(121, 8, 7));

        System.out.println("----------Listar Eventos----------");
        listarEventos();

        System.out.println("----------Buscar Evento----------");
        buscarEvento(2);

        System.out.println("----------Modificar Evento----------");
        System.out.println("Indique id de Evento a modificar");
        int idModificar = lectura.nextInt();
        System.out.println("Ingrese nuevo titulo de evento a modificar");
        String tituloModificar = lecturaS.nextLine();
        System.out.println("Ingrese nueva fecha: dd/mm/aaaa");
        String fecha = lecturaS.nextLine();
        try {
            Date fechaModificar = new SimpleDateFormat("dd/MM/yyyy").parse(fecha);
            actualizarEvento(idModificar, tituloModificar, fechaModificar);
        } catch (ParseException pe) {
            System.out.println("error de fecha");
        }

        System.out.println("----------Listar Eventos----------");
        listarEventos();

        System.out.println("----------Eliminar Evento----------");
        System.out.println("Indique id de Evento a Eliminar");
        int idEliminar = lectura.nextInt();

        eliminarEvento(idEliminar);

        System.out.println("----------Listar Eventos----------");
        listarEventos();

        HibernateUtil.getSessionFactory().close();
    }

    private static void crearEvento(String titulo, Date fecha) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();

            Evento nuevoEvento = new Evento();
            nuevoEvento.setTitulo(titulo);
            nuevoEvento.setFecha(fecha);

            session.save(nuevoEvento);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Algo salio mal al crear nuevo Evento");
        } finally {
            session.close();
        }
    }

    private static void listarEventos() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();

            List<Evento> listado = session.createQuery("FROM Evento", Evento.class).getResultList();
            // List<Evento> listado = (List<Evento>) session.createQuery("from Evento").list();

            session.getTransaction().commit();

            for (Evento ev : listado) {
                System.out.println(ev);
            }
        } catch (Exception e) {
            System.out.println("Algo salio mal al listar los eventos");
        } finally {
            session.close();
        }
    }

    private static Evento buscarEvento(int id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
           session.beginTransaction();

            Evento eventoEncontrado = session.createQuery("FROM Evento where idevento = :id", Evento.class).setParameter("id", id).getSingleResult();

           session.getTransaction().commit();

            System.out.println("Evento Encontrado: " + eventoEncontrado);

            return eventoEncontrado;
        } catch (Exception e) {
            System.out.println("Algo salio mal al Buscar el evento");
        } finally {
            session.close();
        }
        return null;
    }

    private static void actualizarEvento(int id, String titulo, Date fecha) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {

            session.beginTransaction();
            Evento eventoActualizar = buscarEvento(id);
            eventoActualizar.setTitulo(titulo);
            eventoActualizar.setFecha(fecha);
            session.update(eventoActualizar);

            session.getTransaction().commit();

            System.out.println("Evento modificado: " + eventoActualizar);
        } catch (Exception e) {
            System.out.println("Algo salio mal al Modificar el evento");
        } finally {
            session.close();
        }
    }

    public static void eliminarEvento(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            Evento eventoEliminar = buscarEvento(id);
            session.delete(eventoEliminar);

            session.getTransaction().commit();

            System.out.println("El evento se ha eliminado correctamente");
        } catch (Exception e) {
            System.out.println("Algo salio mal al Eliminar el evento");
        } finally {
            session.close();
        }

    }

}
