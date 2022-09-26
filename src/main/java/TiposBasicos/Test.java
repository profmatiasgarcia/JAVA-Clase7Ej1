package TiposBasicos;
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
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import org.hibernate.Session;

public class Test {

    public static void main(String[] args) {

        Date date = new Date();
        byte array[] = {(byte) 0x45, (byte) 0xF5, (byte) 0x3A, (byte) 0x67, (byte) 0xFF};

        TiposBasicos tiposBasicos1 = new TiposBasicos();
        tiposBasicos1.setInte(1);
        tiposBasicos1.setLong1(12);
        tiposBasicos1.setShort1((short) 13);
        tiposBasicos1.setFloat1(14.1F);
        tiposBasicos1.setDouble1(15.2);
        tiposBasicos1.setCharacter1('W');
        tiposBasicos1.setByte1((byte) 16);
        tiposBasicos1.setBoolean1(true);
        tiposBasicos1.setYesno1(true);
        tiposBasicos1.setTruefalse1(true);
        tiposBasicos1.setStri("Hola mundo");
        tiposBasicos1.setDateDate(date);
        tiposBasicos1.setDateTime(date);
        tiposBasicos1.setDateTimestamp(date);
        tiposBasicos1.setTexto("texto muyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy largo");
        tiposBasicos1.setBinario(array);
        tiposBasicos1.setBigDecimal(new BigDecimal("0.3"));
        tiposBasicos1.setBigInteger(new BigInteger("5345345324532"));

        TiposBasicos tiposBasicos2 = new TiposBasicos();
        tiposBasicos2.setInte(2);
        tiposBasicos2.setLong1(12000);
        tiposBasicos2.setShort1((short) 31);
        tiposBasicos2.setFloat1(44.5F);
        tiposBasicos2.setDouble1(95.25);
        tiposBasicos2.setCharacter1('M');
        tiposBasicos2.setByte1((byte) 29);
        tiposBasicos2.setBoolean1(false);   //<<--- Cambiado a false
        tiposBasicos2.setYesno1(false);     //<<--- Cambiado a false
        tiposBasicos2.setTruefalse1(false); //<<--- Cambiado a false
        tiposBasicos2.setStri("Prof Matias");
        tiposBasicos2.setDateDate(date);
        tiposBasicos2.setDateTime(date);
        tiposBasicos2.setDateTimestamp(date);
        tiposBasicos2.setTexto("texto muyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy largo");
        tiposBasicos2.setBinario(array);
        tiposBasicos2.setBigDecimal(new BigDecimal("7.6"));
        tiposBasicos2.setBigInteger(new BigInteger("9875345324789"));

        Session session = HibernateUtil.getCurrentSession();
        
        session.beginTransaction();

        session.persist(tiposBasicos1);
        session.persist(tiposBasicos2);

        session.getTransaction().commit();
        //session.close();
        HibernateUtil.closeSession();
    }
}
