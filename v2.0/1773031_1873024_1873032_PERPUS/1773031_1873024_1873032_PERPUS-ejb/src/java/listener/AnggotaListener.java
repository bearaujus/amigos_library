/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package listener;

import java.util.Date;
import java.util.concurrent.TimeUnit;
import javax.persistence.PostLoad;
import model.Anggota;

/**
 *
 * @author 1773031_1873024_1873032
 */
public class AnggotaListener {

    @PostLoad
    public void anggotaListenerMain(Anggota a) {
        Long tt = new Date().getTime() - a.getTanggalLahir().getTime();
        if (tt < 0) {
            tt = tt * (-1);
        }
        a.setUmur((int) TimeUnit.MILLISECONDS.toDays(tt) / 365);
    }
}
