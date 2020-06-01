package pe.edu.tecsup.sugarormapp;

import android.content.res.Configuration;

import com.orm.SchemaGenerator;
import com.orm.SugarApp;
import com.orm.SugarContext;
import com.orm.SugarDb;

public class SugarOrmApplication extends SugarApp {
    @Override
    public void onCreate() {
        super.onCreate();
        SugarContext.init(getApplicationContext());

        // para crear la tabla si no existe
        SchemaGenerator schemaGenerator = new SchemaGenerator(this);
        schemaGenerator.createDatabase(new SugarDb(this).getDB());
    }
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        SugarContext.terminate();
    }
}
