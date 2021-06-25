/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assets;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author 1773031_1873024_1873032
 */
public class Utill {

    public static String getDateFormatted(Date param) {
        return new SimpleDateFormat("dd MMMM yyyy").format(param);
    }

    public static String getYearFormatted(Date param) {
        Calendar c = Calendar.getInstance();
        c.setTime(param);
        return String.valueOf(c.get(Calendar.YEAR));
    }

    public static String getRupiahConverter(Integer param) {
        DecimalFormat kursIndonesia = (DecimalFormat) DecimalFormat.getCurrencyInstance();
        DecimalFormatSymbols formatRp = new DecimalFormatSymbols();
        formatRp.setCurrencySymbol("Rp ");
        formatRp.setMonetaryDecimalSeparator(',');
        formatRp.setGroupingSeparator('.');
        kursIndonesia.setMaximumFractionDigits(0);
        kursIndonesia.setDecimalFormatSymbols(formatRp);
        return kursIndonesia.format(param);
    }
    
    public static Integer getDiffDays(Date base, Date target) {
        long diff = base.getTime() - target.getTime();
        return (int) ((diff / (1000 * 60 * 60 * 24)) % 365);
    }

}
