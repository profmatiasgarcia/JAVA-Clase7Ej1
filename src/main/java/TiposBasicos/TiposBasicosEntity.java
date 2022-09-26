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
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
//import org.hibernate.annotations.Type;

@Entity
@Table(name = "ejtipos")
public class TiposBasicosEntity implements Serializable {

    @Id
 //   @Type(type = "integer")
    private int inte;

 //   @Type(type = "long")
    private long long1;
 //   @Type(type = "short")
    private short short1;
 //   @Type(type = "float")
    private float float1;
 //   @Type(type = "double")
    private double double1;
 //   @Type(type = "character")
    private char character1;
 //   @Type(type = "byte")
    private byte byte1;
 //   @Type(type = "boolean")
    private boolean boolean1;
 //   @Type(type = "yes_no")
    private boolean yesno1;
 //   @Type(type = "true_false")
    private boolean truefalse1;
 //   @Type(type = "string")
    private String stri;
    @Temporal(TemporalType.DATE)
    private Date dateDate;
    @Temporal(TemporalType.TIME)
    private Date dateTime;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateTimestamp;
    @Lob
    private String texto;
 //   @Type(type = "binary")
    private byte[] binario;
 //   @Type(type = "big_decimal")
    private BigDecimal bigDecimal;
 //   @Type(type = "big_integer")
    private BigInteger bigInteger;

    public TiposBasicosEntity() {
    }

    public int getInte() {
        return inte;
    }

    public void setInte(int inte) {
        this.inte = inte;
    }

    public long getLong1() {
        return long1;
    }

    public void setLong1(long long1) {
        this.long1 = long1;
    }

    public short getShort1() {
        return short1;
    }

    public void setShort1(short short1) {
        this.short1 = short1;
    }

    public float getFloat1() {
        return float1;
    }

    public void setFloat1(float float1) {
        this.float1 = float1;
    }

    public double getDouble1() {
        return double1;
    }

    public void setDouble1(double double1) {
        this.double1 = double1;
    }

    public char getCharacter1() {
        return character1;
    }

    public void setCharacter1(char character1) {
        this.character1 = character1;
    }

    public byte getByte1() {
        return byte1;
    }

    public void setByte1(byte byte1) {
        this.byte1 = byte1;
    }

    public boolean isBoolean1() {
        return boolean1;
    }

    public void setBoolean1(boolean boolean1) {
        this.boolean1 = boolean1;
    }

    public boolean isYesno1() {
        return yesno1;
    }

    public void setYesno1(boolean yesno1) {
        this.yesno1 = yesno1;
    }

    public boolean isTruefalse1() {
        return truefalse1;
    }

    public void setTruefalse1(boolean truefalse1) {
        this.truefalse1 = truefalse1;
    }

    public String getStri() {
        return stri;
    }

    public void setStri(String stri) {
        this.stri = stri;
    }

    public Date getDateDate() {
        return dateDate;
    }

    public void setDateDate(Date dateDate) {
        this.dateDate = dateDate;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public Date getDateTimestamp() {
        return dateTimestamp;
    }

    public void setDateTimestamp(Date dateTimestamp) {
        this.dateTimestamp = dateTimestamp;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public byte[] getBinario() {
        return binario;
    }

    public void setBinario(byte[] binario) {
        this.binario = binario;
    }

    public BigDecimal getBigDecimal() {
        return bigDecimal;
    }

    public void setBigDecimal(BigDecimal bigDecimal) {
        this.bigDecimal = bigDecimal;
    }

    public BigInteger getBigInteger() {
        return bigInteger;
    }

    public void setBigInteger(BigInteger bigInteger) {
        this.bigInteger = bigInteger;
    }

}
