package enyeinteractive.com.bluetoothanalyzer.db;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.ForeignKey;
import com.raizlabs.android.dbflow.annotation.ForeignKeyReference;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;
import com.raizlabs.android.dbflow.structure.container.ForeignKeyContainer;


@Table(databaseName = AppDatabase.NAME)
public class Characteristic  extends BaseModel {

    @Column
    @PrimaryKey(autoincrement = true)
    long id;

    @Column
    String uuid;

    @Column
    String alias;

    @Column
    @ForeignKey(references = {@ForeignKeyReference(columnName = "service_id",
            columnType = Long.class,
            foreignColumnName = "id")},
            saveForeignKeyModel = false)
    ForeignKeyContainer<Service> serviceContainer;

    public void setService(Service service) {
        serviceContainer = new ForeignKeyContainer<>(Service.class);
        serviceContainer.setModel(service);
    }


}
