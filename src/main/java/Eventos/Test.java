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
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;
import org.hibernate.HibernateException;
import org.hibernate.Session;

public class Test {

    public static void main(String[] args) {
        Scanner lectura = new Scanner(System.in);
        Scanner lecturaS = new Scanner(System.in);

        crearEvento("FLISoL", LocalDate.of(2022, Month.APRIL, 29));
        crearEvento("Nerdear.la", LocalDate.of(2022, Month.OCTOBER, 19));
        crearEvento("Eko Party", LocalDate.of(2022, Month.DECEMBER, 16));

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
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate fechaModificar = LocalDate.parse(fecha, formatter);

            actualizarEvento(idModificar, tituloModificar, fechaModificar);
        } catch (DateTimeParseException pe) {
            System.out.println("error de fecha" + pe);
        }

        System.out.println("----------Listar Eventos----------");
        listarEventos();

        System.out.println("----------Eliminar Evento----------");
        System.out.println("Indique id de Evento a Eliminar");
        int idEliminar = lectura.nextInt();

        eliminarEvento(idEliminar);

        System.out.println("----------Listar Eventos----------");
        listarEventos();

        HibernateUtil.closeSession();
    }

    private static void crearEvento(String titulo, LocalDate fecha) {
        try ( Session session = HibernateUtil.getCurrentSession()) {
            session.beginTransaction();

            Evento nuevoEvento = new Evento();
            nuevoEvento.setTitulo(titulo);
            nuevoEvento.setFecha(fecha);

            session.persist(nuevoEvento);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            System.out.println("Algo salio mal al crear nuevo Evento");
        }
    }

    private static void listarEventos() {
        try ( Session session = HibernateUtil.getCurrentSession()) {

            List<Evento> listado = session.createQuery("FROM Evento", Evento.class).getResultList();
            //List<Evento> listado = (List<Evento>) session.createQuery("from Evento").list();

            for (Evento ev : listado) {
                System.out.println(ev);
            }
        } catch (HibernateException e) {
            System.out.println("Algo salio mal al listar los eventos");
        }
    }

    private static Evento buscarEvento(int id) {
        try ( Session session = HibernateUtil.getCurrentSession()) {

            Evento eventoEncontrado = session.createQuery("FROM Evento WHERE id = :id", Evento.class).setParameter("id", id).getSingleResult();

            System.out.println("Evento Encontrado: " + eventoEncontrado);

            return eventoEncontrado;
        } catch (HibernateException e) {
            System.out.println("Algo salio mal al Buscar el evento" + e);
        }
        return null;
    }

    private static void actualizarEvento(int id, String titulo, LocalDate fecha) {
        Evento eventoActualizar = buscarEvento(id);
        try ( Session session = HibernateUtil.getCurrentSession()) {
            session.beginTransaction();

            eventoActualizar.setTitulo(titulo);
            eventoActualizar.setFecha(fecha);
            session.merge(eventoActualizar);

            session.getTransaction().commit();

            System.out.println("Evento modificado: " + eventoActualizar);
        } catch (HibernateException e) {
            System.out.println("Algo salio mal al Modificar el evento");
        }
    }

    public static void eliminarEvento(int id) {
        Evento eventoEliminar = buscarEvento(id);
        try ( Session session = HibernateUtil.getCurrentSession()) {
            session.beginTransaction();

            session.remove(eventoEliminar);

            session.getTransaction().commit();

            System.out.println("El evento se ha eliminado correctamente");
        } catch (HibernateException e) {
            System.out.println("Algo salio mal al Eliminar el evento");
        }
    }
}
