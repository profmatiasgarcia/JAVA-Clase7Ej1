package ZHerencia;
/**
 * @author Prof Matias Garcia.
 * <p> Copyright (C) 2021 para <a href = "https://www.profmatiasgarcia.com.ar/"> www.profmatiasgarcia.com.ar </a>
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
import java.util.Date;
import java.util.List;

import org.hibernate.Session;

public class Test {

    public static void main(String[] args) {
        insertaAlgunDato();
        consultaDatos();
    }

    private static void consultaDatos() {
        Session s = HibernateUtil.getCurrentSession();
        List<Padre> datos = s.createQuery("from Padre").list();
        for (Padre dato : datos) {
            System.out.println(dato.getId());
            System.out.println(dato.getAtributoPadre());
            System.out.println(dato.getFecha());
            System.out.println(dato.diQuienEres());
        }
        s.close();
    }

    private static void insertaAlgunDato() {
        Session s = HibernateUtil.getCurrentSession();
        try {
            Hija1 d1 = new Hija1();
            d1.setFecha(new Date());
            d1.setAtributoPadre("titulo");
            d1.setAtributoHija1(22);

            Hija2 d2 = new Hija2();
            d2.setFecha(new Date());
            d2.setAtributoPadre("otro titulo");
            d2.setAtributoHija2(true);

            s.beginTransaction();
            s.persist(d1);
            s.persist(d2);
            s.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            s.close();
        }
    }
}
