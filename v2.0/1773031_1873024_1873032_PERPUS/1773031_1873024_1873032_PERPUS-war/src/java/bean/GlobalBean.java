/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import assets.Perpus;
import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import model.AdminPerpus;
import model.Anggota;
import model.Buku;
import model.Denda;
import model.Kategori;
import model.Peminjaman;
import model.PeminjamanDetails;
import org.apache.commons.validator.UrlValidator;
import org.primefaces.PrimeFaces;
import org.primefaces.model.chart.PieChartModel;
import session.remote.RemoteAdminPerpus;
import session.remote.RemoteAnggota;
import session.remote.RemoteBuku;
import session.remote.RemoteDenda;
import session.remote.RemoteKategori;
import session.remote.RemotePeminjaman;
import session.remote.RemotePeminjamanDetails;

/**
 *
 * @author 1773031_1873024_1873032
 */
@Named(value = "globalBean")
@SessionScoped
public class GlobalBean implements Serializable {

    @EJB
    private RemoteAdminPerpus ejbAdminPerpus;
    @EJB
    private RemoteAnggota ejbAnggota;
    @EJB
    private RemoteBuku ejbBuku;
    @EJB
    private RemoteDenda ejbDenda;
    @EJB
    private RemoteKategori ejbKategori;
    @EJB
    private RemotePeminjaman ejbPeminjaman;
    @EJB
    private RemotePeminjamanDetails ejbPeminjamanDetails;

    private AdminPerpus adminPerpus;
    private Anggota anggota;
    private Buku buku;
    private Denda denda;
    private Kategori kategori;
    private Peminjaman peminjaman;
    private PeminjamanDetails peminjamanDetails;
    private List<PeminjamanDetails> cartPeminjaman, listActivePeminjaman;
    private List<Buku> modelBuku;

    private String indexer, secIndexer, confirmPassword, adminCreationPassword, selector, filter;
    private Integer idKonfirmasiPeminjaman, totalKas;

    public void init() {
        ejbAnggota.logout();
        ejbAdminPerpus.logout();
        redirect_login();
    }

    public void redirect_login() {
        adminPerpus = new AdminPerpus();
        anggota = new Anggota();
        indexer = "login";
        selector = "user";
    }

    public void redirect_register() {
        adminPerpus = new AdminPerpus();
        anggota = new Anggota();
        indexer = "register";
        selector = "user";
        confirmPassword = "";
        adminCreationPassword = "";
    }

    public void redirect_forgotPassword() {
        adminPerpus = new AdminPerpus();
        anggota = new Anggota();
        indexer = "forgot_password";
        selector = "user";
        adminCreationPassword = "";
    }

    public void redirect_admin_home() {
        indexer = "admin_home";
    }

    public void redirect_admin_peminjaman_konfirmasi() {
        indexer = "admin_peminjaman_konfirmasi";
        idKonfirmasiPeminjaman = null;
        peminjaman = new Peminjaman();
        cartPeminjaman = new ArrayList<>();
        listActivePeminjaman = new ArrayList<>();
        buku = new Buku();
        denda = new Denda();
    }

    public void redirect_admin_peminjaman_konfirmasi_pd(PeminjamanDetails param) {
        redirect_admin_peminjaman_konfirmasi();
        idKonfirmasiPeminjaman = param.getIdPeminjaman().getId();
        submitIDKonfirmasiPeminjaman();
    }

    public void redirect_admin_peminjaman_konfirmasi_p(Peminjaman param) {
        redirect_admin_peminjaman_konfirmasi();
        idKonfirmasiPeminjaman = param.getId();
        submitIDKonfirmasiPeminjaman();
    }

    public void redirect_admin_list_peminjaman_aktif() {
        indexer = "admin_list_peminjaman_aktif";
    }

    public void redirect_admin_history_peminjaman_aktif() {
        indexer = "admin_history_peminjaman_aktif";
    }

    public void redirect_admin_data_pengajuan_peminjaman() {
        indexer = "admin_data_pengajuan_peminjaman";
    }

    public void redirect_admin_master_data_buku() {
        indexer = "admin_master_data_buku";
        buku = new Buku();
        buku.setIdKategori(new Kategori());
    }

    public void redirect_admin_master_data_kategori_buku() {
        indexer = "admin_master_data_kategori_buku";
        kategori = new Kategori();
    }

    public void redirect_admin_master_data_anggota() {
        indexer = "admin_master_data_anggota";
    }

    public void redirect_admin_master_data_admin() {
        indexer = "admin_master_data_admin";
    }

    public void redirect_admin_keuangan() {
        indexer = "admin_keuangan";
        totalKas = ejbDenda.getTotalKas();
    }

    public void redirect_admin_profile() {
        indexer = "admin_profile";
        anggota = new Anggota();
        confirmPassword = "";
        adminCreationPassword = "";
    }

    public void redirect_admin_logout() {
        ejbAdminPerpus.logout();
        addMessage(FacesMessage.SEVERITY_INFO, "Notification", "Logout Success");
        redirect_login();
    }

    public void redirect_user_logout() {
        ejbAnggota.logout();
        addMessage(FacesMessage.SEVERITY_INFO, "Notification", "Logout Success");
        redirect_login();
    }

    public void redirect_user_home() {
        indexer = "user_home";
        filter = "";
        kategori = new Kategori("");
        modelBuku = ejbBuku.getAllDataAvailable();
        cartPeminjaman = new ArrayList<>();
        listActivePeminjaman = ejbPeminjamanDetails.getListActivePeminjaman(ejbAnggota.getLoggedUser());
    }

    public void redirect_user_history_peminjaman() {
        indexer = "user_history_peminjaman";
        listActivePeminjaman = ejbPeminjamanDetails.getListActivePeminjaman(ejbAnggota.getLoggedUser());
    }

    public void redirect_user_profile() {
        indexer = "user_profile";
        adminPerpus = new AdminPerpus();
        confirmPassword = "";
    }

