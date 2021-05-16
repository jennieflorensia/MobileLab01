package jennie.umn.ac.w09_27184;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class MahasiswaRepository {
    private MahasiswaDao daoMahasiswa;
    private LiveData<List<Mahasiswa>> daftarMahasiswa;

    MahasiswaRepository(Application app){
        MahasiswaRoomDatabase db =
                MahasiswaRoomDatabase.getDatabase(app);
        daoMahasiswa = db.daoMahasiswa();
        daftarMahasiswa = daoMahasiswa.getAllMahasiswa();
    }
    LiveData<List<Mahasiswa>> getDaftarMahasiswa(){
        return this.daftarMahasiswa;
    }
    public void insert(Mahasiswa mhs){
        new insertAsyncTask(daoMahasiswa).execute(mhs);
    }

    public void deleteAll(){
        new deleteAllAsyncTask(daoMahasiswa).execute();
    }
    public void delete(Mahasiswa mhs) {
        new deleteAsyncTask(daoMahasiswa).execute(mhs);
    }
    public void update(Mahasiswa mhs) {
        new updateAsyncTask(daoMahasiswa).execute(mhs);
    }
    private static class insertAsyncTask extends
            AsyncTask<Mahasiswa, Void, Void> {
        private MahasiswaDao asyncDaoMahasiswa;
        insertAsyncTask(MahasiswaDao dao){
            this.asyncDaoMahasiswa = dao;
        }
        @Override
        protected Void doInBackground(final Mahasiswa... mahasiswa) {
            asyncDaoMahasiswa.insert(mahasiswa[0]);
            return null;
        }
    }
    private static class deleteAllAsyncTask extends AsyncTask<Void, Void, Void> {
        private MahasiswaDao asyncDaoMahasiswa;
        deleteAllAsyncTask(MahasiswaDao dao){
            this.asyncDaoMahasiswa = dao;
        }
        @Override
        protected Void doInBackground(final Void... voids) {
            asyncDaoMahasiswa.deleteAll();
            return null;
        }
    }
    private static class deleteAsyncTask extends AsyncTask<Mahasiswa, Void, Void>{
        private MahasiswaDao asyncDaoMahasiswa;
        deleteAsyncTask(MahasiswaDao dao){
            this.asyncDaoMahasiswa = dao;
        }
        @Override
        protected Void doInBackground(final Mahasiswa... mahasiswas) {
            asyncDaoMahasiswa.delete(mahasiswas[0]);
            return null;
        }
    }
    private static class updateAsyncTask extends AsyncTask<Mahasiswa, Void, Void>{
        private MahasiswaDao asyncDaoMahasiswa;
        updateAsyncTask(MahasiswaDao dao){
            this.asyncDaoMahasiswa = dao;
        }
        @Override
        protected Void doInBackground(final Mahasiswa... mahasiswas) {
            asyncDaoMahasiswa.update(mahasiswas[0]);
            return null;
        }
    }
}