    public void viewDenda(Denda param) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Notification", param.getKeterangan());
        PrimeFaces.current().dialog().showMessageDynamic(message, true);
    }

    public void konfirmasiPengembalian_normal(PeminjamanDetails param) {
        if (param.getLamaPeminjaman() > 30) {
            denda = new Denda();
            int jumlahHariTelat = param.getLamaPeminjaman() - 30;
            int jumlahDendaPerHari = 2500;
            int totalDenda = jumlahDendaPerHari * jumlahHariTelat;
            denda.setKeterangan("Pengembalian telat sebanyak " + jumlahHariTelat + " hari.\n"
                    + "Jumlah hari telat (" + jumlahHariTelat + ") x Jumlah denda per hari (" + jumlahDendaPerHari + ") = " + totalDenda);
            denda.setTotalDenda(totalDenda);
            ejbDenda.insert(denda);
            List<Denda> modelDenda = ejbDenda.getAllData();
            denda = modelDenda.get(modelDenda.size() - 1);
            param.setIdDenda(denda);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Notification",
                    denda.getKeterangan()
                    + "\n--------------------------------------------------------------------------\n\n"
                    + "Tagih Customer Sebanyak : " + totalDenda);
            PrimeFaces.current().dialog().showMessageDynamic(message, true);
        }
        param.setTanggalPengembalian(new Date());
        ejbPeminjamanDetails.update(param);
        buku = param.getIdBuku();
        buku.setStok(buku.getStok() + 1);
        ejbBuku.update(buku);
        int tmp = param.getIdPeminjaman().getId();
        redirect_admin_peminjaman_konfirmasi();
        idKonfirmasiPeminjaman = tmp;
        peminjaman = ejbPeminjaman.getByID(idKonfirmasiPeminjaman);
        cartPeminjaman = ejbPeminjamanDetails.getListByIDPeminjaman(peminjaman.getId());
        listActivePeminjaman = ejbPeminjamanDetails.getListActivePeminjaman(peminjaman.getIdAnggota());
        addMessage(FacesMessage.SEVERITY_INFO, "Notification", "Pengembalian Buku Berhasil Di Konfirmasi");
    }

    public void konfirmasiPengembalian_rusak(PeminjamanDetails param) {
        buku = param.getIdBuku();
        if (param.getLamaPeminjaman() > 30) {
            denda = new Denda();
            int jumlahHariTelat = param.getLamaPeminjaman() - 30;
            int jumlahDendaPerHari = 2500;
            int jumlahDendaRusak = (int) (buku.getHarga() * 0.3);
            int totalDenda = (jumlahDendaPerHari * jumlahHariTelat) + jumlahDendaRusak;
            denda.setKeterangan("Buku Rusak, Pengembalian telat sebanyak " + jumlahHariTelat + " hari.\n"
                    + "(Jumlah hari telat (" + jumlahHariTelat + ") x Jumlah denda per hari (" + jumlahDendaPerHari + "))\n"
                    + "+ Jumlah Denda Rusak (30% Harga Buku (" + buku.getHarga() + " x 0.3 = " + jumlahDendaRusak + ")) = " + totalDenda);
            denda.setTotalDenda(totalDenda);
            ejbDenda.insert(denda);
            List<Denda> modelDenda = ejbDenda.getAllData();
            denda = modelDenda.get(modelDenda.size() - 1);
            param.setIdDenda(denda);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Notification",
                    denda.getKeterangan()
                    + "\n--------------------------------------------------------------------------\n\n"
                    + "Tagih Customer Sebanyak : " + totalDenda);
            PrimeFaces.current().dialog().showMessageDynamic(message, true);
        } else {
            denda = new Denda();
            int jumlahDendaRusak = (int) (buku.getHarga() * 0.3);
            int totalDenda = jumlahDendaRusak;
            denda.setKeterangan("Buku Rusak.\n"
                    + "Jumlah Denda Rusak (30% Harga Buku) = " + totalDenda);
            denda.setTotalDenda(totalDenda);
            ejbDenda.insert(denda);
            List<Denda> modelDenda = ejbDenda.getAllData();
            denda = modelDenda.get(modelDenda.size() - 1);
            param.setIdDenda(denda);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Notification",
                    denda.getKeterangan()
                    + "\n--------------------------------------------------------------------------\n\n"
                    + "Tagih Customer Sebanyak : " + totalDenda);
            PrimeFaces.current().dialog().showMessageDynamic(message, true);
        }
        param.setTanggalPengembalian(new Date());
        ejbPeminjamanDetails.update(param);
        buku.setStok(buku.getStok() + 1);
        ejbBuku.update(buku);
        int tmp = param.getIdPeminjaman().getId();
        redirect_admin_peminjaman_konfirmasi();
        idKonfirmasiPeminjaman = tmp;
        peminjaman = ejbPeminjaman.getByID(idKonfirmasiPeminjaman);
        cartPeminjaman = ejbPeminjamanDetails.getListByIDPeminjaman(peminjaman.getId());
        listActivePeminjaman = ejbPeminjamanDetails.getListActivePeminjaman(peminjaman.getIdAnggota());
        addMessage(FacesMessage.SEVERITY_INFO, "Notification", "Pengembalian Buku Berhasil Di Konfirmasi");
    }

    public void konfirmasiPengembalian_hilang(PeminjamanDetails param) {
        buku = param.getIdBuku();
        denda = new Denda();
        int jumlahDendaHilang = buku.getHarga();
        int totalDenda = jumlahDendaHilang;
        denda.setKeterangan("Buku Hilang.\n"
                + "Jumlah Denda Buku Hilang (Harga Buku) = " + totalDenda);
        denda.setTotalDenda(totalDenda);
        ejbDenda.insert(denda);
        List<Denda> modelDenda = ejbDenda.getAllData();
        denda = modelDenda.get(modelDenda.size() - 1);
        param.setIdDenda(denda);
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Notification",
                denda.getKeterangan()
                + "\n--------------------------------------------------------------------------\n\n"
                + "Tagih Customer Sebanyak : " + totalDenda);
        PrimeFaces.current().dialog().showMessageDynamic(message, true);
        param.setTanggalPengembalian(new Date());
        ejbPeminjamanDetails.update(param);
        int tmp = param.getIdPeminjaman().getId();
        redirect_admin_peminjaman_konfirmasi();
        idKonfirmasiPeminjaman = tmp;
        peminjaman = ejbPeminjaman.getByID(idKonfirmasiPeminjaman);
        cartPeminjaman = ejbPeminjamanDetails.getListByIDPeminjaman(peminjaman.getId());
        listActivePeminjaman = ejbPeminjamanDetails.getListActivePeminjaman(peminjaman.getIdAnggota());
        addMessage(FacesMessage.SEVERITY_INFO, "Notification", "Pengembalian Buku Berhasil Di Konfirmasi");
    }

    public void konfirmasiPeminjaman(PeminjamanDetails param) {
        param.setStatusKonfirmasi(true);
        param.setTanggalPengambilan(new Date());
        ejbPeminjamanDetails.update(param);
        buku = param.getIdBuku();
        buku.setStok(buku.getStok() - 1);
        ejbBuku.update(buku);
        int tmp = param.getIdPeminjaman().getId();
        redirect_admin_peminjaman_konfirmasi();
        idKonfirmasiPeminjaman = tmp;
        peminjaman = ejbPeminjaman.getByID(idKonfirmasiPeminjaman);
        cartPeminjaman = ejbPeminjamanDetails.getListByIDPeminjaman(peminjaman.getId());
        listActivePeminjaman = ejbPeminjamanDetails.getListActivePeminjaman(peminjaman.getIdAnggota());
        addMessage(FacesMessage.SEVERITY_INFO, "Notification", "Pengambilan Buku Berhasil Di Konfirmasi");
    }

    public Boolean isActivePeminjamanFull() {
        return listActivePeminjaman.size() >= 3;
    }

    public void submitIDKonfirmasiPeminjaman() {
        peminjaman = ejbPeminjaman.getByID(idKonfirmasiPeminjaman);
        if (peminjaman == null) {
            redirect_admin_peminjaman_konfirmasi();
            addMessage(FacesMessage.SEVERITY_ERROR, "Notification", "ID Peminjaman Invalid");
        } else {
            cartPeminjaman = ejbPeminjamanDetails.getListByIDPeminjaman(peminjaman.getId());
            listActivePeminjaman = ejbPeminjamanDetails.getListActivePeminjaman(peminjaman.getIdAnggota());
            addMessage(FacesMessage.SEVERITY_INFO, "Notification", "ID Peminjaman Ditemukan");
            if (peminjaman.getIdAdminPerpus() == null) {
                peminjaman.setIdAdminPerpus(ejbAdminPerpus.getLoggedUser());
                ejbPeminjaman.update(peminjaman);
                addMessage(FacesMessage.SEVERITY_INFO, "Notification", "Penanggung Jawab Peminjaman Ini Telah Di Set");
            }
        }
    }

    public void pinjamBuku() {
        peminjaman = new Peminjaman();
        peminjaman.setIdAnggota(ejbAnggota.getLoggedUser());
        peminjaman.setTanggalPengajuan(new Date());
        peminjaman.setTotalBukuYangDiajukan(cartPeminjaman.size());
        ejbPeminjaman.insert(peminjaman);
        List<Peminjaman> modelPeminjaman = ejbPeminjaman.getAllData();
        Collections.reverse(modelPeminjaman);
        peminjaman = modelPeminjaman.get(0);
        for (PeminjamanDetails pd : cartPeminjaman) {
            pd.setIdPeminjaman(peminjaman);
            pd.setStatusKonfirmasi(false);
            ejbPeminjamanDetails.insert(pd);
        }
        redirect_user_history_peminjaman();
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Notification",
                "Berikan Informasi Dibawah Ini Kepada Admin\n"
                + "--------------------------------------------------------------------------\n\n"
                + "ID Peminjaman : " + peminjaman.getIdAliiasFormatted());
        PrimeFaces.current().dialog().showMessageDynamic(message, true);
    }

    public void addToCartPeminjamanBuku() {
        peminjamanDetails = new PeminjamanDetails();
        peminjamanDetails.setIdBuku(buku);
        cartPeminjaman.add(peminjamanDetails);
    }

    public void removeFromCartPeminjamanBuku(PeminjamanDetails pd) {
        int idx = 0;
        for (PeminjamanDetails p : cartPeminjaman) {
            if (Objects.equals(pd.getIdBuku().getId(), p.getIdBuku().getId())) {
                break;
            }
            idx++;
        }
        cartPeminjaman.remove(idx);
    }

    public Boolean isBookAlreadyInCart() {
        for (PeminjamanDetails p : cartPeminjaman) {
            if (Objects.equals(buku.getId(), p.getIdBuku().getId())) {
                return true;
            }
        }
        return false;
    }

    public Boolean isBookActivePeminjaman() {
        for (PeminjamanDetails p : listActivePeminjaman) {
            if (Objects.equals(buku.getId(), p.getIdBuku().getId())) {
                return true;
            }
        }
        return false;
    }

    public Boolean isBookCartFull() {
        return cartPeminjaman.size() >= (3 - listActivePeminjaman.size());
    }

    public void resetUser() {
        adminPerpus = new AdminPerpus();
        anggota = new Anggota();
    }

    public void showBookDetailsUser(Buku b) {
        buku = b;
        PrimeFaces.current().executeScript("PF('show_book').show()");
    }

    public List<Buku> getDataBukuFiltered() {
        List<Buku> o = new ArrayList<>();
        if (kategori.getNama().equals("") && filter.equals("")) {
            return modelBuku;
        } else if (!kategori.getNama().equals("") && !filter.equals("")) {
            for (Buku b : modelBuku) {
                if (b.getIdKategori().getNama().toLowerCase().equals(kategori.getNama().toLowerCase()) && b.getJudul().toLowerCase().contains(filter.toLowerCase())) {
                    o.add(b);
                }
            }
            return o;
        } else if (!kategori.getNama().equals("")) {
            for (Buku b : modelBuku) {
                if (Objects.equals(b.getIdKategori().getId(), kategori.getId())) {
                    o.add(b);
                }
            }
        } else {
            for (Buku b : modelBuku) {
                if (b.getJudul().toLowerCase().contains(filter.toLowerCase())) {
                    o.add(b);
                }
            }
        }
        return o;
    }

    public void setKategoriFilterAll() {
        kategori = new Kategori("");
    }

    public void resetFilter() {
        filter = "";
        kategori = new Kategori("");
    }

    public void changePasswordAdmin() {
        if (anggota.getPassword().length() >= 6) {
            if (confirmPassword.equals(anggota.getPassword())) {
                if (Perpus.ADMIN_CREATION_PASSWORD.equals(adminCreationPassword)) {
                    adminPerpus.setPassword(anggota.getPassword());
                    ejbAdminPerpus.update(adminPerpus);
                    addMessage(FacesMessage.SEVERITY_INFO, "Notification", "Password Change Success");
                    redirect_admin_profile();
                } else {
                    addMessage(FacesMessage.SEVERITY_INFO, "Notification", "Admin Creation Password Invalid");
                }
            } else {
                addMessage(FacesMessage.SEVERITY_INFO, "Notification", "Confirm Password Invalid");
            }
        } else {
            addMessage(FacesMessage.SEVERITY_INFO, "Notification", "Minimum Password is 6 characters");
        }
    }

    public void changePasswordAnggota() {
        if (adminPerpus.getPassword().length() >= 6) {
            if (confirmPassword.equals(adminPerpus.getPassword())) {
                anggota.setPassword(adminPerpus.getPassword());
                ejbAnggota.update(anggota);
                addMessage(FacesMessage.SEVERITY_INFO, "Notification", "Password Change Success");
                redirect_user_profile();
            } else {
                addMessage(FacesMessage.SEVERITY_INFO, "Notification", "Confirm Password Invalid");
            }
        } else {
            addMessage(FacesMessage.SEVERITY_INFO, "Notification", "Minimum Password is 6 characters");
        }
    }

    public void login() {
        if ("user".equals(selector)) {
            if (ejbAnggota.login(anggota.getUsername(), anggota.getPassword())) {
                anggota = ejbAnggota.getLoggedUser();
                if (anggota.getIsBanned()) {
                    addMessage(FacesMessage.SEVERITY_ERROR, "Notification", "Akun anda telah di ban, harap hubungi administrator");
                    ejbAnggota.logout();
                    redirect_login();
                } else {
                    addMessage(FacesMessage.SEVERITY_INFO, "Notification", "Login Success");
                    redirect_user_home();
                }
            } else {
                addMessage(FacesMessage.SEVERITY_INFO, "Notification", "Username / Password Invalid");
            }
        } else {
            if (ejbAdminPerpus.login(anggota.getUsername(), anggota.getPassword())) {
                adminPerpus = ejbAdminPerpus.getLoggedUser();
                addMessage(FacesMessage.SEVERITY_INFO, "Notification", "Login Success");
                redirect_admin_home();
            } else {
                addMessage(FacesMessage.SEVERITY_INFO, "Notification", "Username / Password Invalid");
            }
        }
    }

    public void register() {
        if (anggota.getUsername().length() < 6) {
            addMessage(FacesMessage.SEVERITY_INFO, "Notification", "Minimum username is 6 characters");
        } else if (anggota.getPassword().length() < 6) {
            addMessage(FacesMessage.SEVERITY_INFO, "Notification", "Minimum Password is 6 characters");
        } else if ("user".equals(selector)) {
            if (ejbAnggota.checkUsernameAlreadyExist(anggota.getUsername())) {
                addMessage(FacesMessage.SEVERITY_INFO, "Notification", "Username already exist");
            } else {
                if (anggota.getPassword().equals(confirmPassword)) {
                    anggota.setTanggalBergabung(new Date());
                    anggota.setIsBanned(false);
                    ejbAnggota.insert(anggota);
                    addMessage(FacesMessage.SEVERITY_INFO, "Notification", "Register Success");
                    redirect_login();
                } else {
                    addMessage(FacesMessage.SEVERITY_INFO, "Notification", "Confirm Password Invalid");
                }
            }
        } else {
            if (ejbAdminPerpus.checkUsernameAlreadyExist(anggota.getUsername())) {
                addMessage(FacesMessage.SEVERITY_INFO, "Notification", "Username already exist");
            } else {
                if (anggota.getPassword().equals(confirmPassword)) {
                    if (Perpus.ADMIN_CREATION_PASSWORD.equals(adminCreationPassword)) {
                        adminPerpus.setNama(anggota.getNama());
                        adminPerpus.setPassword(anggota.getPassword());
                        adminPerpus.setTanggalLahir(anggota.getTanggalLahir());
                        adminPerpus.setUsername(anggota.getUsername());
                        ejbAdminPerpus.insert(adminPerpus);
                        addMessage(FacesMessage.SEVERITY_INFO, "Notification", "Register Success");
                        redirect_login();
                    } else {
                        addMessage(FacesMessage.SEVERITY_INFO, "Notification", "Admin Creation Password Invalid");
                    }
                } else {
                    addMessage(FacesMessage.SEVERITY_INFO, "Notification", "Confirm Password Invalid");
                }
            }
        }
    }

    public void forgotPassword() {
        if ("user".equals(selector)) {
            Anggota result = ejbAnggota.forgotPassword(anggota.getUsername(), anggota.getTanggalLahir());
            if (result == null) {
                addMessage(FacesMessage.SEVERITY_INFO, "Notification", "Username / Tanggal Lahir Invalid");
            } else {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Notification", "Password Details : '" + result.getPassword() + "'");
                PrimeFaces.current().dialog().showMessageDynamic(message, true);
                redirect_login();
            }
        } else {
            if (Perpus.ADMIN_CREATION_PASSWORD.equals(adminCreationPassword)) {
                AdminPerpus result = ejbAdminPerpus.forgotPassword(anggota.getUsername(), anggota.getTanggalLahir());
                if (result == null) {
                    addMessage(FacesMessage.SEVERITY_INFO, "Notification", "Username / Tanggal Lahir Invalid");
                } else {
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Notification", "Password Details : '" + result.getPassword() + "'");
                    PrimeFaces.current().dialog().showMessageDynamic(message, true);
                    redirect_login();
                }
            } else {
                addMessage(FacesMessage.SEVERITY_INFO, "Notification", "Admin Creation Password Invalid");
            }
        }
    }

    public void masterDataBuku_add() {
        if (checkBuku()) {
            ejbBuku.insert(buku);
            addMessage(FacesMessage.SEVERITY_INFO, "Notification", "Data Berhasil Disimpan");
            redirect_admin_master_data_buku();
        }
    }

    public void masterDataBuku_update() {
        if (checkBuku()) {
            ejbBuku.update(buku);
            addMessage(FacesMessage.SEVERITY_INFO, "Notification", "Data Berhasil Diupdate");
            redirect_admin_master_data_buku();
        }
    }

    public void masterDataBuku_delete(Buku param) {
        ejbBuku.delete(param);
        addMessage(FacesMessage.SEVERITY_INFO, "Notification", "Data Berhasil Dihapus");
        redirect_admin_master_data_buku();
    }

    public boolean checkBuku() {
        if (!new UrlValidator().isValid(buku.getGambarLink())) {
            addMessage(FacesMessage.SEVERITY_INFO, "Notification", "Link Gambar Tidak Valid");
            return false;
        } else if (!new UrlValidator().isValid(buku.getGambarSrc())) {
            addMessage(FacesMessage.SEVERITY_INFO, "Notification", "Link Referensi Tidak Valid");
            return false;
        }
        return true;
    }

    public void masterDataKategori_add() {
        ejbKategori.insert(kategori);
        addMessage(FacesMessage.SEVERITY_INFO, "Notification", "Data Berhasil Disimpan");
        redirect_admin_master_data_kategori_buku();
    }

    public void masterDataKategori_update() {
        ejbKategori.update(kategori);
        addMessage(FacesMessage.SEVERITY_INFO, "Notification", "Data Berhasil Diupdate");
        redirect_admin_master_data_kategori_buku();
    }

    public void masterDataKategori_delete(Kategori param) {
        ejbKategori.delete(param);
        addMessage(FacesMessage.SEVERITY_INFO, "Notification", "Data Berhasil Dihapus");
        redirect_admin_master_data_kategori_buku();
    }

    public void banAnggota(Anggota param) {
        param.setIsBanned(true);
        ejbAnggota.update(param);
        addMessage(FacesMessage.SEVERITY_INFO, "Notification", "Akun Berhasil Diban");
    }

    public void unbanAnggota(Anggota param) {
        param.setIsBanned(false);
        ejbAnggota.update(param);
        addMessage(FacesMessage.SEVERITY_INFO, "Notification", "Akun Berhasil Diunban");
    }

    public PieChartModel getGraphUmurAnggota() {
        PieChartModel output = new PieChartModel();

        int anak = 0, remaja = 0, dewasa = 0;

        for (Anggota a : ejbAnggota.getAllData()) {
            if (a.getUmur() >= 22) {
                dewasa += 1;
            } else if (a.getUmur() <= 12) {
                anak += 1;
            } else {
                remaja += 1;
            }
        }
        output.set("Anak - Anak", anak);
        output.set("Remaja", remaja);
        output.set("Dewasa", dewasa);

        output.setTitle("Grafik Umur Anggota");
        output.setLegendPosition("w");
        output.setShadow(false);
        return output;
    }

    public PieChartModel getGraphKategoriBuku() {
        PieChartModel output = new PieChartModel();
        for (Kategori kt : ejbKategori.getAllData()) {
            output.set(kt.getNama(), ejbBuku.getByKategoriID(kt.getId()).size());
        }

        output.setTitle("Grafik Kategori Buku");
        output.setLegendPosition("w");
        output.setShadow(false);
        return output;
    }

    public RemoteAdminPerpus getEjbAdminPerpus() {
        return ejbAdminPerpus;
    }

    public void setEjbAdminPerpus(RemoteAdminPerpus ejbAdminPerpus) {
        this.ejbAdminPerpus = ejbAdminPerpus;
    }

    public RemoteAnggota getEjbAnggota() {
        return ejbAnggota;
    }

    public void setEjbAnggota(RemoteAnggota ejbAnggota) {
        this.ejbAnggota = ejbAnggota;
    }

    public RemoteBuku getEjbBuku() {
        return ejbBuku;
    }

    public void setEjbBuku(RemoteBuku ejbBuku) {
        this.ejbBuku = ejbBuku;
    }

    public RemoteDenda getEjbDenda() {
        return ejbDenda;
    }

    public void setEjbDenda(RemoteDenda ejbDenda) {
        this.ejbDenda = ejbDenda;
    }

    public RemoteKategori getEjbKategori() {
        return ejbKategori;
    }

    public void setEjbKategori(RemoteKategori ejbKategori) {
        this.ejbKategori = ejbKategori;
    }

    public RemotePeminjaman getEjbPeminjaman() {
        return ejbPeminjaman;
    }

    public void setEjbPeminjaman(RemotePeminjaman ejbPeminjaman) {
        this.ejbPeminjaman = ejbPeminjaman;
    }

    public RemotePeminjamanDetails getEjbPeminjamanDetails() {
        return ejbPeminjamanDetails;
    }

    public void setEjbPeminjamanDetails(RemotePeminjamanDetails ejbPeminjamanDetails) {
        this.ejbPeminjamanDetails = ejbPeminjamanDetails;
    }

    public AdminPerpus getAdminPerpus() {
        return adminPerpus;
    }

    public void setAdminPerpus(AdminPerpus adminPerpus) {
        this.adminPerpus = adminPerpus;
    }

    public Anggota getAnggota() {
        return anggota;
    }

    public void setAnggota(Anggota anggota) {
        this.anggota = anggota;
    }

    public Buku getBuku() {
        return buku;
    }

    public void setBuku(Buku buku) {
        this.buku = buku;
    }

    public Denda getDenda() {
        return denda;
    }

    public void setDenda(Denda denda) {
        this.denda = denda;
    }

    public Kategori getKategori() {
        return kategori;
    }

    public void setKategori(Kategori kategori) {
        this.kategori = kategori;
    }

    public Peminjaman getPeminjaman() {
        return peminjaman;
    }

    public void setPeminjaman(Peminjaman peminjaman) {
        this.peminjaman = peminjaman;
    }

    public PeminjamanDetails getPeminjamanDetails() {
        return peminjamanDetails;
    }

    public void setPeminjamanDetails(PeminjamanDetails peminjamanDetails) {
        this.peminjamanDetails = peminjamanDetails;
    }

    public List<PeminjamanDetails> getCartPeminjaman() {
        return cartPeminjaman;
    }

    public void setCartPeminjaman(List<PeminjamanDetails> cartPeminjaman) {
        this.cartPeminjaman = cartPeminjaman;
    }

    public String getIndexer() {
        return indexer;
    }

    public void setIndexer(String indexer) {
        this.indexer = indexer;
    }

    public String getSecIndexer() {
        return secIndexer;
    }

    public void setSecIndexer(String secIndexer) {
        this.secIndexer = secIndexer;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getAdminCreationPassword() {
        return adminCreationPassword;
    }

    public void setAdminCreationPassword(String adminCreationPassword) {
        this.adminCreationPassword = adminCreationPassword;
    }

    public String getSelector() {
        return selector;
    }

    public void setSelector(String selector) {
        this.selector = selector;
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    public List<PeminjamanDetails> getListActivePeminjaman() {
        return listActivePeminjaman;
    }

    public void setListActivePeminjaman(List<PeminjamanDetails> listActivePeminjaman) {
        this.listActivePeminjaman = listActivePeminjaman;
    }

    public Integer getIdKonfirmasiPeminjaman() {
        return idKonfirmasiPeminjaman;
    }

    public void setIdKonfirmasiPeminjaman(Integer idKonfirmasiPeminjaman) {
        this.idKonfirmasiPeminjaman = idKonfirmasiPeminjaman;
    }

    public void addMessage(FacesMessage.Severity facesMessage_Severity, String title, String detail) {
        FacesMessage message = new FacesMessage(facesMessage_Severity, title, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public Integer getTotalKas() {
        return totalKas;
    }

    public void setTotalKas(Integer totalKas) {
        this.totalKas = totalKas;
    }

    public void injectData() throws ParseException {
        adminPerpus = new AdminPerpus("qweqwe",
                "qweqwe",
                "root",
                "24/09/1999");
        if (ejbAdminPerpus.checkUsernameAlreadyExist(adminPerpus.getUsername())) {
            addMessage(FacesMessage.SEVERITY_ERROR, "Notification", "Inject Fail, Data Already Injected");
        } else {
            ejbAdminPerpus.insert(adminPerpus);
            ejbAnggota.insert(new Anggota("qweqwe",
                    "qweqwe",
                    "root",
                    "04827481099",
                    "23/08/1999",
                    "13/06/2021",
                    false));
            ejbAnggota.insert(new Anggota("bocil123",
                    "bocil123",
                    "bocil ganteng",
                    "126126123123",
                    "12/02/2015",
                    "15/02/2021",
                    false));
            ejbAnggota.insert(new Anggota("jametqwe",
                    "jametqwe",
                    "saya jamet",
                    "126126123123",
                    "17/07/1950",
                    "15/02/2021",
                    false));
            ejbAnggota.insert(new Anggota("asdasd",
                    "asdasd",
                    "si asd",
                    "236236",
                    "22/09/2000",
                    "15/02/2021",
                    false));
            ejbKategori.insert(new Kategori("Technology"));
            ejbKategori.insert(new Kategori("Science Fiction"));
            ejbKategori.insert(new Kategori("Romance"));
            ejbKategori.insert(new Kategori("Cooking and Food"));
            ejbKategori.insert(new Kategori("Dogs"));
            ejbKategori.insert(new Kategori("Video Games"));
            List<Kategori> mk = ejbKategori.getAllData();
            ejbBuku.insert(new Buku("The Innovators: How a Group of Hackers, Geniuses, and Geeks Created the Digital Revolution",
                    250000,
                    5,
                    "Walter Isaacson",
                    "04/09/2013",
                    873,
                    4,
                    "https://imgv2-1-f.scribdassets.com/img/word_document/239488191/original/216x287/e2705c0b3d/1623260028?v=1",
                    "https://www.scribd.com/book/239488191/The-Innovators-How-a-Group-of-Hackers-Geniuses-and-Geeks-Created-the-Digital-Revolution",
                    mk.get(0)));
            ejbBuku.insert(new Buku("AI Superpowers: China, Silicon Valley, and the New World Order",
                    175000,
                    6,
                    "Kai-Fu Lee",
                    "05/09/2013",
                    365,
                    4,
                    "https://imgv2-1-f.scribdassets.com/img/word_document/449815155/original/216x287/5d317a1cab/1620609801?v=1",
                    "https://www.scribd.com/book/449815155/AI-Superpowers-China-Silicon-Valley-and-the-New-World-Order",
                    mk.get(0)));
            ejbBuku.insert(new Buku("Coding For Dummies",
                    200000,
                    7,
                    "Nikhil Abraham",
                    "06/09/2013",
                    434,
                    5,
                    "https://imgv2-2-f.scribdassets.com/img/word_document/314291430/original/216x287/b9b4f68690/1617290164?v=1",
                    "https://www.scribd.com/book/314291430/Coding-For-Dummies",
                    mk.get(0)));
            ejbBuku.insert(new Buku("Through the Ever Night",
                    150000,
                    7,
                    "Veronica rossi",
                    "27/06/2017",
                    314,
                    4,
                    "https://imgv2-1-f.scribdassets.com/img/word_document/163652058/original/432x574/d20aee3206/1622840675?v=1",
                    "https://www.scribd.com/book/163652058/Through-the-Ever-Night",
                    mk.get(1)));
            ejbBuku.insert(new Buku("Uglies",
                    140000,
                    4,
                    "Scott Westerfeld",
                    "10/05/2006",
                    423,
                    4,
                    "https://imgv2-1-f.scribdassets.com/img/word_document/225126950/original/432x574/79052cf8d6/1623072267?v=1",
                    "https://www.scribd.com/book/225126950/Uglies",
                    mk.get(1)));
            ejbBuku.insert(new Buku("Renegades Chapter Sampler",
                    100000,
                    5,
                    "Marissa Meyer",
                    "10/10/2017",
                    98,
                    5,
                    "https://imgv2-2-f.scribdassets.com/img/word_document/489957388/original/216x287/9c495a7da6/1622269861?v=1",
                    "https://www.scribd.com/book/489957388/Renegades-Chapter-Sampler",
                    mk.get(1)));
            ejbBuku.insert(new Buku("The Unhoneymooners",
                    180000,
                    7,
                    "Christina Lauren",
                    "07/09/2013",
                    354,
                    4,
                    "https://imgv2-2-f.scribdassets.com/img/word_document/406535992/original/216x287/27e880328a/1623260172?v=1",
                    "https://www.scribd.com/book/406535992/The-Unhoneymooners",
                    mk.get(2)));
            ejbBuku.insert(new Buku("Maybe Someday",
                    190000,
                    5,
                    "Collen Hoover",
                    "08/09/2013",
                    443,
                    4,
                    "https://imgv2-2-f.scribdassets.com/img/word_document/233886933/original/216x287/e4fde59aaf/1623264908?v=1",
                    "https://www.scribd.com/book/233886933/Maybe-Someday",
                    mk.get(2)));
            ejbBuku.insert(new Buku("Dancing at Midnight",
                    185000,
                    6,
                    "Julia Quinn",
                    "09/09/2013",
                    389,
                    4,
                    "https://imgv2-2-f.scribdassets.com/img/word_document/163637573/original/216x287/827d8d1fb4/1623067136?v=1",
                    "https://www.scribd.com/book/163637573/Dancing-at-Midnight",
                    mk.get(2)));
            ejbBuku.insert(new Buku("Mastering the Art of Chinese Cooking",
                    450000,
                    4,
                    "Eileen Yin-Fei Lo and Susie Cushner",
                    "10/09/2013",
                    776,
                    4,
                    "https://imgv2-1-f.scribdassets.com/img/word_document/239946972/original/216x287/e81adac1fb/1617229623?v=1",
                    "https://www.scribd.com/book/239946972/Mastering-the-Art-of-Chinese-Cooking",
                    mk.get(3)));
            ejbBuku.insert(new Buku("Betty Crocker Lost Recipes: Beloved Vintage Recipes for Today's Kitchen",
                    187000,
                    5,
                    "Betty Crocker",
                    "11/09/2013",
                    385,
                    4,
                    "https://imgv2-2-f.scribdassets.com/img/word_document/449814265/original/216x287/ac773fe009/1620349701?v=1",
                    "https://www.scribd.com/book/449814265/Betty-Crocker-Lost-Recipes-Beloved-Vintage-Recipes-for-Today-s-Kitchen",
                    mk.get(3)));
            ejbBuku.insert(new Buku("Flour: A Baker's Collection of Spectacular Recipes",
                    500000,
                    5,
                    "Joanne Chang, Christie Matheson and Keller Keller",
                    "12/09/2013",
                    575,
                    4,
                    "https://imgv2-1-f.scribdassets.com/img/word_document/239832263/original/216x287/3c2dc8a5f0/1617229617?v=1",
                    "https://www.scribd.com/book/239832263/Flour-A-Baker-s-Collection-of-Spectacular-Recipes",
                    mk.get(3)));
            ejbBuku.insert(new Buku("Merle's Door: Lessons from a Freethinking Dog",
                    125000,
                    5,
                    "Ted Kerasote",
                    "21/04/2008",
                    539,
                    4,
                    "https://imgv2-2-f.scribdassets.com/img/word_document/249309715/original/216x287/e07c88f0d3/1619054887?v=1",
                    "https://www.scribd.com/book/249309715/Merle-s-Door-Lessons-from-a-Freethinking-Dog",
                    mk.get(4)));
            ejbBuku.insert(new Buku("Lessons From Lucy: The Simple Joys of an Old, Happy Dog",
                    130000,
                    5,
                    "Dave Berry",
                    "02/04/2019",
                    179,
                    4,
                    "https://imgv2-1-f.scribdassets.com/img/word_document/401747960/original/216x287/1a38520b35/1621394064?v=1",
                    "https://www.scribd.com/book/401747960/Lessons-From-Lucy-The-Simple-Joys-of-an-Old-Happy-Dog",
                    mk.get(4)));
            ejbBuku.insert(new Buku("Lucky Dog Lessons: Train Your Dog in 7 Days",
                    100000,
                    6,
                    "Brandon McMillan",
                    "04/10/2016",
                    386,
                    5,
                    "https://imgv2-1-f.scribdassets.com/img/word_document/323917609/original/216x287/3fd02cd99b/1622840848?v=1",
                    "https://www.scribd.com/book/323917609/Lucky-Dog-Lessons-Train-Your-Dog-in-7-Days",
                    mk.get(4)));
            ejbBuku.insert(new Buku("Blood, Sweat, and Pixels: The Triumphant, Turbulent Stories Behind How Video Games Are Made",
                    145000,
                    5,
                    "Jason Schreier",
                    "05/09/2017",
                    378,
                    4,
                    "https://imgv2-2-f.scribdassets.com/img/word_document/356399357/original/216x287/2f5d7258f9/1623411170?v=1",
                    "https://www.scribd.com/book/356399357/Blood-Sweat-and-Pixels-The-Triumphant-Turbulent-Stories-Behind-How-Video-Games-Are-Made",
                    mk.get(5)));
            ejbBuku.insert(new Buku("Pokémon X Walkthrough and Pokémon Y Walkthrough Ultımate Game Guides",
                    120000,
                    5,
                    "Game Ultımate Game Guides",
                    "24/03/2016",
                    258,
                    4,
                    "https://imgv2-1-f.scribdassets.com/img/word_document/306219423/original/216x287/2e0314272c/1618822200?v=1",
                    "https://www.scribd.com/book/306219423/Pokemon-X-Walkthrough-and-Pokemon-Y-Walkthrough-Ult%C4%B1mate-Game-Guides",
                    mk.get(5)));
            ejbBuku.insert(new Buku("The Ultimate Roblox Book: An Unofficial Guide: Learn How to Build Your Own Worlds, Customize Your Games, and So Much More!",
                    110000,
                    7,
                    "David Jagneaux",
                    "02/01/2018",
                    237,
                    5,
                    "https://imgv2-2-f.scribdassets.com/img/word_document/366624722/original/216x287/03ed5d63ee/1621393627?v=1",
                    "https://www.scribd.com/book/366624722/The-Ultimate-Roblox-Book-An-Unofficial-Guide-Learn-How-to-Build-Your-Own-Worlds-Customize-Your-Games-and-So-Much-More",
                    mk.get(5)));
            ejbBuku.insert(new Buku("Console Wars: Sega, Nintendo, and the Battle that Defined a Generation",
                    525000,
                    3,
                    "Blake J. Harris",
                    "07/24/2018",
                    808,
                    4,
                    "https://imgv2-1-f.scribdassets.com/img/word_document/221042173/original/216x287/a3b2ebc5cd/1623148518?v=1",
                    "https://www.scribd.com/book/221042173/Console-Wars-Sega-Nintendo-and-the-Battle-that-Defined-a-Generation",
                    mk.get(0)));
            ejbBuku.insert(new Buku("Chaos Monkeys: Obscene Fortune and Random Failure in Silicon Valley",
                    490000,
                    4,
                    "Antonio Garcia Martinez",
                    "07/24/2018",
                    757,
                    4,
                    "https://imgv2-1-f.scribdassets.com/img/word_document/383643735/original/216x287/27d573aaaf/1622840899?v=1",
                    "https://www.scribd.com/book/383643735/Chaos-Monkeys-Obscene-Fortune-and-Random-Failure-in-Silicon-Valley",
                    mk.get(0)));
            ejbBuku.insert(new Buku("Introducing Artificial Intelligence: A Graphic Guide",
                    195000,
                    6,
                    "Henry Brighton and Howard Selina",
                    "09/03/2015",
                    332,
                    4,
                    "https://imgv2-2-f.scribdassets.com/img/word_document/353201256/original/216x287/ca3fde8641/1623260918?v=1",
                    "https://www.scribd.com/book/353201256/Introducing-Artificial-Intelligence-A-Graphic-Guide",
                    mk.get(0)));
            ejbBuku.insert(new Buku("LikeWar: The Weaponization of Social Media",
                    350000,
                    5,
                    "P. W. Singer and Emerson T. Brooking",
                    "10/02/2018",
                    632,
                    4,
                    "https://imgv2-2-f.scribdassets.com/img/word_document/449813372/original/216x287/b9f48c407f/1621819156?v=1",
                    "https://www.scribd.com/book/449813372/LikeWar-The-Weaponization-of-Social-Media",
                    mk.get(0)));
            ejbBuku.insert(new Buku("The Seven Husbands of Evelyn Hugo: A Novel",
                    250000,
                    4,
                    "Taylor Jenkins Reid",
                    "06/13/2017",
                    477,
                    4,
                    "https://imgv2-1-f.scribdassets.com/img/word_document/348099344/original/216x287/4dd4018ba7/1623243926?v=1",
                    "https://www.scribd.com/book/348099344/The-Seven-Husbands-of-Evelyn-Hugo-A-Novel",
                    mk.get(2)));
            ejbBuku.insert(new Buku("Heartbreaker",
                    275000,
                    5,
                    "Julie Garwood",
                    "02/21/2001",
                    533,
                    4,
                    "https://imgv2-2-f.scribdassets.com/img/word_document/224421568/original/216x287/2329021f49/1621384282?v=1",
                    "https://www.scribd.com/book/224421568/Heartbreaker",
                    mk.get(2)));
            ejbBuku.insert(new Buku("Twice in a Blue Moon",
                    165000,
                    6,
                    "Christina Lauren",
                    "10/22/2019",
                    358,
                    4,
                    "https://imgv2-2-f.scribdassets.com/img/word_document/428513343/original/216x287/c6bd476534/1623260328?v=1",
                    "https://www.scribd.com/book/428513343/Twice-in-a-Blue-Moon",
                    mk.get(2)));
            ejbBuku.insert(new Buku("Love and Other Words",
                    150000,
                    7,
                    "Christina Lauren",
                    "04/10/2018",
                    375,
                    4,
                    "https://imgv2-1-f.scribdassets.com/img/word_document/372755316/original/216x287/d35e7bf53e/1623263332?v=1",
                    "https://www.scribd.com/book/372755316/Love-and-Other-Words",
                    mk.get(2)));
            ejbBuku.insert(new Buku("The Last Tudor",
                    470000,
                    5,
                    "Philippa Gregory",
                    "08/08/2017",
                    684,
                    4,
                    "https://imgv2-1-f.scribdassets.com/img/word_document/354319649/original/216x287/98252e88ba/1621392794?v=1",
                    "https://www.scribd.com/book/354319649/The-Last-Tudor",
                    mk.get(2)));
            ejbBuku.insert(new Buku("Sausage Making: The Definitive Guide with Recipes",
                    300000,
                    4,
                    "Ryan Farr, Jessica Battilana and Ed Anderson",
                    "05/13/2014",
                    409,
                    3,
                    "https://imgv2-1-f.scribdassets.com/img/word_document/239948233/original/216x287/ab845fcd76/1617229623?v=1",
                    "https://www.scribd.com/book/239948233/Sausage-Making-The-Definitive-Guide-with-Recipes",
                    mk.get(3)));
            ejbBuku.insert(new Buku("Quick & Easy Korean Cooking: More Than 70 Everyday Recipes",
                    180000,
                    5,
                    "Cecilia Hae-Jin Lee and Julie Toy",
                    "01/11/2013",
                    256,
                    4,
                    "https://imgv2-2-f.scribdassets.com/img/word_document/239944370/original/216x287/2d1e55d526/1617229621?v=1",
                    "https://www.scribd.com/book/239944370/Quick-Easy-Korean-Cooking-More-Than-70-Everyday-Recipes",
                    mk.get(3)));
            ejbBuku.insert(new Buku("Baked Explorations: Classic American Desserts Reinvented",
                    300000,
                    5,
                    "Matt Lewis, Renato Poliafito and Tina Rupp",
                    "12/12/2011",
                    407,
                    4,
                    "https://imgv2-2-f.scribdassets.com/img/word_document/372674118/original/216x287/ff04cc4314/1623043229?v=1",
                    "https://www.scribd.com/book/372674118/Baked-Explorations-Classic-American-Desserts-Reinvented",
                    mk.get(3)));
            ejbBuku.insert(new Buku("Bradbury Stories: 100 of His Most Celebrated Tales",
                    250000,
                    5,
                    "Ray Bradbury",
                    "05/21/2013",
                    1470,
                    4,
                    "https://imgv2-1-f.scribdassets.com/img/word_document/163635913/original/216x287/7e38bb43d5/1623685025?v=1",
                    "https://www.scribd.com/book/163635913/Bradbury-Stories-100-of-His-Most-Celebrated-Tales",
                    mk.get(1)));
            ejbBuku.insert(new Buku("The Rise and Fall of D.O.D.O.: A Novel",
                    180000,
                    7,
                    "Neal Stephenson and Nicole Galland",
                    "06/13/2017",
                    861,
                    4,
                    "https://imgv2-2-f.scribdassets.com/img/word_document/351160354/original/216x287/5533851fc2/1623687307?v=1",
                    "https://www.scribd.com/book/351160354/The-Rise-and-Fall-of-D-O-D-O-A-Novel",
                    mk.get(1)));
            ejbBuku.insert(new Buku("https://www.scribd.com/book/364661651/Puppy-Training-Train-Your-Puppy-in-Obedience-Potty-Training-and-Leash-Training-in-Record-Time",
                    160000,
                    5,
                    "Anthony Portokaloglou",
                    "11/16/2017",
                    41,
                    5,
                    "https://imgv2-1-f.scribdassets.com/img/word_document/364661651/original/216x287/df11527484/1623347568?v=1",
                    "https://www.scribd.com/book/364661651/Puppy-Training-Train-Your-Puppy-in-Obedience-Potty-Training-and-Leash-Training-in-Record-Time",
                    mk.get(4)));
            ejbBuku.insert(new Buku("AGGRESSION IN DOGS: PRACTICAL MANAGEMENT, PREVENTION & BEHAVIOUR MODIFICATION",
                    150000,
                    6,
                    "Brenda Aloff",
                    "01/01/2002",
                    960,
                    4,
                    "https://imgv2-1-f.scribdassets.com/img/word_document/357227442/original/216x287/5a4d1cff12/1617228997?v=1",
                    "https://www.scribd.com/book/357227442/AGGRESSION-IN-DOGS-PRACTICAL-MANAGEMENT-PREVENTION-BEHAVIOUR-MODIFICATION",
                    mk.get(4)));
            ejbBuku.insert(new Buku("Meet Your Dog",
                    165000,
                    7,
                    "Kim Brophey",
                    "04/17/2018",
                    375,
                    4,
                    "https://imgv2-1-f.scribdassets.com/img/word_document/375723990/original/216x287/2d79882520/1617233823?v=1",
                    "https://www.scribd.com/book/375723990/Meet-Your-Dog",
                    mk.get(4)));
            ejbBuku.insert(new Buku("Ultimate Guide to Advanced Combat: Combat Strategies and Battle Techniques for Minecraft®™",
                    180000,
                    5,
                    "Triumph Books",
                    "12/01/2016",
                    223,
                    3,
                    "https://imgv2-2-f.scribdassets.com/img/word_document/331453141/original/216x287/0ef3c33ede/1617221374?v=1",
                    "https://www.scribd.com/book/331453141/Ultimate-Guide-to-Advanced-Combat-Combat-Strategies-and-Battle-Techniques-for-Minecraft",
                    mk.get(5)));
            ejbBuku.insert(new Buku("The Fortnite Guide to Staying Alive: Tips and Tricks for Every Kind of Player",
                    100000,
                    5,
                    "Daniel Kuhn",
                    "10/12/2018",
                    111,
                    5,
                    "https://imgv2-2-f.scribdassets.com/img/word_document/390778429/original/216x287/1848cb968b/1617228805?v=1",
                    "https://www.scribd.com/book/390778429/The-Fortnite-Guide-to-Staying-Alive-Tips-and-Tricks-for-Every-Kind-of-Player",
                    mk.get(5)));
            ejbBuku.insert(new Buku("The Phantom Virus: Herobrine's Revenge Book One (A Gameknight999 Adventure): An Unofficial Minecrafter's Adventure",
                    120000,
                    4,
                    "MARK CHEVERTON",
                    "03/08/2016",
                    239,
                    3,
                    "https://imgv2-1-f.scribdassets.com/img/word_document/396765799/original/432x574/de28a175aa/1621400867?v=1",
                    "https://www.scribd.com/book/396765799/The-Phantom-Virus-Herobrine-s-Revenge-Book-One-A-Gameknight999-Adventure-An-Unofficial-Minecrafter-s-Adventure",
                    mk.get(5)));
            ejbBuku.insert(new Buku("The Legend of Dragon Quest: Creation - universe - decryption",
                    120000,
                    5,
                    "Daniel Andreyev",
                    "05/21/2019",
                    342,
                    3,
                    "https://imgv2-1-f.scribdassets.com/img/word_document/496227688/original/216x287/ea40936636/1617229559?v=1",
                    "https://www.scribd.com/book/496227688/The-Legend-of-Dragon-Quest-Creation-universe-decryption",
                    mk.get(5)));
            adminPerpus = new AdminPerpus();
            addMessage(FacesMessage.SEVERITY_INFO, "Notification", "Inject Success");
        }
    }

}
